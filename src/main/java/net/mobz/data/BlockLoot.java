package net.mobz.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import net.mobz.init.MobZBlocks;

public class BlockLoot extends BlockLootSubProvider {
	private final List<Block> blocks = new LinkedList<>();

	protected BlockLoot() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void add(Block pBlock, LootTable.Builder pBuilder) {
		super.add(pBlock, pBuilder);
		blocks.add(pBlock);
	}

	@Override
    protected Iterable<Block> getKnownBlocks() {
        return this.blocks;
    }

	@Override
	protected void generate() {
		this.dropSelf(MobZBlocks.AMAT_BLOCK.get());
		this.dropSelf(MobZBlocks.BOSS_BLOCK.get());
		this.dropSelf(MobZBlocks.BOSS_TROPHY.get());
		this.dropSelf(MobZBlocks.ENDER_HEADER.get());
		this.dropSelf(MobZBlocks.HARDENED_METAL_BLOCK.get());

		this.dropSelf(MobZBlocks.TOTEM_BASE.get());
		this.dropSelf(MobZBlocks.TOTEM_MIDDLE.get());
		this.dropSelf(MobZBlocks.TOTEM_TOP.get());
	}
}
