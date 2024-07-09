package net.mobz.neoforge;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.storage.loot.LootPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

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
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import net.mobz.ILootTableAdder;
import net.mobz.MobZ;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobSpawns;
import net.mobz.neoforge.datagen.SpawnBiomeTagProvider;
import net.mobz.neoforge.datagen.ItemModelDataProvider;

@Mod(MobZ.MODID)
public class NeoforgeEntry {
	public static NeoforgeEntry instance;

	public NeoforgeEntry(IEventBus modBus) {
		if (instance == null)
			instance = this;
		else
			throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

		MobZ.platform = new NeoforgeRegistryWrapper(modBus);

		MobZ.initConfig();

		Supplier<Runnable> toRun = () -> ClientRegistrationHandler::registerConfigGui;
        if (Dist.CLIENT == FMLEnvironment.dist)  {
            toRun.get().run();
        }

		MobZ.invokeStaticFields();
	}

	@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public final static class ModEventBusHandler {
		@SubscribeEvent
		public static void onEntityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
			((NeoforgeRegistryWrapper) MobZ.platform).applyGlobalEntityAttrib(event::put);
			MobSpawnRestrictions.applyAll(SpawnPlacements::register);
		}

		@SubscribeEvent
		public static void onTabPopulation(BuildCreativeModeTabContentsEvent event) {

		}

		@SubscribeEvent
		public static void onDataGeneratorInvoked(final GatherDataEvent event) {
			DataGenerator generator = event.getGenerator();
	        PackOutput packOutput = generator.getPackOutput();
	        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
			ExistingFileHelper exfh = event.getExistingFileHelper();

			// Data: Mob spawns
			RegistryAccess registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

			Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> rawMap = new HashMap<>();
			MobSpawns.collectAll(rawMap);
			RegistrySetBuilder.RegistryBootstrap<BiomeModifier> biomeModifierPopulator = context -> {
				for (Entry<ResourceLocation, Pair<TagKey<Biome>, List<SpawnerData>>> entry: rawMap.entrySet()) {
					ResourceLocation resloc = entry.getKey();
					ResourceKey<BiomeModifier> resKey = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, resloc);
					Pair<TagKey<Biome>, List<SpawnerData>> payload = entry.getValue();
					TagKey<Biome> toBiomeWithTag = payload.getLeft();
					HolderGetter<Biome> biomeReg = context.lookup(toBiomeWithTag.registry());
					HolderSet.Named<Biome> biomeHolderSet = biomeReg.getOrThrow(toBiomeWithTag);
					BiomeModifier modifier = new BiomeModifiers.AddSpawnsBiomeModifier(biomeHolderSet, payload.getRight());
					context.register(resKey, modifier);
				}
			};

			generator.addProvider(event.includeServer(), (DataProvider.Factory<DatapackBuiltinEntriesProvider>) vanillaPackOutput ->
				new DatapackBuiltinEntriesProvider(vanillaPackOutput, lookupProvider,
					new RegistrySetBuilder().add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, biomeModifierPopulator),
					Set.of(MobZ.MODID)
				)
			);

			// Data: Biome tags for spawns
			generator.addProvider(event.includeServer(), new SpawnBiomeTagProvider(packOutput, lookupProvider, exfh));

			// Resource: SpawnEgg items
			generator.addProvider(event.includeClient(), new ItemModelDataProvider(packOutput, registryAccess.registryOrThrow(Registries.ITEM),  exfh));
		}
	}

	@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public final static class ForgeEventBusHandler { // MinecraftForge.EVENT_BUS MinecraftForgeEventsHandler
		@SubscribeEvent(priority = EventPriority.HIGH)
		public static void onLootTableLoadEvent(final LootTableLoadEvent event) {
			ILootTableAdder lootTableAdder = (lootTableIDs, range, entryBuilder) -> {
				for (ResourceLocation lootTableID : lootTableIDs) {
					if (event.getName().equals(lootTableID)) {
						event.getTable().addPool(LootPool.lootPool().setRolls(range).add(entryBuilder).build());
					}
				}
			};

			LootTableModifier.loadAll(lootTableAdder);
		}
	}
}