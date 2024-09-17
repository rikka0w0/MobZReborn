package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import net.mobz.init.MobZBlocks;

public class BlockTagProvider extends TagsProvider<Block> {
	public BlockTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.BLOCK, lookupProvider);
	}

	public TagsProvider.TagAppender<Block> tag(TagKey<Block> tagKey,
			Block... blocks) {
		TagsProvider.TagAppender<Block> appender = this.tag(tagKey);
		for (Block block: blocks) {
			appender.add(block.builtInRegistryHolder().key());
		}
		return appender;
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE,
			MobZBlocks.AMAT_BLOCK.get(),
			MobZBlocks.BOSS_BLOCK.get(),
			MobZBlocks.HARDENED_METAL_BLOCK.get()
		);
	}
}
