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
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;

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
		simpleItemModel(MobZItems.BEARLEATHER.get());
		simpleItemModel(MobZItems.BOSS_INGOT.get());
		simpleItemModel(MobZItems.FROZENMEAL.get());
		simpleItemModel(MobZItems.HARDENEDMETAL_INGOT.get());

		simpleItemModel(MobZItems.ORB.get());
		simpleItemModel(MobZItems.ORB_2.get());
		simpleItemModel(MobZItems.ROTTENFLESH.get());
		this.sacrificeKnifeSubTexture();

		this.lilithBowSubTexture();
		simpleItemModel(MobZItems.SEALITEM.get());
		simpleItemModel(MobZItems.SHOWEGG.get());
		simpleItemModel(MobZItems.WHITEBAG.get());

		simpleItemModel(MobZItems.WITHERMEAL.get());
		simpleItemModel(MobZItems.MEDIVEAL_DISC.get());
		simpleItemModel(MobZItems.MEDIVEAL_DISC2.get());
	}
}
