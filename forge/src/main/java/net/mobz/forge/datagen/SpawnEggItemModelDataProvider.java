package net.mobz.forge.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mobz.MobZ;

public class SpawnEggItemModelDataProvider extends ItemModelProvider {
	public SpawnEggItemModelDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, MobZ.MODID, existingFileHelper);
	}

	private void spawnEggItemModel(SpawnEggItem egg) {
		String itemModelPath = "item/" + Registry.ITEM.getKey(egg).getPath();
		ItemModelBuilder itemModelBuilder = this.getBuilder(itemModelPath);
		itemModelBuilder.parent(new ModelFile.ExistingModelFile(mcLoc("item/template_spawn_egg"), existingFileHelper));
	}

	@Override
	protected void registerModels() {
		// Spawn Eggs
		Registry.ITEM.stream()
			.filter((item) -> item instanceof SpawnEggItem && Registry.ITEM.getKey(item).getNamespace().equals(MobZ.MODID))
			.forEach((item) -> spawnEggItemModel((SpawnEggItem) item));
	}
}
