package net.mobz.portable;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabBuilder {
	public static CreativeModeTab of(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		return new CreativeModeTab(resLoc.getNamespace() + "." + resLoc.getPath()) {
			public ItemStack makeIcon() {
				return iconSupplier.get();
			}
		};
	}
}
