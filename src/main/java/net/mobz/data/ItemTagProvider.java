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

import net.mobz.MobZ;
import net.mobz.init.MobZItems;

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
		this.tag(MobZ.FIORA_EQUIP_TAG,
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

		this.tag(MobZ.FIORA_FOOD_TAG, Items.MELON_SLICE);

		this.tag(MobZ.FIORA_TAME_TAG, Items.GOLD_NUGGET);

		this.tag(MobZ.KATHERINE_EQUIP_TAG,
			Items.SHIELD,
			MobZItems.SHIELD.get()
		);

		this.tag(MobZ.KATHERINE_FOOD_TAG, Items.MELON_SLICE);

		this.tag(MobZ.KATHERINE_TAME_TAG, Items.GOLD_NUGGET);

		this.tag(MobZ.TOAD_FOOD_TAG, Items.SPIDER_EYE);
	}
}
