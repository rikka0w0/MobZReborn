package net.mobz;

import java.util.function.Supplier;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

@FunctionalInterface
public interface ILayerDefinitionRegistration {
	void register(ModelLayerLocation modelLayer, Supplier<LayerDefinition> layerDefinitionConstructor);
}
