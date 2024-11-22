package net.mobz.item.weapon;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class Debugo extends SwordItem {
	public Debugo(ToolMaterial toolMaterial, Item.Properties properties) {
		super(toolMaterial, 1, 6.0F, properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (entity instanceof Player player && slot >= 0 && slot < 9 && !world.isClientSide) {
			player.getAbilities().mayfly = true;
		}
	}
}
