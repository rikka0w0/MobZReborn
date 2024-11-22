package net.mobz.data;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.mobz.init.MobZItems;
import net.mobz.tags.MobZItemTags;

public class ItemTagProvider extends TagsProvider<Item> {
	public ItemTagProvider(PackOutput packOutput,
			CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Registries.ITEM, lookupProvider);
	}

	public TagsProvider.TagAppender<Item> tag(TagKey<Item> tagKey,
			Item... items) {
		TagsProvider.TagAppender<Item> appender = this.tag(tagKey);
		for (Item item: items) {
			appender.add(item.builtInRegistryHolder().key());
		}
		return appender;
	}

	@Override
	protected void addTags(Provider pProvider) {
		this.tag(MobZItemTags.FIORA_EQUIP_TAG,
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

		this.tag(MobZItemTags.FIORA_FOOD_TAG, Items.MELON_SLICE);

		this.tag(MobZItemTags.FIORA_TAME_TAG, Items.GOLD_NUGGET);

		this.tag(MobZItemTags.KATHERINE_EQUIP_TAG,
			Items.SHIELD,
			MobZItems.SHIELD.get()
		);

		this.tag(MobZItemTags.KATHERINE_FOOD_TAG, Items.MELON_SLICE);

		this.tag(MobZItemTags.KATHERINE_TAME_TAG, Items.GOLD_NUGGET);

		this.tag(MobZItemTags.GOLDEN_CHICKEN_FOOD, Items.GOLD_NUGGET);

		this.tag(MobZItemTags.TOAD_FOOD_TAG, Items.SPIDER_EYE);

		// Tool/weapon repair materials
		this.tag(MobZItemTags.BOSS_TOOL_MATERIALS, MobZItems.BOSS_INGOT.get());
		this.tag(MobZItemTags.ARMORED_TOOL_MATERIALS);
		this.tag(MobZItemTags.ERAGONS_TOOL_MATERIALS);
		this.tag(MobZItemTags.POISON_TOOL_MATERIALS);
		this.tag(MobZItemTags.RAINBOW_TOOL_MATERIALS, Items.DRAGON_EGG);
		this.tag(MobZItemTags.WITHER_TOOL_MATERIALS);
		this.tag(MobZItemTags.DEBUG_TOOL_MATERIALS);

		// Armor repair materials
		this.tag(MobZItemTags.REPAIRS_AMAT_ARMOR, MobZItems.AMAT_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_BOSS_ARMOR, MobZItems.BOSS_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_LIFE_ARMOR, MobZItems.HARDENEDMETAL_INGOT.get());
		this.tag(MobZItemTags.REPAIRS_SPEED_ARMOR, MobZItems.BEAR_LEATHER.get());
		this.tag(MobZItemTags.REPAIRS_SPEED2_ARMOR, Items.EMERALD);
	}
}
