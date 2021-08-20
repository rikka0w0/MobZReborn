package net.mobz.item;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class WitherMeal extends SimpleItem {
	public WitherMeal(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
        Player player = context.getPlayer();
        ItemStack mealitem = player.getMainHandItem();
		BlockPos pos = context.getClickedPos();
		BlockState blockState = world.getBlockState(pos);

        if (blockState.getBlock() == Blocks.SOUL_SAND && !world.isClientSide) {
            Random random = new Random();
            int randomNumber = (random.nextInt() % 2);
            if (randomNumber < 0) {
                randomNumber = randomNumber * (-1);
            }
            if (world.getBlockState(pos.east()).getBlock().equals(Blocks.SOUL_SAND) && randomNumber == 0) {
                world.setBlock(pos.east().above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.south()).getBlock().equals(Blocks.SOUL_SAND) && randomNumber == 1) {
                world.setBlock(pos.south().above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.north()).getBlock().equals(Blocks.SOUL_SAND) && randomNumber == 0) {
                world.setBlock(pos.north().above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.west()).getBlock().equals(Blocks.SOUL_SAND) && randomNumber == 1) {
                world.setBlock(pos.west().above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            }
            world.setBlock(pos.above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            mealitem.shrink(1);
            return InteractionResult.SUCCESS;
        }

        if (((blockState.getBlock() == Blocks.SOUL_SAND || blockState.getBlock() == Blocks.FARMLAND)
                && world.isClientSide)) {
            for (int i = 0; i < 12; ++i) {
                double d = random.nextGaussian() * 0.02D;
                double e = random.nextGaussian() * 0.02D;
                double f = random.nextGaussian() * 0.02D;
                world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) ((float) pos.getX() + random.nextFloat()),
                        (double) pos.getY() + (double) random.nextFloat()
                                * blockState.getShape(world, pos).max(Direction.Axis.Y) + 1,
                        (double) ((float) pos.getZ() + random.nextFloat()), d, e, f);
            }
            return InteractionResult.SUCCESS;
        }

        if (blockState.getBlock() == Blocks.FARMLAND && !world.isClientSide) {
            if (world.getBlockState(pos.east()).getBlock().equals(Blocks.FARMLAND)) {
                world.setBlock(pos.east(), Blocks.SOUL_SAND.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.south()).getBlock().equals(Blocks.FARMLAND)) {
                world.setBlock(pos.south(), Blocks.SOUL_SAND.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.north()).getBlock().equals(Blocks.FARMLAND)) {
                world.setBlock(pos.north(), Blocks.SOUL_SAND.defaultBlockState(), 3);
            }
            if (world.getBlockState(pos.west()).getBlock().equals(Blocks.FARMLAND)) {
                world.setBlock(pos.west(), Blocks.SOUL_SAND.defaultBlockState(), 3);
            }
            world.setBlock(pos.above(), Blocks.WITHER_ROSE.defaultBlockState(), 3);
            world.setBlock(pos, Blocks.SOUL_SAND.defaultBlockState(), 3);
            mealitem.shrink(1);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}