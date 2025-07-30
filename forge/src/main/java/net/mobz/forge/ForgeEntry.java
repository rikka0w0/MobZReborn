package net.mobz.forge;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.ClientBootstrap;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.listener.Priority;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import net.mobz.ILootTableAdder;
import net.mobz.MobZ;
import net.mobz.data.ModelDataProvider;
import net.mobz.data.Recipes;
import net.mobz.data.Advancements;
import net.mobz.data.BlockTagProvider;
import net.mobz.data.EntityTagProvider;
import net.mobz.data.EquipmentModelProvider;
import net.mobz.data.ItemTagProvider;
import net.mobz.data.JukeboxSongs;
import net.mobz.data.Loots;
import net.mobz.data.SpawnBiomeTagProvider;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobSpawns;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	private final ForgeRegistryWrapper registryWrapper;

	public ForgeEntry(FMLJavaModLoadingContext context) {
		if (instance == null)
			instance = this;
		else
			throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

		this.registryWrapper = new ForgeRegistryWrapper(context);
		MobZ.platform = this.registryWrapper;

		MobZ.configs = ForgeConfigManager.loadFromFile();
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientRegistrationHandler::registerConfigGui);

		MobZ.invokeStaticFields();
	}

	@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public final static class ModEventBusHandler {
		@SubscribeEvent
		public static void onEntityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
			ForgeEntry.instance.registryWrapper.applyGlobalEntityAttrib(event::put);
		}

		private static record SpawnPlacementRegistar(SpawnPlacementRegisterEvent event) {
			private <T extends Mob> void register(EntityType<T> entityType, SpawnPlacementType spawnPlacementType, Heightmap.Types types, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {
				event.register(entityType, spawnPlacementType, types, spawnPredicate, Operation.OR);
			}

			private void registerAll() {
				MobSpawnRestrictions.applyAll(this::register);
			}
		}

		@SubscribeEvent
		public static void onSpawnPlacementRegisterEvent(final SpawnPlacementRegisterEvent event) {
			new SpawnPlacementRegistar(event).registerAll();
		}

		@SubscribeEvent
		public static void onTabPopulation(BuildCreativeModeTabContentsEvent event) {

		}

		@SubscribeEvent
		public static void onDataGeneratorInvoked(final GatherDataEvent event) {
			DataGenerator generator = event.getGenerator();
			PackOutput packOutput = generator.getPackOutput();
			CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

			// We need to invoke client-side bootstrap manually, otherwise some codec wont work
			// https://github.com/MinecraftForge/MinecraftForge/issues/10203
			if (event.includeClient()) {
				ClientBootstrap.bootstrap();
			}

			// Data: Mob spawns
			RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

			Map<ResourceLocation, Pair<TagKey<Biome>, WeightedList<SpawnerData>>> rawMap = new HashMap<>();
			MobSpawns.collectAll(rawMap);
			RegistrySetBuilder.RegistryBootstrap<BiomeModifier> biomeModifierPopulator = context -> {
				for (Entry<ResourceLocation, Pair<TagKey<Biome>, WeightedList<SpawnerData>>> entry: rawMap.entrySet()) {
					ResourceLocation resloc = entry.getKey();
					ResourceKey<BiomeModifier> resKey = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, resloc);
					Pair<TagKey<Biome>, WeightedList<SpawnerData>> payload = entry.getValue();
					TagKey<Biome> toBiomeWithTag = payload.getLeft();
					HolderGetter<Biome> biomeReg = context.lookup(toBiomeWithTag.registry());
					HolderSet.Named<Biome> biomeHolderSet = biomeReg.getOrThrow(toBiomeWithTag);
					BiomeModifier modifier = new ForgeBiomeModifiers.AddSpawnsBiomeModifier(biomeHolderSet, payload.getRight());
					context.register(resKey, modifier);
				}
			};

			generator.addProvider(event.includeServer(), (DataProvider.Factory<DatapackBuiltinEntriesProvider>) vanillaPackOutput ->
				new DatapackBuiltinEntriesProvider(vanillaPackOutput, lookupProvider,
					new RegistrySetBuilder()
						.add(ForgeRegistries.Keys.BIOME_MODIFIERS, biomeModifierPopulator)
						.add(Registries.JUKEBOX_SONG, new JukeboxSongs()),
					Set.of(MobZ.MODID)
				)
			);

			// Data: Biome tags for spawns
			generator.addProvider(event.includeServer(), new SpawnBiomeTagProvider(packOutput, lookupProvider));

			// Data: Mineable tags
			generator.addProvider(event.includeServer(), new BlockTagProvider(packOutput, lookupProvider));

			// Data: Item tags
			generator.addProvider(event.includeServer(), new ItemTagProvider(packOutput, lookupProvider));

			// Data: Entity tags
			generator.addProvider(event.includeServer(), new EntityTagProvider(packOutput, lookupProvider));

			// Resource: Models and blockstates
			generator.addProvider(event.includeClient(), new ModelDataProvider(packOutput, registryAccess.lookupOrThrow(Registries.ITEM)));

			// Data: LootTable
			generator.addProvider(event.includeServer(), (DataProvider.Factory<LootTableProvider>) vanillaPackOutput -> Loots.all(vanillaPackOutput, lookupProvider));

			// Data: Recipes
			generator.addProvider(event.includeServer(), new Recipes.Runner(packOutput, lookupProvider));

			// Data: Advancements
			generator.addProvider(event.includeServer(), (DataProvider.Factory<AdvancementProvider>) vanillaPackOutput -> Advancements.all(vanillaPackOutput, lookupProvider));

			// Resource: EquipmentModels
			generator.addProvider(event.includeClient(), new EquipmentModelProvider(packOutput));
		}
	}

	@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public final static class ForgeEventBusHandler { // MinecraftForge.EVENT_BUS MinecraftForgeEventsHandler
		@SubscribeEvent(priority = Priority.HIGH)
		public static void onLootTableLoadEvent(final LootTableLoadEvent event) {
			ILootTableAdder lootTableAdder = (lootTableIDs, range, entryBuilder) -> {
				lootTableIDs.stream()
				.filter(lootTableID -> lootTableID.location().equals(event.getName()))
				.forEach(lootTableID ->
					event.getTable().addPool(LootPool.lootPool().setRolls(range).add(entryBuilder).build())
				);
			};

			LootTableModifier.loadAll(lootTableAdder);
		}
	}
}
