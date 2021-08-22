package net.mobz.portable;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabBuilder {
	public static CreativeModeTab of(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		return FabricItemGroupBuilder.build(resLoc, iconSupplier);
	}
}
