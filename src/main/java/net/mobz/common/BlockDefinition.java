package net.mobz.common;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockDefinition<T extends Block> implements IRegistrable {
	public final String name;
	public final T block;
	public final BlockItem blockItem;

	public BlockDefinition(String name, T block, BlockItem blockItem) {
		this.name = name;
		this.block = block;
		this.blockItem = blockItem;
	}

	public BlockDefinition(String name, T block, Item.Properties itemProps) {
		this(name, block, new BlockItem(block, new Item.Properties()));
	}
	
	public BlockDefinition(String name, T block, ItemGroup tab) {
		this(name, block, new BlockItem(block, new Item.Properties().tab(tab)));
	}

	@Override
	public String Mobz$getRegistryName() {
		return name;
	}
}
