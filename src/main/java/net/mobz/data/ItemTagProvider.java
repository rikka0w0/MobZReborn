package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.mobz.MobZTagsProvider;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;
import net.mobz.tags.MobZItemTags;

public class ItemTagProvider extends MobZTagsProvider<Item> {
	public ItemTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ITEM, lookupProvider);
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(ItemTags.SWORDS).add(
			key(MobZWeapons.ARMORED_SWORD.get()),
			key(MobZWeapons.BOSS_SWORD.get()),
			key(MobZWeapons.FROZEN_SWORD.get()),
			key(MobZWeapons.POISON_SWORD.get()),
			key(MobZWeapons.RAINBOW_SWORD.get()),
			key(MobZWeapons.WITHER_SWORD.get())
		);

		this.tag(MobZItemTags.FIORA_EQUIP_TAG).add(
			key(Items.SHIELD),
			key(MobZItems.SHIELD.get()),
			key(Items.BLUE_ORCHID),
			key(Items.CORNFLOWER),
			key(Items.WHITE_TULIP),
			key(Items.PINK_TULIP),
			key(Items.RED_TULIP),
			key(Items.ORANGE_TULIP),
			key(Items.ALLIUM),
			key(Items.AZURE_BLUET),
			key(Items.DANDELION),
			key(Items.OXEYE_DAISY),
			key(Items.LILY_OF_THE_VALLEY),
			key(Items.POPPY)
		);

		this.tag(MobZItemTags.FIORA_FOOD_TAG).add(key(Items.MELON_SLICE));

		this.tag(MobZItemTags.FIORA_TAME_TAG).add(key(Items.GOLD_NUGGET));

		this.tag(MobZItemTags.KATHERINE_EQUIP_TAG).add(
			key(Items.SHIELD),
			key(MobZItems.SHIELD.get())
		);

		this.tag(MobZItemTags.KATHERINE_FOOD_TAG).add(key(Items.MELON_SLICE));

		this.tag(MobZItemTags.KATHERINE_TAME_TAG).add(key(Items.GOLD_NUGGET));

		this.tag(MobZItemTags.GOLDEN_CHICKEN_FOOD).add(key(Items.GOLD_NUGGET));

		this.tag(MobZItemTags.TOAD_FOOD_TAG).add(key(Items.SPIDER_EYE));

		// Tool/weapon repair materials
		this.tag(MobZItemTags.BOSS_TOOL_MATERIALS).add(key(MobZItems.BOSS_INGOT.get()));
		this.tag(MobZItemTags.ARMORED_TOOL_MATERIALS);
		this.tag(MobZItemTags.ERAGONS_TOOL_MATERIALS);
		this.tag(MobZItemTags.POISON_TOOL_MATERIALS);
		this.tag(MobZItemTags.RAINBOW_TOOL_MATERIALS).add(key(Items.DRAGON_EGG));
		this.tag(MobZItemTags.WITHER_TOOL_MATERIALS);
		this.tag(MobZItemTags.DEBUG_TOOL_MATERIALS);

		// Armor repair materials
		this.tag(MobZItemTags.REPAIRS_AMAT_ARMOR).add(key(MobZItems.AMAT_INGOT.get()));
		this.tag(MobZItemTags.REPAIRS_BOSS_ARMOR).add(key(MobZItems.BOSS_INGOT.get()));
		this.tag(MobZItemTags.REPAIRS_LIFE_ARMOR).add(key(MobZItems.HARDENEDMETAL_INGOT.get()));
		this.tag(MobZItemTags.REPAIRS_SPEED_ARMOR).add(key(MobZItems.BEAR_LEATHER.get()));
		this.tag(MobZItemTags.REPAIRS_SPEED2_ARMOR).add(key(Items.EMERALD));
	}

	private static ResourceKey<Item> key(Item item) {
		return item.builtInRegistryHolder().key();
	}
}
