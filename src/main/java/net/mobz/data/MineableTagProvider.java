package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.mobz.init.MobZBlocks;

public class MineableTagProvider extends TagsProvider<Block> {
	public MineableTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.BLOCK, lookupProvider);
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(MobZBlocks.AMAT_BLOCK.get().builtInRegistryHolder().key())
			.add(MobZBlocks.BOSS_BLOCK.get().builtInRegistryHolder().key())
			.add(MobZBlocks.HARDENED_METAL_BLOCK.get().builtInRegistryHolder().key())
			;
	}
}
