package net.mobz;

import net.minecraft.world.level.biome.Biome;

@FunctionalInterface
public interface IBiomeFilter {
	boolean accept(Biome.BiomeCategory category);
}
