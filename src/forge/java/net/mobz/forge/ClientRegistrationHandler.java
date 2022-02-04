package net.mobz.forge;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mobz.Configs;
import net.mobz.MobZ;
import net.mobz.client.EntityRenderers;
import net.mobz.client.VanillaClientRegistry;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		EntityRenderers.registerAll(ForgeClientRegistryWrapper.instance);
		VanillaClientRegistry.registerItemModelProperties();
	}

	public static void registerConfigGui() {
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> AutoConfig.getConfigScreen(Configs.class, parent).get());
	}
}
