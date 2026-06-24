package net.mobz.forge;

import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.mobz.client.EntityRenderers;

public class ClientRegistrationHandler {
	public static void registerClientListeners(BusGroup modBusGroup) {
		FMLClientSetupEvent.getBus(modBusGroup).addListener(ClientRegistrationHandler::onClientSetup);
		RegisterLayerDefinitions.BUS.addListener(ClientRegistrationHandler::onLayerDefinitionRegistration);
		registerConfigGui();
	}

	private static void onClientSetup(final FMLClientSetupEvent event) {
		EntityRenderers.registerAll(net.minecraft.client.renderer.entity.EntityRenderers::register);
	}

	private static void onLayerDefinitionRegistration(final RegisterLayerDefinitions event) {
		EntityRenderers.registerLayerDefinitions(event::registerLayerDefinition);
	}

	public static void registerConfigGui() {
//		ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((client, parent) -> AutoConfig.getConfigScreen(Configs.class, parent).get()));
	}
}
