package net.mobz;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mobz.init.MobSpawnRestrictions;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;
import net.mobz.init.MobZWeapons;
import net.mobz.portable.CreativeTabBuilder;

public class MobZ {
	public static final String MODID = "mobz";

	// ItemGroup
	public final static CreativeModeTab tab = CreativeTabBuilder.of(new ResourceLocation(MODID, "glomod"),
					() -> new ItemStack(MobZArmors.boss_helmet));

	public final static CreativeModeTab eggs = CreativeTabBuilder.of(new ResourceLocation(MODID, "glomodegg"),
			() -> new ItemStack(MobZItems.SHOWEGG));

	public static void registerAll(IRegistryWrapper regWrapper, IEntitySpawnPlacementWrapper spawnRestrictionAdder) {
    	MobZItems.registerAll(regWrapper);
    	MobZBlocks.registerAll(regWrapper);
    	MobZEntities.registerAll(regWrapper);
    	MobZArmors.registerAll(regWrapper);
    	MobZWeapons.registerAll(regWrapper);
    	MobZSounds.registerAll(regWrapper);
    	MobZIcons.registerAll(regWrapper);

    	MobSpawnRestrictions.applyAll(spawnRestrictionAdder);
	}
}
