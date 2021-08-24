package net.mobz.forge;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.mobz.ILootTableAdder;
import net.mobz.MobZ;
import net.mobz.forge.datagen.SpawnEggItemModelDataProvider;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobSpawns;
import net.mobz.portable.StaticAPIWrapper;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	private static ForgeMobSpawnAdder mobSpawns = new ForgeMobSpawnAdder();

	public ForgeEntry() {
    	if (instance == null)
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

    	ForgeConfigManager.register();

    	MobZ.invokeStaticFields();
    	MobSpawnRestrictions.applyAll(SpawnPlacements::register);
    	MobSpawns.addMobSpawns(mobSpawns);
	}

    @Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public final static class ModEventBusHandler {
    	@SubscribeEvent
		public static void onEntityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
    		((ForgeRegistryWrapper) StaticAPIWrapper.instance).applyGlobalEntityAttrib(event::put);
    	}

    	@SubscribeEvent
    	public static void onDataGeneratorInvoked(final GatherDataEvent event) {
    		DataGenerator generator = event.getGenerator();
    		ExistingFileHelper exfh = event.getExistingFileHelper();

    		if (event.includeClient()) {
    			generator.addProvider(new SpawnEggItemModelDataProvider(generator, exfh));
    		}
    	}
    }

    @Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public final static class ForgeEventBusHandler{	// MinecraftForge.EVENT_BUS MinecraftForgeEventsHandler
    	@SubscribeEvent(priority = EventPriority.HIGH)
		public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
    		if (event.getPhase() == EventPriority.HIGH) {
    			mobSpawns.addMobSpawns(event.getCategory(), event.getSpawns());
    		}
    	}

    	@SubscribeEvent(priority = EventPriority.HIGH)
		public static void onLootTableLoadEvent (final LootTableLoadEvent event) {
    		ILootTableAdder lootTableAdder = (lootTableIDs, range, entryBuilder) -> {
    			for (ResourceLocation lootTableID: lootTableIDs) {
    				if (event.getName().equals(lootTableID)) {
    					event.getTable().addPool(LootPool.lootPool().setRolls(range).add(entryBuilder).build());
    				}
    			}
    		};

    		LootTableModifier.loadAll(lootTableAdder);
    	}
    }
}
