package net.mobz.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.mobz.common.BlockDefinition;
import net.mobz.common.IRegistryWrapper;
import net.mobz.init.Items;

public class Blocks {
	public static final BlockDefinition<BossTrophy> BOSS_TROPHY = 
			new BlockDefinition<>("bosstrophy", 
					new BossTrophy(AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(net.minecraft.block.Blocks.ZOMBIE_HEAD)),
					Items.tab);
	
	public static void registerAll(IRegistryWrapper registry) {
		registry.register(BOSS_TROPHY);
	}
}
