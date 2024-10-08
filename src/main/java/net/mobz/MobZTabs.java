package net.mobz;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZItems;

public class MobZTabs {
	// ItemGroup
	public final static CreativeModeTab tab = MobZ.platform.tab(
			ResourceLocation.tryBuild(MobZ.MODID, "glomod"), () -> new ItemStack(MobZBlocks.BOSS_TROPHY.get()));

	public final static CreativeModeTab eggs = MobZ.platform.tab(
			ResourceLocation.tryBuild(MobZ.MODID, "glomodegg"), () -> new ItemStack(MobZItems.SPAWN_EGG.get()));
}
