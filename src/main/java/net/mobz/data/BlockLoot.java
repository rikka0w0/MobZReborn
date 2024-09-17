package net.mobz.data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

import net.mobz.init.MobZBlocks;

public class BlockLoot extends BlockLootSubProvider {
	private final List<Block> blocks = new LinkedList<>();

	protected BlockLoot() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	/*
	 * BlockLootSubProvider start
	 */

	@Override
	public void add(Block pBlock, LootTable.Builder pBuilder) {
		super.add(pBlock, pBuilder);
		blocks.add(pBlock);
	}

	@Override
	public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
		this.generate();
		Set<ResourceLocation> processed = new HashSet<>();

		for (Block block : this.blocks) {
			if (block.isEnabled(this.enabledFeatures)) {
				ResourceLocation resLoc = block.getLootTable();
				if (resLoc != BuiltInLootTables.EMPTY && processed.add(resLoc)) {
					LootTable.Builder builder = this.map.remove(resLoc);
					if (builder == null) {
						throw new IllegalStateException(
							String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", resLoc, BuiltInRegistries.BLOCK.getKey(block))
						);
					}

					biConsumer.accept(resLoc, builder);
				}
			}
		}

		if (!this.map.isEmpty()) {
			throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
		}
	}

	/*
	 * BlockLootSubProvider end
	 */

	@Override
	public void generate() {
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
