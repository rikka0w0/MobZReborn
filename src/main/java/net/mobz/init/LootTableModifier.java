package net.mobz.init;

import net.minecraft.loot.BinomialRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;
import net.mobz.ILootTableAdder;

public class LootTableModifier {
    public static final ResourceLocation[] LONE = new ResourceLocation[] { LootTables.BURIED_TREASURE };
    public static final ResourceLocation[] LTWO = new ResourceLocation[] { LootTables.VILLAGE_ARMORER,
    		LootTables.SHIPWRECK_TREASURE, LootTables.ABANDONED_MINESHAFT,
            LootTables.VILLAGE_FLETCHER, LootTables.VILLAGE_WEAPONSMITH,
            LootTables.VILLAGE_TOOLSMITH };
    public static final ResourceLocation[] LTHREE = new ResourceLocation[] { LootTables.NETHER_BRIDGE };

    public static void loadAll(ILootTableAdder lootTableAdder) {
    	lootTableAdder.addRoll(LONE, new BinomialRange(1, 0.3f), ItemLootEntry.lootTableItem(MobZItems.WHITEBAG));
    	lootTableAdder.addRoll(LTWO, new BinomialRange(5, 0.3f), ItemLootEntry.lootTableItem(MobZItems.HARDENEDMETAL_INGOT));
    	lootTableAdder.addRoll(LTWO, new BinomialRange(2, 0.1f), ItemLootEntry.lootTableItem(MobZItems.BOSS_INGOT));
    	lootTableAdder.addRoll(LTWO, new BinomialRange(1, 0.05f), ItemLootEntry.lootTableItem(MobZItems.AMAT_INGOT));
    	lootTableAdder.addRoll(LTHREE, new BinomialRange(2, 0.2f), ItemLootEntry.lootTableItem(MobZItems.WITHERMEAL));
    	lootTableAdder.addRoll("minecraft:entities/wither_skeleton", new BinomialRange(1, 0.1f), ItemLootEntry.lootTableItem(MobZItems.WITHERMEAL));
    }
}
