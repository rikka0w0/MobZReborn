package net.mobz.forge;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.storage.loot.LootPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.mobz.ILootTableAdder;
import net.mobz.MobZ;
import net.mobz.forge.datagen.SpawnBiomeTagProvider;
import net.mobz.forge.datagen.SpawnEggItemModelDataProvider;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobSpawns;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	private static final ForgeRegistryWrapper registryWrapper = new ForgeRegistryWrapper();

	public ForgeEntry() {
		if (instance == null)
			instance = this;
		else
			throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

		MobZ.platform = registryWrapper;

		MobZ.initConfig();
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientRegistrationHandler::registerConfigGui);

		MobZ.invokeStaticFields();
	}

	@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public final static class ModEventBusHandler {
		@SubscribeEvent
		public static void onEntityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
			registryWrapper.applyGlobalEntityAttrib(event::put);
			MobSpawnRestrictions.applyAll(SpawnPlacements::register);
		}

		@SubscribeEvent
		public static void onDataGeneratorInvoked(final GatherDataEvent event) {
			DataGenerator generator = event.getGenerator();
			ExistingFileHelper exfh = event.getExistingFileHelper();

			// Data: Mob spawns
			RegistryAccess registryAccess = RegistryAccess.builtinCopy();
			RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, registryAccess);

			Map<ResourceLocation, BiomeModifier> map = new HashMap<>();
			Map<ResourceLocation, Pair<TagKey<Biome>, List<MobSpawnSettings.SpawnerData>>> rawMap = new HashMap<>();
			MobSpawns.collectAll(rawMap);
			rawMap.forEach((resLocFileName, payload) -> {
				TagKey<Biome> toBiomeWithTag = payload.getLeft();
				Registry<Biome> biomeReg = registryAccess.registryOrThrow(toBiomeWithTag.registry());
				HolderSet.Named<Biome> biomeHolderSet = new HolderSet.Named<>(biomeReg, toBiomeWithTag);
				BiomeModifier modifier = new ForgeBiomeModifiers.AddSpawnsBiomeModifier(biomeHolderSet,
						payload.getRight());
				map.put(resLocFileName, modifier);
			});

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, exfh,
					MobZ.MODID, registryOps, ForgeRegistries.Keys.BIOME_MODIFIERS, map));

			// Data: Biome tags for spawns
			generator.addProvider(event.includeServer(), new SpawnBiomeTagProvider(generator,
					registryAccess.registryOrThrow(Registry.BIOME_REGISTRY), exfh));

			// Resource: SpawnEgg items
			generator.addProvider(event.includeClient(), new SpawnEggItemModelDataProvider(generator, registryAccess.registryOrThrow(Registry.ITEM_REGISTRY),  exfh));
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
