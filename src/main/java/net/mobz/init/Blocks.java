package net.mobz.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.mobz.block.BossTrophy;
import net.mobz.common.IRegistryWrapper;

public class Blocks {
	@SuppressWarnings("deprecation")
	public static final BossTrophy BOSS_TROPHY = new BossTrophy(AbstractBlock.Properties.of(Material.DECORATION)
			.strength(1.0F).dropsLike(net.minecraft.block.Blocks.ZOMBIE_HEAD));

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("bosstrophy", BOSS_TROPHY, Items.tab);
	}
}
