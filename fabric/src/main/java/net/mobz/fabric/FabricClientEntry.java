package net.mobz.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.mobz.client.EntityRenderers;
import net.mobz.client.VanillaClientRegistry;

public class FabricClientEntry implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRenderers.registerAll(EntityRendererRegistry.INSTANCE::register);
		VanillaClientRegistry.registerItemModelProperties(FabricModelPredicateProviderRegistry::register);
	}
}