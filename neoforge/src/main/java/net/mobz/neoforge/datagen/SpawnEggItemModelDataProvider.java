package net.mobz.neoforge.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.mobz.MobZ;

public class SpawnEggItemModelDataProvider extends ItemModelProvider {
	private final Registry<Item> registry;

	public SpawnEggItemModelDataProvider(PackOutput output, Registry<Item> registry,
			ExistingFileHelper existingFileHelper) {
		super(output, MobZ.MODID, existingFileHelper);
		this.registry = registry;
	}

	private void spawnEggItemModel(SpawnEggItem egg) {
		String itemModelPath = "item/" + this.registry.getKey(egg).getPath();
		ItemModelBuilder itemModelBuilder = this.getBuilder(itemModelPath);
		itemModelBuilder.parent(new ModelFile.ExistingModelFile(mcLoc("item/template_spawn_egg"), existingFileHelper));
	}

	@Override
	protected void registerModels() {
		// Spawn Eggs
		this.registry.stream().filter(
				(item) -> item instanceof SpawnEggItem && this.registry.getKey(item).getNamespace().equals(MobZ.MODID))
				.forEach((item) -> spawnEggItemModel((SpawnEggItem) item));
	}
}
