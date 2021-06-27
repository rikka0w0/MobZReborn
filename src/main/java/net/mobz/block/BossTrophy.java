package net.mobz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;


public class BossTrophy extends Block {
	private final static Direction[] horizontalDirections = new Direction[]{Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.EAST};

	public static final DirectionProperty ROTATION;
	protected static final VoxelShape SHAPE;

	public BossTrophy(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		return (BlockState) this.defaultBlockState().setValue(ROTATION,
				horizontalDirections[MathHelper.floor((double) (ctx.getRotation() * 4.0F / 360.0F) + 0.5D) & 3].getOpposite());
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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(ROTATION);
	}

	static {
		ROTATION = BlockStateProperties.FACING;
		SHAPE = VoxelShapes.or(box(4D, 0, 4D, 12D, 8D, 12D),
				box(4D, 8D, 4D, 5D, 9D, 12D), box(11D, 8D, 4D, 12D, 9D, 12D),
				box(5D, 8D, 4D, 11D, 9D, 5D), box(5D, 8D, 11D, 11D, 9D, 12D),
				box(4D, 9D, 11D, 6D, 10D, 12D), box(10D, 9D, 11D, 12D, 10D, 12D),
				box(7D, 9D, 11D, 9D, 10D, 12D), box(7D, 9D, 4D, 9D, 10D, 5D),
				box(10D, 9D, 4D, 12D, 10D, 5D), box(4D, 9D, 4D, 6D, 10D, 5D),
				box(4D, 9D, 7D, 5D, 10D, 9D), box(11D, 9D, 7D, 12D, 10D, 9D),
				box(11D, 9D, 10D, 12D, 10D, 11D), box(4D, 9D, 10D, 5D, 10D, 11D),
				box(4D, 9D, 5D, 5D, 10D, 6D), box(11D, 9D, 5D, 12D, 10D, 6D));
	}
}
