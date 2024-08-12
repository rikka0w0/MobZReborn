package net.mobz.init;

import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;

import net.mobz.ILootTableAdder;

public class LootTableModifier {
	public static void loadAll(ILootTableAdder lootTableAdder) {
		lootTableAdder.addRoll(BuiltInLootTables.BURIED_TREASURE, BinomialDistributionGenerator.binomial(1, 0.3f),
				LootItem.lootTableItem(MobZItems.WHITE_BAG.get()));

		List<ResourceLocation> CREATURE = List.of(BuiltInLootTables.VILLAGE_ARMORER,
				BuiltInLootTables.SHIPWRECK_TREASURE, BuiltInLootTables.ABANDONED_MINESHAFT,
				BuiltInLootTables.VILLAGE_FLETCHER, BuiltInLootTables.VILLAGE_WEAPONSMITH,
				BuiltInLootTables.VILLAGE_TOOLSMITH);
		lootTableAdder.addRoll(CREATURE,
				BinomialDistributionGenerator.binomial(5, 0.3f),
				LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT.get()));
		lootTableAdder.addRoll(CREATURE,
				BinomialDistributionGenerator.binomial(2, 0.1f),
				LootItem.lootTableItem(MobZItems.BOSS_INGOT.get()));
		lootTableAdder.addRoll(CREATURE,
				BinomialDistributionGenerator.binomial(1, 0.05f),
				LootItem.lootTableItem(MobZItems.AMAT_INGOT.get()));

		lootTableAdder.addRoll(BuiltInLootTables.NETHER_BRIDGE,
				BinomialDistributionGenerator.binomial(2, 0.2f),
				LootItem.lootTableItem(MobZItems.WITHER_POWDER.get()));

		lootTableAdder.addRoll(ResourceLocation.tryBuild("minecraft", "entities/wither_skeleton"),
				BinomialDistributionGenerator.binomial(1, 0.1f),
				LootItem.lootTableItem(MobZItems.WITHER_POWDER.get()));
	}
}