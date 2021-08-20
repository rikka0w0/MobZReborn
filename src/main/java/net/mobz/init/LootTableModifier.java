package net.mobz.init;

import net.minecraft.world.level.storage.loot.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.resources.ResourceLocation;
import net.mobz.ILootTableAdder;

public class LootTableModifier {
    public static final ResourceLocation[] LONE = new ResourceLocation[] { BuiltInLootTables.BURIED_TREASURE };
    public static final ResourceLocation[] LTWO = new ResourceLocation[] { BuiltInLootTables.VILLAGE_ARMORER,
    		BuiltInLootTables.SHIPWRECK_TREASURE, BuiltInLootTables.ABANDONED_MINESHAFT,
            BuiltInLootTables.VILLAGE_FLETCHER, BuiltInLootTables.VILLAGE_WEAPONSMITH,
            BuiltInLootTables.VILLAGE_TOOLSMITH };
    public static final ResourceLocation[] LTHREE = new ResourceLocation[] { BuiltInLootTables.NETHER_BRIDGE };

    public static void loadAll(ILootTableAdder lootTableAdder) {
    	lootTableAdder.addRoll(LONE, new BinomialDistributionGenerator(1, 0.3f), LootItem.lootTableItem(MobZItems.WHITEBAG));
    	lootTableAdder.addRoll(LTWO, new BinomialDistributionGenerator(5, 0.3f), LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT));
    	lootTableAdder.addRoll(LTWO, new BinomialDistributionGenerator(2, 0.1f), LootItem.lootTableItem(MobZItems.BOSS_INGOT));
    	lootTableAdder.addRoll(LTWO, new BinomialDistributionGenerator(1, 0.05f), LootItem.lootTableItem(MobZItems.AMAT_INGOT));
    	lootTableAdder.addRoll(LTHREE, new BinomialDistributionGenerator(2, 0.2f), LootItem.lootTableItem(MobZItems.WITHERMEAL));
    	lootTableAdder.addRoll("minecraft:entities/wither_skeleton", new BinomialDistributionGenerator(1, 0.1f), LootItem.lootTableItem(MobZItems.WITHERMEAL));
    }
}
