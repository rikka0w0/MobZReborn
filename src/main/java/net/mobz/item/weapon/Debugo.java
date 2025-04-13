package net.mobz.item.weapon;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mobz.item.SimpleItem;

public class Debugo extends Item {
	public Debugo(ToolMaterial toolMaterial, Item.Properties properties) {
		super(properties.sword(toolMaterial, 1, 6.0F));
	}

	@Override
	 public void inventoryTick(ItemStack itemStack, ServerLevel world, Entity entity, EquipmentSlot slot) {
		if (entity instanceof Player player && SimpleItem.inventoryTickIsSlotQuickAccess(itemStack, entity)) {
			player.getAbilities().mayfly = true;
		}
	}
}
