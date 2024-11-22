package net.mobz;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZItems;

public class MobZTabs {
	// ItemGroup
	public final static CreativeModeTab MAIN = MobZ.platform.tab(MobZ.resLoc("glomod"),
			() -> new ItemStack(MobZBlocks.BOSS_TROPHY.get()));

	public final static CreativeModeTab EGGS = MobZ.platform.tab(MobZ.resLoc("glomodegg"),
			() -> new ItemStack(MobZItems.SPAWN_EGG.get()));
}
