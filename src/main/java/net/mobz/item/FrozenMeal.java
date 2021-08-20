package net.mobz.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class FrozenMeal extends SimpleItem {
	public FrozenMeal(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		Player player = context.getPlayer();
		ItemStack handItem = player.getMainHandItem();
		BlockPos pos = context.getClickedPos();
		BlockState blockState = world.getBlockState(pos);
		
		BlockHitResult blockHitResult = getPlayerPOVHitResult(world, player,
				ClipContext.Fluid.SOURCE_ONLY);
		BlockPos blockPos = blockHitResult.getBlockPos();
		BlockState blockBlock = world.getBlockState(blockPos);

		if (blockBlock.getBlock() instanceof BucketPickup && !world.isClientSide) {
			world.setBlock(blockPos, Blocks.ICE.defaultBlockState(), 3);
			handItem.shrink(1);
			return InteractionResult.SUCCESS;
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
			return InteractionResult.SUCCESS;
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
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}
}
