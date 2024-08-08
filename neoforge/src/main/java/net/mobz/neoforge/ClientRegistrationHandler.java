package net.mobz.neoforge;

import net.minecraft.client.renderer.item.ItemProperties;

import net.mobz.MobZ;
import net.mobz.client.EntityRenderers;
import net.mobz.config.MobZComposedGuiRegistryAccess;
import net.mobz.client.VanillaClientRegistry;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@EventBusSubscriber(value = Dist.CLIENT, modid = MobZ.MODID, bus = EventBusSubscriber.Bus.MOD)
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
		ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class,
				() -> (client, parent) -> MobZComposedGuiRegistryAccess.buildScreen(parent));
	}
}
