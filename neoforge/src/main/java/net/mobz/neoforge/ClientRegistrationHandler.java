package net.mobz.neoforge;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mobz.Configs;
import net.mobz.MobZ;
import net.mobz.client.EntityRenderers;
import net.mobz.client.VanillaClientRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		EntityRenderers.registerAll(net.minecraft.client.renderer.entity.EntityRenderers::register);
		VanillaClientRegistry.registerItemModelProperties(ItemProperties::register);
	}

	@SubscribeEvent
	public static void onLayerDefinitionRegistration(final RegisterLayerDefinitions event) {
		EntityRenderers.registerLayerDefinitions(event::registerLayerDefinition);
	}

	public static void registerConfigGui() {
		ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((client, parent) -> AutoConfig.getConfigScreen(Configs.class, parent).get()));
	}
}
