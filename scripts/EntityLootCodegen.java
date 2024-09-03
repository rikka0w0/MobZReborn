package net.mobz.neoforge;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class EntityLootCodegen implements DataProvider {
	// Works in Neoforge 1.20.6
	// Usage:
	// 1. fill in rootDir
	// 2. Call `event.getGenerator().addProvider(true, new EntityLootCodegen(event.getLookupProvider()));`
	public static String rootDir = "";

	public final CompletableFuture<HolderLookup.Provider> lookupProvider;

	public EntityLootCodegen(CompletableFuture<HolderLookup.Provider> lookupProvider) {
		this.lookupProvider = lookupProvider;
	}

	public static JsonElement readJson(String filePath) {
        try {
			return JsonParser.parseString(Files.readString(Path.of(filePath), StandardCharsets.UTF_8));
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
        return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getUnsafe(Object me, Class<?> fieldCls, String fieldName) {
		try {
			Field field = fieldCls.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(me);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public static <T> T getUnsafe(Object me, String fieldName) {
		return getUnsafe(me, me.getClass(), fieldName);
	}

	@SuppressWarnings("unchecked")
	public static List<LootItemFunction> getFunctions(LootPoolSingletonContainer entry) {
		try {
			Field field = LootPoolSingletonContainer.class.getDeclaredField("functions");
			field.setAccessible(true);
			return (List<LootItemFunction>) field.get(entry);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public static Item getItem(LootItem lootItem) {
		Item[] itemResult = new Item[1];
		lootItem.createItemStack(itemStack ->  itemResult[0] = itemStack.getItem(), null);
		return itemResult[0];
	}

	public static boolean isItemTheSame(Object itemLike, Object value) {
		if (itemLike instanceof BlockItem blockItem) {
			if (blockItem.getBlock() == value) {
				return true;
			} else {
				return itemLike == value;
			}
		} else {
			return itemLike == value;
		}
	}

	public static String getItemCode(ItemLike itemLike) {
		return getCodeName(itemLike, EntityLootCodegen::isItemTheSame,
				MobZBlocks.class, Items.class, MobZItems.class, MobZWeapons.class, MobZArmors.class);
	}

	public static String getCodeName(Object givenObj, BiPredicate<Object, Object> comparator, Class<?>... classes) {
        List<Field> fields = Stream
        		.of(classes)
        		.map(Class::getDeclaredFields)
        		.flatMap(Arrays::stream)
        		.collect(Collectors.toList());


		for (Field field: fields) {
			if (!Modifier.isStatic(field.getModifiers()))
				continue;

			if (field.getType().isAssignableFrom(Supplier.class)) {
				try {
					Supplier<?> supplier = (Supplier<?>) field.get(null);
					if (supplier != null) {
						Object value = supplier.get();
						if (comparator.test(givenObj, value)) {
							return field.getDeclaringClass().getSimpleName() + "." + field.getName() + ".get()";
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else if (field.getType().isAssignableFrom(givenObj.getClass())) {
				try {
					Object value = field.get(null);
					if (comparator.test(givenObj, value)) {
						return field.getDeclaringClass().getSimpleName() + "." + field.getName();
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		throw new RuntimeException("Cannot gen code for " + givenObj.toString());
	}

	public static String getMobZEntityCode(String entityName) {
		for (Field field: MobZEntities.class.getDeclaredFields()) {
			if (!Modifier.isStatic(field.getModifiers()))
				continue;

			if (field.getType().isAssignableFrom(Supplier.class)) {
				try {
					Supplier<?> supplier = (Supplier<?>) field.get(null);
					if (supplier != null) {
						Object value = supplier.get();
						if (value instanceof EntityType<?> entityType) {
							ResourceLocation resLoc = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
							if (resLoc.getNamespace().equals(MobZ.MODID) && resLoc.getPath().equals(entityName)) {
								return field.getDeclaringClass().getSimpleName() + "." + field.getName() + ".get()";
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		throw new RuntimeException("Cannot find code for: " + entityName);
	}

	public static String pathOf(String entityName) {
		return rootDir + "\\" + entityName + ".json";
	}

	public static String handleCondition(String entityName, LootItemCondition condition) {
		if (condition instanceof LootItemEntityPropertyCondition propCondition) {
			String target = "LootContext.EntityTarget." + propCondition.entityTarget().name();
			Optional<EntityPredicate> predicate = propCondition.predicate();
			String code = ".when(LootItemEntityPropertyCondition.hasProperties(" + target + ", ";
			if (predicate.get().flags().isPresent()
					&& predicate.get().flags().get().isOnFire().isPresent()) {
				return code + "ENTITY_ON_FIRE))";
			} else if (predicate.get().entityType().isPresent()) {
				Optional<TagKey<EntityType<?>>> types = predicate.get().entityType().get().types().unwrapKey();
				if (types.isPresent()) {
					TagKey<EntityType<?>> tagKey = types.get();
					String entityTag = getCodeName(tagKey, (a,b) -> a==b, EntityTypeTags.class);
					return code + "EntityPredicate.Builder.entity().of(" + entityTag +")))";
				}
			} else {
				throw new RuntimeException("Unknown predicate found in: " + entityName);
			}
		} else if (condition instanceof LootItemKilledByPlayerCondition kbp) {
			return ".when(LootItemKilledByPlayerCondition.killedByPlayer())";
		} else if (condition instanceof LootItemRandomChanceWithLootingCondition lootBoost) {
			return ".when(randomChanceAndLootingBoost(this.registries, "
					+ lootBoost.percent() + "F, "
					+ lootBoost.lootingMultiplier() + "F))";
		}

		throw new RuntimeException("Unknown condition " + condition.getClass().getSimpleName() + " found in: " + entityName);
	}

	public static String genCodeForEntity(RegistryOps<JsonElement> ops, String entityName) {
		String entityCode = getMobZEntityCode(entityName);

		JsonElement e = readJson(pathOf(entityName));
		DataResult<LootTable> parseResult = LootTable.DIRECT_CODEC.parse(ops, e);
		if (!parseResult.isSuccess()) {
			throw new RuntimeException("Error!");
		}

//		String code = "	private LootTable.Builder " + entityName + "() {\n"
//				+ "		return LootTable.lootTable()";

		String code = "		this.add(" + entityCode + ", LootTable.lootTable()";

		LootTable lootTable = parseResult.getOrThrow();
		List<LootPool> pools = getUnsafe(lootTable, "pools");
		for (LootPool pool: pools) {
			if (pool.getRolls() instanceof ConstantValue exactValue) {
				code += "\n				.withPool(LootPool.lootPool().setRolls(exactly(" + exactValue.value() + "F))\n";
			} else {
				throw new RuntimeException("Not ConstantValue, in " + entityName);
			}

			List<LootPoolEntryContainer> entries = getUnsafe(pool, "entries");
			for (LootPoolEntryContainer entry: entries) {
				if (entry instanceof LootItem lootItem) {
					Item item = getItem(lootItem);
					String itemCode = getItemCode(item);

					List<LootItemFunction> functions = getUnsafe(lootItem, LootPoolSingletonContainer.class, "functions");
					if (functions.size() == 0) {
						code += "					.add(LootItem.lootTableItem(" + itemCode + "))\n";
					} else {
						code += "					.add(LootItem.lootTableItem(" + itemCode + ")\n";
						for (LootItemFunction function: functions) {
							if (! (function instanceof LootItemConditionalFunction conditionalFunction))
								throw new RuntimeException("Not a LootItemConditionalFunction, no support, in " + entityName);

							String functionName = function.getClass().getSimpleName();
							code += "						.apply(";
							if (function instanceof SetItemCountFunction setCount) {
								NumberProvider value = getUnsafe(function, SetItemCountFunction.class, "value");
								boolean add = getUnsafe(function, SetItemCountFunction.class, "add");
								if (value instanceof UniformGenerator uniform) {
									ConstantValue min = (ConstantValue) uniform.min();
									ConstantValue max = (ConstantValue) uniform.max();
									code += "SetItemCountFunction.setCount(between(";
									code += "" + min.value() + "F, " + max.value() + "F";
									if (add)
										code += ", true";
									code += "))";
								} else if (value instanceof BinomialDistributionGenerator binomial) {
									ConstantValue n = (ConstantValue) binomial.n();
									ConstantValue p = (ConstantValue) binomial.p();
									code += "SetItemCountFunction.setCount(binomial("
											 + Math.round(n.value()) + ", " + p.value() + "F))";
								} else {
									throw new RuntimeException("Unknown NumberProvider: " + value.getClass().getSimpleName() + " in " + entityName);
								}
							} else if (function instanceof LootingEnchantFunction lootEnchant) {
								NumberProvider value = getUnsafe(function, LootingEnchantFunction.class, "value");
								int limit = getUnsafe(function, LootingEnchantFunction.class, "limit");
								if (value instanceof UniformGenerator uniform) {
									ConstantValue min = (ConstantValue) uniform.min();
									ConstantValue max = (ConstantValue) uniform.max();
									code += "EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, between(";
									code += "" + min.value() + "F, " + max.value() + "F))";
									if (limit > 0) {
										code += ".setLimit(" + limit + ")";
									}
								} else {
									throw new RuntimeException("Unknown NumberProvider: " + value.getClass().getSimpleName());
								}
								lootEnchant = null;
							} else if (function instanceof SmeltItemFunction smeltItemFunc) {
								code += "SmeltItemFunction.smelted()";
							} else {
								throw new RuntimeException("Unknown function: " + functionName  + " in " + entityName);
							}

							List<LootItemCondition> funcConditions = getUnsafe(function, LootItemConditionalFunction.class, "predicates");
							for (LootItemCondition funcCondition: funcConditions) {
								code += handleCondition(entityName, funcCondition);
							}


							code += ")\n";
						}
						code += "					)\n";
					}
				} else if (entry instanceof TagEntry tagEntry) {
					TagKey<Item> tag = getUnsafe(tagEntry, TagEntry.class, "tag");
					String codeName = getCodeName(tag, (a,b) -> a==b, ItemTags.class);
					code += "					.add(TagEntry.expandTag(" + codeName + "))\n";
				} else {
					throw new RuntimeException("Unknown entry " + entry.getClass().getSimpleName()  + " found in " + entityName);
				}
			} // Entries

			List<LootItemCondition> conditions = getUnsafe(pool, "conditions");
			for (LootItemCondition condition: conditions) {
				code += "					";
				code += handleCondition(entityName, condition);
				code += "\n";
			}

			List<LootItemFunction> functions = getUnsafe(pool, "functions");
			if (!functions.isEmpty())
				throw new RuntimeException("Pool function is not supported  in " + entityName);

			code += "				)";
		} // Pools
		code += "\n			);";

		return code;
	}

	public static void parseLootTables(RegistryOps<JsonElement> ops) {
//		String code = genCodeForEntity(ops, "cookie_creeper");

		File folder = new File(rootDir);
		File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
		for (File jsonFile: jsonFiles) {
			String entityName = jsonFile.getName().replace(".json", "");
			System.out.println("		// " + entityName);
			System.out.println(genCodeForEntity(ops, entityName));
			System.out.println();
		}

		return;
	}

	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {
		return this.lookupProvider.thenAccept(provider -> {
			RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, provider);
			parseLootTables(ops);
		});
	}

	@Override
	public String getName() {
		return "Code generator for the entity loot table";
	}
}
