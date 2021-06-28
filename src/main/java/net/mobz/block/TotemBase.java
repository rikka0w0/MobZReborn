package net.mobz.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

public class TotemBase extends AbstractHead {
	protected static final VoxelShape SHAPE = VoxelShapes.or(
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
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader world, List<ITextComponent> tooltip,
			ITooltipFlag options) {
		tooltip.add(new TranslationTextComponent("block.mobz.totembase.tooltip"));
	}
}
