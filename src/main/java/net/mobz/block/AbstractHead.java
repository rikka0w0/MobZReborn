package net.mobz.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.util.Mth;

public abstract class AbstractHead extends Block {
	public static final EnumProperty<Direction> ROTATION = BlockStateProperties.HORIZONTAL_FACING;

	public AbstractHead(Properties settings) {
		super(settings);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		int horizontal2DData = Mth.floor((double) (ctx.getRotation() * 4.0F / 360.0F) + 0.5D) & 3;
		Direction playerLookingAt = Direction.from2DDataValue(horizontal2DData);
		return (BlockState) this.defaultBlockState().setValue(ROTATION, playerLookingAt.getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return (BlockState) state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return (BlockState) state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION);
	}
}
