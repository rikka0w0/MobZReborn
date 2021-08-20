package net.mobz;

import net.minecraft.world.level.biome.Biome;

public interface IBiomeFilter {
	boolean accept(Biome.BiomeCategory category);
}
