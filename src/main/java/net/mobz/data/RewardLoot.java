package net.mobz.data;

import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;

import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;

import net.mobz.MobZ;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZItems;

public record RewardLoot() implements LootTableSubProvider {
	public static final ResourceKey<LootTable> BOSS_ZOMBIE =
			ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.tryBuild(MobZ.MODID, "rewards/boss_zombie"));
	public static final ResourceKey<LootTable> BIGBOSS =
			ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.tryBuild(MobZ.MODID, "rewards/bigboss"));

	@Override
	public void generate(HolderLookup.Provider registry, BiConsumer<ResourceKey<LootTable>, Builder> builder) {
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
