package net.mobz.block;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

public class TotemMiddle extends Block {
	protected static final VoxelShape SHAPE = Shapes.or(
			box(4D, 0, 4D, 12D, 2D, 12D), box(5D, 2D, 5D, 11D, 3D, 11D),
			box(4D, 3D, 4D, 12D, 13D, 12D), box(5D, 13D, 5D, 11D, 14D, 11D),
			box(4D, 14D, 4D, 12D, 16D, 12D), box(3D, 4D, 5D, 4D, 12D, 6D),
			box(3D, 4D, 10D, 4D, 12D, 11D), box(3D, 5D, 9D, 4D, 11D, 10D),
			box(3D, 5D, 6D, 4D, 11D, 7D), box(3D, 6D, 7D, 4D, 10D, 8D),
			box(3D, 6D, 8D, 4D, 10D, 9D), box(5D, 4D, 12D, 6D, 12D, 13D),
			box(6D, 5D, 12D, 7D, 11D, 13D), box(7D, 6D, 12D, 8D, 10D, 13D),
			box(8D, 6D, 12D, 9D, 10D, 13D), box(9D, 5D, 12D, 10D, 11D, 13D),
			box(10D, 4D, 12D, 11D, 12D, 13D), box(12D, 4D, 10D, 13D, 12D, 11D),
			box(12D, 5D, 9D, 13D, 11D, 10D), box(12D, 6D, 8D, 13D, 10D, 9D),
			box(12D, 6D, 7D, 13D, 10D, 8D), box(12D, 5D, 6D, 13D, 11D, 7D),
			box(12D, 4D, 5D, 13D, 12D, 6D), box(10D, 4D, 3D, 11D, 12D, 4D),
			box(9D, 5D, 3D, 10D, 11D, 4D), box(8D, 6D, 3D, 9D, 10D, 4D),
			box(7D, 6D, 3D, 8D, 10D, 4D), box(6D, 5D, 3D, 7D, 11D, 4D),
			box(5D, 4D, 3D, 6D, 12D, 4D), box(3D, 0, 3D, 5D, 2D, 5D),
			box(3D, 0, 11D, 5D, 2D, 13D), box(11D, 0, 11D, 13D, 2D, 13D),
			box(11D, 0, 3D, 13D, 2D, 5D));

	public TotemMiddle(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
