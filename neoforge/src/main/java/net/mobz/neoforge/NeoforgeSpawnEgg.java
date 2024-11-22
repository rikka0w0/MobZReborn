package net.mobz.neoforge;

import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

public class NeoforgeSpawnEgg extends DeferredSpawnEggItem {
	public NeoforgeSpawnEgg(Supplier<? extends EntityType<? extends Mob>> entityType, int color1, int color2, Item.Properties itemProps) {
		super(entityType, color1, color2, itemProps);
	}

	@Override
	protected String getOrCreateDescriptionId() {
		return "item.mobz.spawn_egg_of";
	}

	@Override
	public Component getDescription() {
		return Component.translatable("item.mobz.spawn_egg_of", this.getDefaultType().getDescription());
	}

	@Override
	public Component getName(ItemStack itemStack) {
		return getDescription();
	}
}
