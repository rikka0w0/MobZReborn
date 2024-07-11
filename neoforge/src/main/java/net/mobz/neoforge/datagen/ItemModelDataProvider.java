package net.mobz.neoforge.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class ItemModelDataProvider extends ItemModelProvider {
	private final Registry<Item> registry;

	public ItemModelDataProvider(PackOutput output, Registry<Item> registry,
			ExistingFileHelper existingFileHelper) {
		super(output, MobZ.MODID, existingFileHelper);
		this.registry = registry;
	}

	private void spawnEggItemModel(SpawnEggItem egg) {
		String itemModelPath = "item/" + this.registry.getKey(egg).getPath();
		ItemModelBuilder itemModelBuilder = this.getBuilder(itemModelPath);
		itemModelBuilder.parent(new ModelFile.ExistingModelFile(mcLoc("item/template_spawn_egg"), existingFileHelper));
	}

	private void lilithBowSubTexture() {
		for (int i = 0; i<=2; i++) {
			simpleItemModel("lilith_bow_pulling_" + i);
			simpleItemModel("lilith_bow_pulling_" + i);
			simpleItemModel("lilith_bow_pulling_" + i);
		}
	}

	private void sacrificeKnifeSubTexture() {
		for (int i = 1; i<=4; i++) {
			simpleItemModel("sacrifice_knife_blood" + i);
			simpleItemModel("sacrifice_knife_blood" + i + "dry1");
			simpleItemModel("sacrifice_knife_blood" + i + "dry2");
		}
	}

	private void simpleItemModel(Item item) {
		ResourceLocation resLoc = this.registry.getKey(item);
		simpleItemModel(resLoc.getPath());
	}

	private void simpleItemModel(String itemName) {
		String itemModelPath = "item/" + itemName;
		ItemModelBuilder itemModelBuilder = this.getBuilder(itemModelPath);
		itemModelBuilder.texture("layer0", itemModelPath);
		itemModelBuilder.parent(new ModelFile.ExistingModelFile(mcLoc("item/generated"), existingFileHelper));
	}

	@Override
	protected void registerModels() {
		// Spawn Eggs
		this.registry.stream().filter(
				(item) -> item instanceof SpawnEggItem && this.registry.getKey(item).getNamespace().equals(MobZ.MODID))
				.forEach((item) -> spawnEggItemModel((SpawnEggItem) item));

		// Advancement Icons
		for (String headName: MobZIcons.headNames) {
			simpleItemModel(headName);
		}

		simpleItemModel(MobZItems.AMAT_INGOT.get());
		simpleItemModel(MobZItems.BEAR_LEATHER.get());
		simpleItemModel(MobZItems.BOSS_INGOT.get());
		simpleItemModel(MobZItems.FROZEN_POWDER.get());
		simpleItemModel(MobZItems.HARDENEDMETAL_INGOT.get());

		simpleItemModel(MobZItems.IMMUNITY_ORB.get());
		simpleItemModel(MobZItems.LEVITATION_ORB.get());
		simpleItemModel(MobZItems.ROTTEN_FLESH.get());
		this.sacrificeKnifeSubTexture();

		this.lilithBowSubTexture();
		simpleItemModel(MobZItems.SEAL_KEY.get());
		simpleItemModel(MobZItems.SPAWN_EGG.get());
		simpleItemModel(MobZItems.WHITE_BAG.get());

		simpleItemModel(MobZItems.WITHER_POWDER.get());
		simpleItemModel(MobZItems.MEDIVEAL_DISC.get());
		simpleItemModel(MobZItems.MEDIVEAL_DISC2.get());

		// Weapon
		simpleItemModel(MobZWeapons.ARMORED_SWORD.get());
		simpleItemModel(MobZWeapons.BOSS_SWORD.get());
		simpleItemModel(MobZWeapons.POISON_SWORD.get());
		simpleItemModel(MobZWeapons.RAINBOW_SWORD.get());
		simpleItemModel(MobZWeapons.STONE_TOMAHAWK.get());

		// Armor
		simpleItemModel(MobZArmors.AMAT_HELMET.get());
		simpleItemModel(MobZArmors.AMAT_CHESTPLATE.get());
		simpleItemModel(MobZArmors.AMAT_LEGGINGS.get());
		simpleItemModel(MobZArmors.AMAT_BOOTS.get());
		simpleItemModel(MobZArmors.BOSS_HELMET.get());
		simpleItemModel(MobZArmors.BOSS_CHESTPLATE.get());
		simpleItemModel(MobZArmors.BOSS_LEGGINGS.get());
		simpleItemModel(MobZArmors.BOSS_BOOTS.get());
		simpleItemModel(MobZArmors.LIFE_HELMET.get());
		simpleItemModel(MobZArmors.LIFE_CHESTPLATE.get());
		simpleItemModel(MobZArmors.LIFE_LEGGINGS.get());
		simpleItemModel(MobZArmors.LIFE_BOOTS.get());
		simpleItemModel(MobZArmors.SPEED_BOOTS.get());
		simpleItemModel(MobZArmors.SPEED2_BOOTS.get());
	}
}
