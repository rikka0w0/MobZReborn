package net.mobz.fabric.biome;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;

public interface BiomeModifier {
    void apply(BiomeModification biomeModification);

    /**
     * @return the codec which serializes and deserializes this biome modifier
     */
    Codec<? extends BiomeModifier> codec();
}
