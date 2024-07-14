package net.mobz.data;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class ItemModelDataProvider implements DataProvider {
	private final PackOutput.PathProvider modelPathProvider;
	private final Registry<Item> itemRegistry;
	private final Predicate<ResourceLocation> existenceChecker;

	public ItemModelDataProvider(PackOutput packOutput, Registry<Item> itemRegistry, @Nullable Predicate<ResourceLocation> existenceChecker) {
		this.modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
		this.itemRegistry = itemRegistry;
		this.existenceChecker = (existenceChecker == null) ? resLoc->true : existenceChecker;
	}

	@Override
	public String getName() {
		return "Item models for " + MobZ.MODID;
	}

	private void collect(Builder builder) {
		// Spawn Eggs
		this.itemRegistry.stream()
			.filter((item) -> this.itemRegistry.getKey(item).getNamespace().equals(MobZ.MODID))
			.filter((item) -> item instanceof SpawnEggItem)
			.forEach((item) -> builder.spawnEgg((SpawnEggItem) item));

		// Advancement Icons
		for (String headName: MobZIcons.headNames) {
			builder.simpleItem(headName);
		}

		// Items
		builder.simpleItem(MobZItems.AMAT_INGOT.get());
		builder.simpleItem(MobZItems.BEAR_LEATHER.get());
		builder.simpleItem(MobZItems.BOSS_INGOT.get());
		builder.simpleItem(MobZItems.FROZEN_POWDER.get());
		builder.simpleItem(MobZItems.HARDENEDMETAL_INGOT.get());

		builder.simpleItem(MobZItems.IMMUNITY_ORB.get());
		builder.simpleItem(MobZItems.LEVITATION_ORB.get());
		builder.simpleItem(MobZItems.ROTTEN_FLESH.get());
		for (int i = 1; i<=4; i++) {
			builder.simpleItem("sacrifice_knife_blood" + i);
			builder.simpleItem("sacrifice_knife_blood" + i + "dry1");
			builder.simpleItem("sacrifice_knife_blood" + i + "dry2");
		}

		for (int i = 0; i<=2; i++) {
			builder.simpleItem("lilith_bow_pulling_" + i);
		}
		builder.simpleItem(MobZItems.SEAL_KEY.get());
		builder.simpleItem(MobZItems.SPAWN_EGG.get());
		builder.simpleItem(MobZItems.WHITE_BAG.get());

		builder.simpleItem(MobZItems.WITHER_POWDER.get());
		builder.simpleItem(MobZItems.MEDIVEAL_DISC.get());
		builder.simpleItem(MobZItems.MEDIVEAL_DISC2.get());

		// Weapon
		builder.simpleItem(MobZWeapons.ARMORED_SWORD.get());
		builder.simpleItem(MobZWeapons.BOSS_SWORD.get());
		builder.simpleItem(MobZWeapons.POISON_SWORD.get());
		builder.simpleItem(MobZWeapons.RAINBOW_SWORD.get());
		builder.simpleItem(MobZWeapons.STONE_TOMAHAWK.get());

		// Armor
		builder.simpleItem(MobZArmors.AMAT_HELMET.get());
		builder.simpleItem(MobZArmors.AMAT_CHESTPLATE.get());
		builder.simpleItem(MobZArmors.AMAT_LEGGINGS.get());
		builder.simpleItem(MobZArmors.AMAT_BOOTS.get());
		builder.simpleItem(MobZArmors.BOSS_HELMET.get());
		builder.simpleItem(MobZArmors.BOSS_CHESTPLATE.get());
		builder.simpleItem(MobZArmors.BOSS_LEGGINGS.get());
		builder.simpleItem(MobZArmors.BOSS_BOOTS.get());
		builder.simpleItem(MobZArmors.LIFE_HELMET.get());
		builder.simpleItem(MobZArmors.LIFE_CHESTPLATE.get());
		builder.simpleItem(MobZArmors.LIFE_LEGGINGS.get());
		builder.simpleItem(MobZArmors.LIFE_BOOTS.get());
		builder.simpleItem(MobZArmors.SPEED_BOOTS.get());
		builder.simpleItem(MobZArmors.SPEED2_BOOTS.get());
	}

	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {
        Map<ResourceLocation, Supplier<JsonElement>> cachedOutput = Maps.<ResourceLocation, Supplier<JsonElement>>newHashMap();
		BiConsumer<ResourceLocation, Supplier<JsonElement>> outputConsumer = (resourceLocation, supplier) -> {
			Supplier<JsonElement> supplier2 = cachedOutput.put(resourceLocation, supplier);
			if (supplier2 != null) {
				throw new IllegalStateException("Duplicate model definition for " + resourceLocation);
			}
		};

		Builder builder = new Builder(outputConsumer, this.existenceChecker);

		this.collect(builder);

		return saveCollection(pOutput, cachedOutput, this.modelPathProvider::json);
	}

	private <T> CompletableFuture<?> saveCollection(CachedOutput cachedOutput, Map<T, ? extends Supplier<JsonElement>> map, Function<T, Path> function) {
		return CompletableFuture.allOf(map.entrySet().stream().map(entry -> {
			Path path = function.apply(entry.getKey());
			JsonElement jsonElement = (entry.getValue()).get();
			return DataProvider.saveStable(cachedOutput, jsonElement, path);
		}).toArray(CompletableFuture[]::new));
	}

	public static class Builder {
		private final BiConsumer<ResourceLocation, Supplier<JsonElement>> outputConsumer;
		private final Predicate<ResourceLocation> existenceChecker;

		public Builder(BiConsumer<ResourceLocation, Supplier<JsonElement>> outputConsumer,
				Predicate<ResourceLocation> existenceChecker) {
			this.outputConsumer = outputConsumer;
			this.existenceChecker = existenceChecker;
		}

		public void simpleItem(ResourceLocation modelLoc, ResourceLocation textureLoc) {
			ResourceLocation textureRealLoc = new ResourceLocation(textureLoc.getNamespace(),
					"textures/" + textureLoc.getPath() + ".png");
			if (!existenceChecker.test(textureRealLoc)) {
				System.out.println("MobZ ItemModelDataProvider cannot find texture: " + textureRealLoc);
			}

			TextureMapping textureMapping = TextureMapping.layer0(textureLoc);
			ModelTemplates.FLAT_ITEM.create(modelLoc, textureMapping, this.outputConsumer);
		}

		public void simpleItem(Item item, ResourceLocation textureLoc) {
			this.simpleItem(ModelLocationUtils.getModelLocation(item), textureLoc);
		}

		public void simpleItem(Item item) {
			this.simpleItem(item, TextureMapping.getItemTexture(item));
		}

		public void simpleItem(String string) {
			ResourceLocation resLoc = new ResourceLocation(MobZ.MODID, "item/" + string);
			this.simpleItem(resLoc, resLoc);
		}

		public void spawnEgg(SpawnEggItem egg) {
		    JsonObject root = new JsonObject();
		    root.addProperty("parent", "minecraft:item/template_spawn_egg");
			this.outputConsumer.accept(ModelLocationUtils.getModelLocation(egg), ()->root);
		}
	}
}
