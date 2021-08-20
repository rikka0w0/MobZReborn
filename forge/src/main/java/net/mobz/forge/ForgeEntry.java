package net.mobz.forge;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.mobz.ILootTableAdder;
import net.mobz.MobZ;
import net.mobz.init.LootTableModifier;
import net.mobz.init.MobSpawns;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	public static ForgeRegistryWrapper regWrapper;

	public ForgeEntry() {
    	if (instance == null) 
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

    	ForgeConfigManager.register();

    	regWrapper = new ForgeRegistryWrapper();
    	MobZ.registerAll(regWrapper, SpawnPlacements::register);
	}

    @Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public final static class ModEventBusHandler {
    	@SubscribeEvent
		public static void onEntityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
    		regWrapper.applyGlobalEntityAttrib(event::put);
    	}
    }

    @Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public final static class ForgeEventBusHandler{	// MinecraftForge.EVENT_BUS MinecraftForgeEventsHandler
    	@SubscribeEvent(priority = EventPriority.HIGH)
		public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
    		if (event.getPhase() == EventPriority.HIGH) {
    			ForgeMobSpawnAdder mobSpawns = new ForgeMobSpawnAdder();
    			MobSpawns.addMobSpawns(mobSpawns);
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
