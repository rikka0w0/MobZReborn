package net.mobz.data;

import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;

import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;

import net.mobz.MobZ;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZItems;

public record RewardLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
	public static final ResourceKey<LootTable> GOLDEN_CHICKEN_LAY =
			MobZ.resKey(Registries.LOOT_TABLE, "gameplay/golden_chicken_lay");
	public static final ResourceKey<LootTable> BOSS_ZOMBIE =
			MobZ.resKey(Registries.LOOT_TABLE, "rewards/boss_zombie");
	public static final ResourceKey<LootTable> BIGBOSS =
			MobZ.resKey(Registries.LOOT_TABLE, "rewards/bigboss");

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> builder) {
		builder.accept(GOLDEN_CHICKEN_LAY, LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(exactly(1.0F)))
					)
				)
		);

		builder.accept(BOSS_ZOMBIE, LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZBlocks.BOSS_TROPHY.get())
						.apply(SetItemCountFunction.setCount(exactly(1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get())
						.apply(SetItemCountFunction.setCount(exactly(1.0F)))
					)
				)
		);

		builder.accept(BIGBOSS, LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get())
						.apply(SetItemCountFunction.setCount(exactly(1.0F)))
					)
				)
		);
	}

}
