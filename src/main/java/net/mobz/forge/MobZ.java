package net.mobz.forge;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mobz.init.Blocks;
import net.mobz.init.Entities;
import net.mobz.init.Items;

@Mod(MobZ.MODID)
public class MobZ {
	public static final String MODID = "mobz";
	
	public static MobZ instance;
	
	public static ForgeRegistryWrapper regWrapper;
	
	public MobZ() {
    	if (instance == null) 
            instance = this;
        else
            throw new RuntimeException("Duplicated Class Instantiation: net.mobz.forge.MobZ");
    	
    	regWrapper = new ForgeRegistryWrapper();
    	Items.registerAll(regWrapper);
    	Blocks.registerAll(regWrapper);
    	Entities.registerAll(regWrapper);
	}
	
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public final static class ModEventBusHandler {
    	@SubscribeEvent
		public static void registerBlocks(final EntityAttributeCreationEvent event) {
    		regWrapper.applyGlobalEntityAttrib(event::put);
    	}
    }
}