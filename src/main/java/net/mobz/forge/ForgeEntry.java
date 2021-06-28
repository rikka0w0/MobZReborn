package net.mobz.forge;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mobz.MobZ;

@Mod(MobZ.MODID)
public class ForgeEntry {
	public static ForgeEntry instance;

	public static ForgeRegistryWrapper regWrapper;

	public ForgeEntry() {
    	if (instance == null) 
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");

    	regWrapper = new ForgeRegistryWrapper();
    	MobZ.registerAll(regWrapper);
	}

    @Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public final static class ModEventBusHandler {
    	@SubscribeEvent
		public static void registerBlocks(final EntityAttributeCreationEvent event) {
    		regWrapper.applyGlobalEntityAttrib(event::put);
    	}
    }
}
