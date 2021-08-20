package net.mobz.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

public class BossTrophy extends AbstractHead {
	protected static final VoxelShape SHAPE =
			Shapes.or(box(4D, 0, 4D, 12D, 8D, 12D),
			box(4D, 8D, 4D, 5D, 9D, 12D), box(11D, 8D, 4D, 12D, 9D, 12D),
			box(5D, 8D, 4D, 11D, 9D, 5D), box(5D, 8D, 11D, 11D, 9D, 12D),
			box(4D, 9D, 11D, 6D, 10D, 12D), box(10D, 9D, 11D, 12D, 10D, 12D),
			box(7D, 9D, 11D, 9D, 10D, 12D), box(7D, 9D, 4D, 9D, 10D, 5D),
			box(10D, 9D, 4D, 12D, 10D, 5D), box(4D, 9D, 4D, 6D, 10D, 5D),
			box(4D, 9D, 7D, 5D, 10D, 9D), box(11D, 9D, 7D, 12D, 10D, 9D),
			box(11D, 9D, 10D, 12D, 10D, 11D), box(4D, 9D, 10D, 5D, 10D, 11D),
			box(4D, 9D, 5D, 5D, 10D, 6D), box(11D, 9D, 5D, 12D, 10D, 6D));;

	public BossTrophy(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
