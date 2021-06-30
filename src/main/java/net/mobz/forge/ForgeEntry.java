package net.mobz.forge;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mobz.MobZ;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	public static ForgeRegistryWrapper regWrapper;
	public static ForgeMobSpawnAdder mobSpawns;

	public ForgeEntry() {
    	if (instance == null) 
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

    	regWrapper = new ForgeRegistryWrapper();
    	mobSpawns = new ForgeMobSpawnAdder();
    	MobZ.registerAll(regWrapper, mobSpawns);
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
    			mobSpawns.addMobSpawns(event.getCategory(), event.getSpawns());
    		}
    	}
    }
}
