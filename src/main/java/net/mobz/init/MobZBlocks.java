package net.mobz.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;

import net.mobz.block.*;
import net.mobz.common.IRegistryWrapper;

public class MobZBlocks {
	@SuppressWarnings("deprecation")
	private static final AbstractBlock.Properties ZOMBIE_HEAD_PROP = AbstractBlock.Properties.of(Material.DECORATION)
			.strength(1.0F).dropsLike(net.minecraft.block.Blocks.ZOMBIE_HEAD);

	public static final BossTrophy BOSS_TROPHY = new BossTrophy(ZOMBIE_HEAD_PROP);
	public static final EnderHeader ENDERHEADER = new EnderHeader(ZOMBIE_HEAD_PROP);

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("bosstrophy", BOSS_TROPHY, MobZItems.tab);
		registry.register("enderheader", ENDERHEADER, MobZItems.tab);
	}
}
