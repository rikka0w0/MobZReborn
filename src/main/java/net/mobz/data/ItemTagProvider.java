package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.mobz.init.MobZItems;
import net.mobz.tags.MobZItemTags;

public class ItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
	public ItemTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ITEM, lookupProvider, item -> item.builtInRegistryHolder().key());
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(MobZItemTags.FIORA_EQUIP_TAG).add(
			Items.SHIELD,
			MobZItems.SHIELD.get(),
			Items.BLUE_ORCHID,
			Items.CORNFLOWER,
			Items.WHITE_TULIP,
			Items.PINK_TULIP,
			Items.RED_TULIP,
			Items.ORANGE_TULIP,
			Items.ALLIUM,
			Items.AZURE_BLUET,
			Items.DANDELION,
			Items.OXEYE_DAISY,
			Items.LILY_OF_THE_VALLEY,
			Items.POPPY
		);

		this.tag(MobZItemTags.FIORA_FOOD_TAG).add(Items.MELON_SLICE);

		this.tag(MobZItemTags.FIORA_TAME_TAG).add(Items.GOLD_NUGGET);

		this.tag(MobZItemTags.KATHERINE_EQUIP_TAG).add(
			Items.SHIELD,
			MobZItems.SHIELD.get()
		);

		this.tag(MobZItemTags.KATHERINE_FOOD_TAG).add(Items.MELON_SLICE);

		this.tag(MobZItemTags.KATHERINE_TAME_TAG).add(Items.GOLD_NUGGET);

		this.tag(MobZItemTags.GOLDEN_CHICKEN_FOOD).add(Items.GOLD_NUGGET);

		this.tag(MobZItemTags.TOAD_FOOD_TAG).add(Items.SPIDER_EYE);

		// Tool/weapon repair materials
		this.tag(MobZItemTags.BOSS_TOOL_MATERIALS).add(MobZItems.BOSS_INGOT.get());
		this.tag(MobZItemTags.ARMORED_TOOL_MATERIALS);
		this.tag(MobZItemTags.ERAGONS_TOOL_MATERIALS);
		this.tag(MobZItemTags.POISON_TOOL_MATERIALS);
		this.tag(MobZItemTags.RAINBOW_TOOL_MATERIALS).add(Items.DRAGON_EGG);
		this.tag(MobZItemTags.WITHER_TOOL_MATERIALS);
		this.tag(MobZItemTags.DEBUG_TOOL_MATERIALS);

		// Armor repair materials
		this.tag(MobZItemTags.REPAIRS_AMAT_ARMOR).add(MobZItems.AMAT_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_BOSS_ARMOR).add(MobZItems.BOSS_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_LIFE_ARMOR).add(MobZItems.HARDENEDMETAL_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_SPEED_ARMOR).add(MobZItems.BEAR_LEATHER.get());
		this.tag(MobZItemTags.REPAIRS_SPEED2_ARMOR).add(Items.EMERALD);
	}
}
