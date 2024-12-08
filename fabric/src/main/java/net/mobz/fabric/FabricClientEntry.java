package net.mobz.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mobz.client.EntityRenderers;

public class FabricClientEntry implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRenderers.registerAll(EntityRendererRegistry::register);
		EntityRenderers.registerLayerDefinitions((modelResLoc, layerDefCon) -> EntityModelLayerRegistry.registerModelLayer(modelResLoc, ()->layerDefCon.get()));
	}
}
