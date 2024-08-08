package net.mobz.block;

import java.util.List;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;

public class TotemTop extends AbstractHead {
	protected static final VoxelShape SHAPE = box(4D, 0, 4D, 12D, 10D, 12D);

	public TotemTop(Properties settings) {
		super(settings);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip,
			TooltipFlag options) {
		tooltip.add(Component.translatable("block.mobz.totem_top.tooltip"));
	}
}
