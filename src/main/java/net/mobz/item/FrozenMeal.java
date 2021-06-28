package net.mobz.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public class FrozenMeal extends SimpleItem {
	public FrozenMeal(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		ItemStack handItem = player.getMainHandItem();
		BlockPos pos = context.getClickedPos();
		BlockState blockState = world.getBlockState(pos);
		
		BlockRayTraceResult blockHitResult = getPlayerPOVHitResult(world, player,
				RayTraceContext.FluidMode.SOURCE_ONLY);
		BlockPos blockPos = blockHitResult.getBlockPos();
		BlockState blockBlock = world.getBlockState(blockPos);

		if (blockBlock.getBlock() instanceof IBucketPickupHandler && !world.isClientSide) {
			world.setBlock(blockPos, Blocks.ICE.defaultBlockState(), 3);
			handItem.shrink(1);
			return ActionResultType.SUCCESS;
		}

		if (((blockState.getBlock() == Blocks.WATER || blockState.getBlock() == Blocks.ICE) && world.isClientSide)) {
			for (int i = 0; i < 16; ++i) {
				double d = random.nextGaussian() * 0.02D;
				double e = random.nextGaussian() * 0.02D;
				double f = random.nextGaussian() * 0.02D;
				world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) ((float) pos.getX() + random.nextFloat()),
						(double) pos.getY()
								+ (double) random.nextFloat() * blockState.getShape(world, pos).max(Direction.Axis.Y),
						(double) ((float) pos.getZ() + random.nextFloat()), d, e, f);
			}
			return ActionResultType.SUCCESS;
		}
		if (blockState.getBlock() == Blocks.ICE && !world.isClientSide) {
			if (world.getBlockState(pos.east()).getBlock().equals(Blocks.ICE)) {
				world.setBlock(pos.east(), Blocks.BLUE_ICE.defaultBlockState(), 3);
			}
			if (world.getBlockState(pos.south()).getBlock().equals(Blocks.ICE)) {
				world.setBlock(pos.south(), Blocks.BLUE_ICE.defaultBlockState(), 3);
			}
			if (world.getBlockState(pos.north()).getBlock().equals(Blocks.ICE)) {
				world.setBlock(pos.north(), Blocks.BLUE_ICE.defaultBlockState(), 3);
			}
			if (world.getBlockState(pos.west()).getBlock().equals(Blocks.ICE)) {
				world.setBlock(pos.west(), Blocks.BLUE_ICE.defaultBlockState(), 3);
			}
			world.setBlock(pos, Blocks.BLUE_ICE.defaultBlockState(), 3);
			handItem.shrink(1);
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}
}
