package net.mobz.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;
import static net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator.binomial;

public class EntityLoot implements LootTableSubProvider {
	/*
	 * EntityLootSubProvider start
	 */
	protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity()
			.flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

	private final FeatureFlagSet allowed = FeatureFlags.REGISTRY.allFlags();
	private final FeatureFlagSet required = this.allowed;
	private final Map<EntityType<?>, Map<ResourceLocation, LootTable.Builder>> map = Maps.newHashMap();

	protected void add(EntityType<?> entityType, LootTable.Builder lootTableBuilder) {
		this.add(entityType, entityType.getDefaultLootTable(), lootTableBuilder);
	}

	protected void add(EntityType<?> entityType, ResourceLocation defaultLootTable,
			LootTable.Builder lootTableBuilder) {
		this.map.computeIfAbsent(entityType, p_251466_ -> new HashMap<>()).put(defaultLootTable, lootTableBuilder);
	}

	protected boolean canHaveLootTable(EntityType<?> entityType) {
		return entityType.getCategory() != MobCategory.MISC;
	}

	@Override
	public void generate(BiConsumer<ResourceLocation, LootTable.Builder> lootTableGenerator) {
		this.generate();

		Set<ResourceLocation> processed = new HashSet<>();
		Set.copyOf(this.map.keySet()).stream().map(EntityType::builtInRegistryHolder).forEach(entityTypeRef -> {
			EntityType<?> entitytype = entityTypeRef.value();
			if (entitytype.isEnabled(this.allowed)) {
				Map<ResourceLocation, LootTable.Builder> map = this.map.remove(entitytype);
				if (this.canHaveLootTable(entitytype)) {
					ResourceLocation resourcekey = entitytype.getDefaultLootTable();
					if (resourcekey != BuiltInLootTables.EMPTY && entitytype.isEnabled(this.required)
							&& (map == null || !map.containsKey(resourcekey))) {
						throw new IllegalStateException(String.format(Locale.ROOT, "Missing loottable '%s' for '%s'",
								resourcekey, entityTypeRef.key().location()));
					}

					if (map != null) {
						map.forEach((resKey, builder) -> {
							if (!processed.add(resKey)) {
								throw new IllegalStateException(String.format(Locale.ROOT,
										"Duplicate loottable '%s' for '%s'", resKey, entityTypeRef.key().location()));
							} else {
								lootTableGenerator.accept(resKey, builder);
							}
						});
					}
				} else {
					if (map != null) {
						throw new IllegalStateException(String.format(Locale.ROOT,
								"Weird loottables '%s' for '%s', not a LivingEntity so should not have loot",
								map.keySet().stream().map(ResourceLocation::toString)
										.collect(Collectors.joining(",")),
								entityTypeRef.key().location()));
					}
				}
			}
		});

		if (!this.map.isEmpty()) {
			throw new IllegalStateException(
					"Created loot tables for entities not supported by datapack: " + this.map.keySet());
		}
	}

	/*
	 * EntityLootSubProvider end
	 */

	public void generate() {
		// andriu
		this.add(MobZEntities.ANDRIU.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.FROZEN_SWORD.get())
						.apply(SetItemCountFunction.setCount(binomial(1, 0.05F)))
					)
				)
			);

		// archer
		this.add(MobZEntities.ARCHER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 6.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.CROSSBOW))
					.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE))
					.add(LootItem.lootTableItem(MobZBlocks.TOTEM_BASE.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
			);

		// armored_zombie
		this.add(MobZEntities.ARMORED_ZOMBIE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT))
					.add(LootItem.lootTableItem(MobZWeapons.ARMORED_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.025F, 0.01F))
				)
			);

		// baby_ravager
		this.add(MobZEntities.BABY_RAVAGER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BEEF)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// bigboss
		this.add(MobZEntities.BIGBOSS.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.005F, 0.01F))
				)
			);

		// black_bear
		this.add(MobZEntities.BLACKBEAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BEEF)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(2.0F))
					.add(LootItem.lootTableItem(MobZItems.BEAR_LEATHER.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.1F))
				)
			);

		// blue_spider
		this.add(MobZEntities.BLUE_SPIDER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.STRING)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SPIDER_EYE)
						.apply(SetItemCountFunction.setCount(between(-1.0F, 1.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			);

		// boar
		this.add(MobZEntities.BOAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.PORKCHOP)
						.apply(SetItemCountFunction.setCount(between(1.0F, 3.0F)))
						.apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// boss_skeleton
		this.add(MobZEntities.BOSS_SKELETON.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ARROW)
						.apply(SetItemCountFunction.setCount(between(0.0F, 9.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// boss_zombie
		this.add(MobZEntities.BOSS_ZOMBIE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(5.0F))
					.add(LootItem.lootTableItem(MobZItems.BOSS_INGOT.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.05F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.005F, 0.01F))
				)
			);

		// bowman
		this.add(MobZEntities.BOWMAN.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 6.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BOW))
					.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE))
					.add(LootItem.lootTableItem(MobZBlocks.TOTEM_BASE.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
			);

		// brown_bear
		this.add(MobZEntities.BROWNBEAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BEEF)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(2.0F))
					.add(LootItem.lootTableItem(MobZItems.BEAR_LEATHER.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.1F))
				)
			);

		// charles
		this.add(MobZEntities.CHARLES.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.ARMORED_SWORD.get())
						.apply(SetItemCountFunction.setCount(between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(2.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.1F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.SEAL_KEY.get()))
				)
			);

		// cookie_creeper
		this.add(MobZEntities.COOKIE_CREEPER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GUNPOWDER)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(5.0F))
					.add(LootItem.lootTableItem(Items.COOKIE))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.8F, 0.05F))
				)
			);

		// dirty_boar
		this.add(MobZEntities.DIRTY_BOAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.PORKCHOP)
						.apply(SetItemCountFunction.setCount(between(1.0F, 3.0F)))
						.apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// dwarf
		this.add(MobZEntities.DWARF.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 6.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT.get())
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.SHIELD.get()))
					.add(LootItem.lootTableItem(MobZWeapons.ERAGONS_AXE.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 5.0E-4F, 0.01F))
				)
			);

		// ender
		this.add(MobZEntities.ENDER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ENDER_PEARL)
						.apply(SetItemCountFunction.setCount(between(0.0F, 1.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZBlocks.ENDER_HEADER.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
			);

		// ender_knight
		this.add(MobZEntities.ENDER_KNIGHT.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.WITHER_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// ender_zombie
		this.add(MobZEntities.ENDER_ZOMBIE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ENDER_PEARL))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.025F, 0.01F))
				)
			);

		// fast_zombie
		this.add(MobZEntities.FAST_ZOMBIE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT))
					.add(LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// fiora
		this.add(MobZEntities.FIORA.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.ARMORED_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// frost_blaze
		this.add(MobZEntities.FROST_BLAZE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SNOWBALL)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(2.0F))
					.add(LootItem.lootTableItem(MobZItems.FROZEN_POWDER.get()))
					.add(LootItem.lootTableItem(Items.ICE))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.1F))
				)
			);

		// frost_creeper
		this.add(MobZEntities.FROST_CREEPER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GUNPOWDER)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))
				)
			);

		// golden_chicken
		this.add(MobZEntities.GOLDEN_CHICKEN.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.FEATHER)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.CHICKEN)
						.apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// honey_slime
		this.add(MobZEntities.HONEY_SLIME.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.HONEY_BLOCK)
						.apply(SetItemCountFunction.setCount(binomial(1, 0.8F)))
					)
				)
			);

		// ice_golem
		this.add(MobZEntities.ICEGOLEM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.FROZEN_POWDER.get())
						.apply(SetItemCountFunction.setCount(between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT)
						.apply(SetItemCountFunction.setCount(between(2.0F, 3.0F)))
					)
				)
			);

		// illusioner
		this.add(MobZEntities.ILLUSIONER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BOW))
					.add(LootItem.lootTableItem(Items.ARROW))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.01F))
				)
			);

		// iron_steve
		this.add(MobZEntities.IRON_STEVE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.MEDIVEAL_DISC.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.005F, 0.01F))
				)
			);

		// katherine
		this.add(MobZEntities.KATHERINE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// knight
		this.add(MobZEntities.KNIGHT.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_SWORD)
						.apply(SetItemCountFunction.setCount(binomial(1, 0.05F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.SHIELD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.08F, 0.01F))
				)
			);

		// lava_golem
		this.add(MobZEntities.LAVAGOLEM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.WARPED_FUNGUS)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT)
						.apply(SetItemCountFunction.setCount(between(1.0F, 3.0F)))
					)
				)
			);

		// lord_of_darkness
		this.add(MobZEntities.LORD_OF_DARKNESS.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.WITHER_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.3F, 0.01F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WHITE_BAG.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 5.0E-4F, 0.01F))
				)
			);

		// lost_skeleton
		this.add(MobZEntities.LOST_SKELETON.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.STONE_TOMAHAWK.get())
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// metal_golem
		this.add(MobZEntities.METALGOLEM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT.get())
						.apply(SetItemCountFunction.setCount(binomial(7, 0.5F)))
					)
				)
			);

		// nether_skeleton
		this.add(MobZEntities.NETHER_SKELETON.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ARROW)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// nether_wolf
		this.add(MobZEntities.NETHER_WOLF.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WITHER_POWDER.get())
						.apply(SetItemCountFunction.setCount(binomial(1, 0.001F)))
					)
				)
			);

		// overgrown_skeleton
		this.add(MobZEntities.OVERGROWN_SKELETON.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.ARROW)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// pigman
		this.add(MobZEntities.PIGMAN.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(3.0F))
					.add(LootItem.lootTableItem(Items.GOLD_INGOT))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.5F, 0.01F))
				)
			);

		// pillager_boss
		this.add(MobZEntities.PILLAGER_BOSS.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.EMERALD)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.PILLAGER_STAFF.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.8F, 0.05F))
				)
			);

		// purple_spider
		this.add(MobZEntities.PURPLE_SPIDER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.STRING)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SPIDER_EYE)
						.apply(SetItemCountFunction.setCount(between(-1.0F, 1.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
				)
			);

		// small_spider
		this.add(MobZEntities.SMALL_SPIDER.get(), LootTable.lootTable()
			);

		// small_zombie
		this.add(MobZEntities.SMALL_ZOMBIE.get(), LootTable.lootTable()
			);

		// soul_creeper
		this.add(MobZEntities.SOUL_CREEPER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GUNPOWDER)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
					.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))
				)
			);

		// spider_mage
		this.add(MobZEntities.SPIDER_MAGE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.EMERALD)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZBlocks.TOTEM_TOP.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.06F, 0.01F))
				)
			);

		// stone_golem
		this.add(MobZEntities.STONEGOLEM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.STONE)
						.apply(SetItemCountFunction.setCount(binomial(2, 0.5F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.COAL_ORE)
						.apply(SetItemCountFunction.setCount(binomial(1, 0.2F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_ORE)
						.apply(SetItemCountFunction.setCount(binomial(1, 0.2F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_ORE)
						.apply(SetItemCountFunction.setCount(binomial(1, 0.1F)))
					)
				)
			);

		// tank_zombie
		this.add(MobZEntities.TANK_ZOMBIE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.ROTTEN_FLESH.get())
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.IRON_INGOT))
					.add(LootItem.lootTableItem(MobZItems.HARDENEDMETAL_INGOT.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
			);

		// templar
		this.add(MobZEntities.TEMPLAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SHIELD))
					.add(LootItem.lootTableItem(MobZWeapons.ARMORED_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// tiny_spider
		this.add(MobZEntities.TINY_SPIDER.get(), LootTable.lootTable()
			);

		// warrior
		this.add(MobZEntities.WARRIOR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.GOLD_NUGGET)
						.apply(SetItemCountFunction.setCount(between(0.0F, 5.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.POISON_SWORD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.05F, 0.01F))
				)
			);

		// wild_boar
		this.add(MobZEntities.WILD_BOAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.PORKCHOP)
						.apply(SetItemCountFunction.setCount(between(1.0F, 3.0F)))
						.apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
			);

		// william
		this.add(MobZEntities.WILLIAM.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZWeapons.ARMORED_SWORD.get())
						.apply(SetItemCountFunction.setCount(binomial(1, 0.05F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.SHIELD.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.08F, 0.01F))
				)
			);

		// withender
		this.add(MobZEntities.WITHENDER.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.IMMUNITY_ORB.get())
						.apply(SetItemCountFunction.setCount(binomial(1, 0.2F)))
					)
				)
			);

		// wither_blaze
		this.add(MobZEntities.WITHER_BLAZE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.COAL)
						.apply(SetItemCountFunction.setCount(between(0.0F, 1.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.WITHER_POWDER.get())
						.apply(SetItemCountFunction.setCount(binomial(1, 0.01F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.025F, 0.01F))
				)
			);

		// zombie_mage
		this.add(MobZEntities.ZOMBIE_MAGE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(Items.EMERALD)
						.apply(SetItemCountFunction.setCount(between(0.0F, 2.0F)))
						.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(0.0F, 1.0F)))
					)
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZBlocks.TOTEM_TOP.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.2F, 0.01F))
				)
				.withPool(LootPool.lootPool().setRolls(exactly(1.0F))
					.add(LootItem.lootTableItem(MobZItems.MEDIVEAL_DISC_2.get()))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(randomChanceAndLootingBoost(this.registries, 0.06F, 0.01F))
				)
			);
	}

	// Wrapper for 1.21
	private Object registries = null;
	public static class EnchantedCountIncreaseFunction {
		public static LootingEnchantFunction.Builder lootingMultiplier(Object dummy,
				NumberProvider pLootingMultiplier) {
			return new LootingEnchantFunction.Builder(pLootingMultiplier);
		}
	}

	public static LootItemCondition.Builder randomChanceAndLootingBoost(Object dummy, float pChance, float pLootingMultiplier) {
		return LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(pChance, pLootingMultiplier);
	}
}
