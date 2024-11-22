package net.mobz.block;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mobz.MobZRarity;
import net.mobz.entity.MetalGolem;
import net.mobz.init.MobZEntities;

public class HardenedMetalBlock extends Block {
	@Nullable
	private BlockPattern golemPattern;
	private static final Predicate<BlockState> IS_PUMPKIN = (state) -> {
		return state != null && (state.is(Blocks.CARVED_PUMPKIN) || state.is(Blocks.JACK_O_LANTERN));
	};

	public HardenedMetalBlock(Properties settings) {
		super(settings);
	}

	private BlockPattern getGolemPattern() {
		if (this.golemPattern == null) {
			this.golemPattern = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
					.where('^', BlockInWorld.hasState(IS_PUMPKIN))
					.where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(this)))
					.where('~', BlockInWorld.hasState((blockState) -> blockState.isAir()))
					.build();
		}

		return this.golemPattern;
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, @Nullable Orientation orientation, boolean isMoving) {
		super.neighborChanged(state, world, pos, blockIn, orientation, isMoving);

		BlockPattern.BlockPatternMatch blockpattern$patternhelper = this.getGolemPattern().find(world, pos);
		if (blockpattern$patternhelper != null) {
			for (int j = 0; j < this.getGolemPattern().getWidth(); ++j) {
				for (int k = 0; k < this.getGolemPattern().getHeight(); ++k) {
					BlockInWorld cachedblockinfo2 = blockpattern$patternhelper.getBlock(j, k, 0);
					world.setBlock(cachedblockinfo2.getPos(), Blocks.AIR.defaultBlockState(), 2);
					world.globalLevelEvent(2001, cachedblockinfo2.getPos(), Block.getId(cachedblockinfo2.getState()));
				}
			}

			BlockPos spawnPos = blockpattern$patternhelper.getBlock(1, 2, 0).getPos();
			MetalGolem golem = MobZEntities.METALGOLEM.get().create(world, EntitySpawnReason.TRIGGERED);
			golem.moveTo(spawnPos, 0.0F, 0.0F);
			world.addFreshEntity(golem);
		}
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip,
			TooltipFlag options) {
		MobZRarity.UNCOMMON.addToTooltip(tooltip);
	}
}
