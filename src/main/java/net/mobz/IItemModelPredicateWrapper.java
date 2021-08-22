package net.mobz;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

@FunctionalInterface
public interface IItemModelPredicateWrapper {
	void register(Item item, ResourceLocation resourceLocation, ClampedItemPropertyFunction clampedItemPropertyFunction);
}
