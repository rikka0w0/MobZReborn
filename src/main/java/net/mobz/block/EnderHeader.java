package net.mobz.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

public class EnderHeader extends AbstractHead {
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);;

	@Nullable
	private BlockPattern pattern = null;

	public BlockPattern getWithenderPattern() {
		if (this.pattern == null) {
			this.pattern = BlockPatternBuilder.start().aisle("hhh", "sss", "asa")
			.where('h', BlockInWorld.hasState(BlockStatePredicate.forBlock(this)))
			.where('s', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SOUL_SAND)))
			.where('a', BlockInWorld.hasState(BlockState::isAir))
			.build();
		}

		return this.pattern;
	}

	public EnderHeader(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
