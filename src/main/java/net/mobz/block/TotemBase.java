package net.mobz.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.ticks.TickPriority;
import net.minecraft.server.level.ServerLevel;
import net.mobz.entity.PillagerBoss;
import net.mobz.init.MobZEntities;

public class TotemBase extends Block {
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	protected static final VoxelShape SHAPE = Shapes.or(
			box(1D, 0, 1D, 15D, 1D, 15D), box(2D, 1D, 2D, 14D, 2D, 14D),
	        box(4D, 2D, 4D, 12D, 16D, 12D), box(3D, 5D, 4D, 4D, 8D, 5D),
	        box(3D, 5D, 11D, 4D, 8D, 12D), box(2D, 6D, 4D, 3D, 9D, 5D),
	        box(1D, 8D, 4D, 2D, 11D, 5D), box(2D, 6D, 11D, 3D, 9D, 12D),
	        box(1D, 8D, 11D, 2D, 11D, 12D), box(4D, 5D, 3D, 5D, 8D, 4D),
	        box(4D, 6D, 2D, 5D, 9D, 3D), box(4D, 8D, 1D, 5D, 11D, 2D),
	        box(11D, 5D, 3D, 12D, 8D, 4D), box(11D, 6D, 2D, 12D, 9D, 3D),
	        box(11D, 8D, 1D, 12D, 11D, 2D), box(11D, 5D, 12D, 12D, 8D, 13D),
	        box(11D, 6D, 13D, 12D, 9D, 14D), box(11D, 8D, 14D, 12D, 11D, 15D),
	        box(4D, 5D, 12D, 5D, 8D, 13D), box(4D, 6D, 13D, 5D, 9D, 14D),
	        box(4D, 8D, 14D, 5D, 11D, 15D), box(12D, 5D, 11D, 13D, 8D, 12D),
	        box(13D, 6D, 11D, 14D, 9D, 12D), box(14D, 8D, 11D, 15D, 11D, 12D),
	        box(12D, 5D, 4D, 13D, 8D, 5D), box(13D, 6D, 4D, 14D, 9D, 5D),
	        box(14D, 8D, 4D, 15D, 11D, 5D), box(4D, 14D, 3D, 5D, 16D, 4D),
	        box(11D, 14D, 3D, 12D, 16D, 4D), box(12D, 14D, 4D, 13D, 16D, 5D),
	        box(12D, 14D, 11D, 13D, 16D, 12D), box(11D, 14D, 12D, 12D, 16D, 13D),
	        box(4D, 14D, 12D, 5D, 16D, 13D), box(3D, 14D, 11D, 4D, 16D, 12D),
	        box(3D, 14D, 4D, 4D, 16D, 5D), box(3D, 15D, 3D, 4D, 16D, 4D),
	        box(12D, 15D, 3D, 13D, 16D, 4D), box(12D, 15D, 12D, 13D, 16D, 13D),
	        box(3D, 15D, 12D, 4D, 16D, 13D));

	public TotemBase(Properties settings) {
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(ENABLED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ENABLED);
	}

	public void trigger(Level world, BlockPos pos) {
		world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(TotemBase.ENABLED, true));
		world.scheduleTick(pos, this, 100, TickPriority.HIGH);
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (state.getValue(TotemBase.ENABLED)) {
			world.removeBlock(pos, false);
			world.removeBlock(pos.above(), false);
			world.removeBlock(pos.above().above(), false);

			PillagerBoss pillager = (PillagerBoss) MobZEntities.PILLAGER_BOSS.get().create(world);
			BlockPos spawnPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
			pillager.moveTo(spawnPos, 0.0F, 0.0F);
			world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.HOSTILE, 1F, 1F);
			world.addFreshEntity(pillager);
		}
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		if (state.getValue(ENABLED)) {
			for (int i=0; i<10; i++) {
		        double d = (double) pos.getX() + (double) rand.nextFloat();
		        double e = (double) pos.getY() + (double) rand.nextFloat();
		        double f = (double) pos.getZ() + (double) rand.nextFloat();
		        double o = (double) pos.getY() + ((double) rand.nextFloat() + 1);
		        double r = (double) pos.getY() - ((double) rand.nextFloat());
		        world.addParticle(ParticleTypes.ASH, d, e, f, 0.0D, 0.0D, 0.0D);
		        world.addParticle(ParticleTypes.SMOKE, d, o, f, 0.0D, 0.0D, 0.0D);
		        world.addParticle(ParticleTypes.SMOKE, d, r, f, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter world, List<Component> tooltip,
			TooltipFlag options) {
		tooltip.add(Component.translatable("block.mobz.totembase.tooltip"));
	}
}
