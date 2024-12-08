package net.mobz.data;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty;
import net.minecraft.client.renderer.item.properties.numeric.Damage;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZIcons;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;
import net.mobz.item.MobZSpawnEgg;
import net.mobz.item.SacrificeKnife;

public class ModelDataProvider implements DataProvider {
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider itemInfoPathProvider;
	private final PackOutput.PathProvider modelPathProvider;
	private final Registry<Item> itemRegistry;
	private final Predicate<ResourceLocation> existenceChecker;

	protected Map<ResourceLocation, ModelInstance> models = new HashMap<>();
	protected Map<Item, ClientItem> itemStateMap = new HashMap<>();
	protected Map<Block, BlockStateGenerator> blockStateMap = new HashMap<>();

	public ModelDataProvider(PackOutput packOutput, Registry<Item> itemRegistry, @Nullable Predicate<ResourceLocation> existenceChecker) {
		this.blockStatePathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
		this.itemInfoPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
		this.modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
		this.itemRegistry = itemRegistry;
		this.existenceChecker = (existenceChecker == null) ? resLoc -> true : existenceChecker;
	}

	@Override
	public String getName() {
		return "Item models for " + MobZ.MODID;
	}

	protected void collect() {
		// Spawn Eggs
		this.itemRegistry.stream()
			.filter((item) -> this.itemRegistry.getKey(item).getNamespace().equals(MobZ.MODID))
			.filter((item) -> item instanceof MobZSpawnEgg)
			.map((item) -> (MobZSpawnEgg)item)
			.forEach(this::spawnEggMobZ);

		// Advancement Icons
		for (String headName: MobZIcons.headNames) {
			ResourceLocation itemResLoc = MobZ.resLoc(headName);
			Item headItem = this.itemRegistry.getValue(itemResLoc);
			this.simpleItem(headItem);
		}

		// Items
		this.simpleItem(MobZItems.AMAT_INGOT.get());
		this.simpleItem(MobZItems.BEAR_LEATHER.get());
		this.simpleItem(MobZItems.BOSS_INGOT.get());
		this.simpleItem(MobZItems.FROZEN_POWDER.get());
		this.simpleItem(MobZItems.HARDENEDMETAL_INGOT.get());

		this.simpleItem(MobZItems.IMMUNITY_ORB.get());
		this.simpleItem(MobZItems.LEVITATION_ORB.get());
		this.simpleItemWithExistingModel(MobZItems.PILLAGER_STAFF.get());
		this.simpleItem(MobZItems.ROTTEN_FLESH.get());
		this.sacrificeKnife(MobZItems.SACRIFICE_KNIFE.get());

		this.vanillaBow(MobZItems.LILITH_BOW.get());
		this.simpleItem(MobZItems.SEAL_KEY.get());
		this.shieldWithExistingModel(MobZItems.SHIELD.get());
		this.simpleItem(MobZItems.SPAWN_EGG.get());
		this.simpleItem(MobZItems.WHITE_BAG.get());

		this.simpleItem(MobZItems.WITHER_POWDER.get());
		this.simpleItem(MobZItems.MEDIVEAL_DISC.get());
		this.simpleItem(MobZItems.MEDIVEAL_DISC_2.get());
		this.simpleItem(MobZItems.TADPOLE_BUCKET.get());

		// Weapon
		this.simpleItemWithExistingModel(MobZWeapons.ERAGONS_AXE.get());
		this.handheldItem(MobZWeapons.ARMORED_SWORD.get());
		this.handheldItem(MobZWeapons.BOSS_SWORD.get());
		this.simpleItemWithExistingModel(MobZWeapons.FROZEN_SWORD.get());
		this.handheldItem(MobZWeapons.POISON_SWORD.get());

		this.handheldItem(MobZWeapons.RAINBOW_SWORD.get());
		this.handheldItem(MobZWeapons.STONE_TOMAHAWK.get());
		this.simpleItemWithExistingModel(MobZWeapons.WITHER_SWORD.get());

		// Armor
		this.simpleItem(MobZArmors.AMAT_HELMET.get());
		this.simpleItem(MobZArmors.AMAT_CHESTPLATE.get());
		this.simpleItem(MobZArmors.AMAT_LEGGINGS.get());
		this.simpleItem(MobZArmors.AMAT_BOOTS.get());
		this.simpleItem(MobZArmors.BOSS_HELMET.get());
		this.simpleItem(MobZArmors.BOSS_CHESTPLATE.get());
		this.simpleItem(MobZArmors.BOSS_LEGGINGS.get());
		this.simpleItem(MobZArmors.BOSS_BOOTS.get());
		this.simpleItem(MobZArmors.LIFE_HELMET.get());
		this.simpleItem(MobZArmors.LIFE_CHESTPLATE.get());
		this.simpleItem(MobZArmors.LIFE_LEGGINGS.get());
		this.simpleItem(MobZArmors.LIFE_BOOTS.get());
		this.simpleItem(MobZArmors.SPEED_BOOTS.get());
		this.simpleItem(MobZArmors.SPEED2_BOOTS.get());

		// Blocks
		this.cubeAll(MobZBlocks.AMAT_BLOCK.get());
		this.cubeAll(MobZBlocks.BOSS_BLOCK.get());
		this.blockItem(MobZBlocks.BOSS_TROPHY.get());
		this.blockItem(MobZBlocks.ENDER_HEADER.get());
		this.cubeAll(MobZBlocks.HARDENED_METAL_BLOCK.get());

		this.blockItem(MobZBlocks.TOTEM_BASE.get());
		this.blockItem(MobZBlocks.TOTEM_MIDDLE.get());
		this.blockItem(MobZBlocks.TOTEM_TOP.get());
	}

	protected void addItemModel(ResourceLocation modelPath, ModelInstance model) {
		if (models.put(modelPath, model) != null) {
			throw new IllegalStateException("Duplicate model definition for " + modelPath);
		}
	}

	protected void addItemModelInfo(Item item, ItemModel.Unbaked unbakedItemModel) {
    	ClientItem clientModel = new ClientItem(unbakedItemModel, ClientItem.Properties.DEFAULT);
        if (itemStateMap.put(item, clientModel) != null) {
            throw new IllegalStateException("Duplicate item model definition for " + item);
        }
	}

	protected void addBlockState(BlockStateGenerator blockgen) {
        Block block = blockgen.getBlock();
        if (blockStateMap.put(block, blockgen) != null) {
            throw new IllegalStateException("Duplicate blockstate definition for " + block);
        }
	}

	@SuppressWarnings("deprecation")
	protected Path itemStateToPath(Item item) {
		return this.itemInfoPathProvider.json(item.builtInRegistryHolder().key().location());
	}

	@SuppressWarnings("deprecation")
	protected Path blockStateToPath(Block block) {
		return this.blockStatePathProvider.json(block.builtInRegistryHolder().key().location());
	}

	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {
		this.models.clear();
		this.itemStateMap.clear();
		this.blockStateMap.clear();

		// Populates the above three maps
		this.collect();

		return CompletableFuture.allOf(
				DataProvider.saveAll(pOutput, Supplier::get, this.modelPathProvider::json, this.models),
				DataProvider.saveAll(pOutput, ClientItem.CODEC, this::itemStateToPath, this.itemStateMap),
				DataProvider.saveAll(pOutput, Supplier::get, this::blockStateToPath, this.blockStateMap)
			);
	}

	public static ResourceLocation getRealTextureLoc(ResourceLocation resLoc) {
		return ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "textures/" + resLoc.getPath() + ".png");
	}

	protected ResourceLocation getRealTextureLocWithCheck(ResourceLocation resLoc) {
		ResourceLocation realLoc = getRealTextureLoc(resLoc);
		if (!existenceChecker.test(realLoc)) {
			System.out.println("MobZ ItemModelDataProvider cannot find texture: " + realLoc);
		};
		return realLoc;
	}

	// From ItemModelGenerators
	protected ResourceLocation generateFlatItem(Item item, String suffix, ModelTemplate modelTemplate) {
		ResourceLocation textureResLoc = ModelLocationUtils.getModelLocation(item, suffix);
		getRealTextureLocWithCheck(textureResLoc);

		return modelTemplate.create(textureResLoc, TextureMapping.layer0(textureResLoc), this::addItemModel);
    }

	protected void simpleItemWithExistingModel(Item item) {
		this.addItemModelInfo(item, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item)));
	}

	protected void simpleItem(Item item) {
		this.addItemModelInfo(item,
				ItemModelUtils.plainModel(this.generateFlatItem(item, "", ModelTemplates.FLAT_ITEM)));
	}

	protected void blockItem(Block block) {
		ResourceLocation textureResLoc = ModelLocationUtils.getModelLocation(block.asItem());
		ModelTemplate template = new ModelTemplate(Optional.of(ModelLocationUtils.getModelLocation(block)), null);
		ResourceLocation modelResLoc = template.create(textureResLoc, new TextureMapping(), this::addItemModel);
		this.addItemModelInfo(block.asItem(), ItemModelUtils.plainModel(modelResLoc));
	}

	protected void handheldItem(Item item) {
		this.addItemModelInfo(item,
				ItemModelUtils.plainModel(this.generateFlatItem(item, "", ModelTemplates.FLAT_HANDHELD_ITEM)));
	}

	protected void spawnEggMobZ(MobZSpawnEgg egg) {
        ResourceLocation resourcelocation = ModelLocationUtils.decorateItemModelLocation("template_spawn_egg");
        this.addItemModelInfo(egg, ItemModelUtils.tintedModel(resourcelocation,
        		ItemModelUtils.constantTint(egg.backgroundColor),
        		ItemModelUtils.constantTint(egg.highlightColor)));
	}

	protected void shieldWithExistingModel(Item item) {
		this.addItemModelInfo(item,
				ItemModelUtils.conditional(
					ItemModelUtils.isUsingItem(),
					ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item, "_blocking")),
					ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item))
				)
			);
	}

	protected void vanillaBow(Item bowItem) {
		ItemModel.Unbaked idleModel = ItemModelUtils.plainModel(
				ModelLocationUtils.getModelLocation(bowItem));
		ItemModel.Unbaked pulling0 = ItemModelUtils.plainModel(
				this.generateFlatItem(bowItem, "_pulling_0", ModelTemplates.BOW));
		ItemModel.Unbaked pulling1 = ItemModelUtils.plainModel(
				this.generateFlatItem(bowItem, "_pulling_1", ModelTemplates.BOW));
		ItemModel.Unbaked pulling2 = ItemModelUtils.plainModel(
				this.generateFlatItem(bowItem, "_pulling_2", ModelTemplates.BOW));

		this.addItemModelInfo(bowItem,
				ItemModelUtils.conditional(
						ItemModelUtils.isUsingItem(),
						ItemModelUtils.rangeSelect(
								new UseDuration(false), 0.05F,
								pulling0,
								ItemModelUtils.override(pulling1, 0.65F),
								ItemModelUtils.override(pulling2, 0.9F)
						),
						idleModel
				)
			);
	}

	private ItemModel.Unbaked sacrificeKnifeBloodDry(SacrificeKnife knife, int bloodCounter) {
		String suffix = "_blood" + bloodCounter;
		return ItemModelUtils.rangeSelect(
				new CustomModelDataProperty(0), 1.0F,
				ItemModelUtils.plainModel(this.generateFlatItem(knife, suffix, ModelTemplates.FLAT_ITEM)),
				ItemModelUtils.override(
					ItemModelUtils.plainModel(this.generateFlatItem(knife, suffix + "dry1", ModelTemplates.FLAT_ITEM)), 0.666F),
				ItemModelUtils.override(
					ItemModelUtils.plainModel(this.generateFlatItem(knife, suffix + "dry2", ModelTemplates.FLAT_ITEM)), 1.333F)
		);
	}

	protected void sacrificeKnife(SacrificeKnife knife) {
		ItemModel.Unbaked model = ItemModelUtils.rangeSelect(
				new Damage(true), 1.0F,
				ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(knife)),
				ItemModelUtils.override(sacrificeKnifeBloodDry(knife, 1), 0.2F),
				ItemModelUtils.override(sacrificeKnifeBloodDry(knife, 2), 0.4F),
				ItemModelUtils.override(sacrificeKnifeBloodDry(knife, 3), 0.6F),
				ItemModelUtils.override(sacrificeKnifeBloodDry(knife, 4), 0.8F)
		);

		this.addItemModelInfo(knife, model);
	}

	// Blocks
	protected void cubeAll(Block block) {
    	this.addBlockState(createSimpleBlock(block,
        		TexturedModel.CUBE.create(block, this::addItemModel)));
    	this.blockItem(block);
    }

    public static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation resLoc) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resLoc));
    }
}
