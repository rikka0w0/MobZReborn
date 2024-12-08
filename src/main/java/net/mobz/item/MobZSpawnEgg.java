package net.mobz.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;

public class MobZSpawnEgg extends SpawnEggItem {
	// Only used by the data generator!
	public final int backgroundColor, highlightColor;

	public MobZSpawnEgg(EntityType<? extends Mob> entityType, int backgroundColor, int highlightColor, Item.Properties props) {
		super(entityType, props);

		this.backgroundColor = backgroundColor;
		this.highlightColor = highlightColor;
	}

	@Override
	public Component getName(ItemStack stack) {
		return Component.translatable("item.mobz.spawn_egg_of", this.getName());
	}
}
