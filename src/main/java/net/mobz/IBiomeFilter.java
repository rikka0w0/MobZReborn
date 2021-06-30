package net.mobz;

import net.minecraft.world.biome.Biome;

public interface IBiomeFilter {
	boolean accept(Biome.Category category);
}
