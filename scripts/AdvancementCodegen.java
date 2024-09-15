package net.mobz.neoforge;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;

import net.mobz.MobZ;
import net.mobz.init.MobZIcons;

public class AdvancementCodegen implements DataProvider {
	public static String rootDir = "";

	public final CompletableFuture<HolderLookup.Provider> lookupProvider;

	public AdvancementCodegen(CompletableFuture<HolderLookup.Provider> lookupProvider) {
		this.lookupProvider = lookupProvider;
	}

	public static String pathOf(String entityName) {
		return rootDir + "\\" + entityName + ".json";
	}

	public static boolean headExists(String name) {
		for (String headName: MobZIcons.headNames) {
			if (headName.equals(name))
				return true;
		}
		return false;
	}

	public static final Map<CriterionTrigger<?>, String> TRIGGER_NAMES = Map.of(
			CriteriaTriggers.PLAYER_KILLED_ENTITY, "killByPlayer",
			CriteriaTriggers.PLAYER_HURT_ENTITY, "hurtByPlayer");

	public static record GeneratedCode(String name, String parentName, String code) {};

	public static GeneratedCode genCodeForEntity(RegistryOps<JsonElement> ops, String entityName) {
		JsonElement e = EntityLootCodegen.readJson(pathOf(entityName));
		DataResult<Advancement> parseResult = Advancement.CODEC.parse(ops, e);
		if (!parseResult.isSuccess()) {
			throw new RuntimeException("Error while parsing: " + entityName);
		}

		String entityCode = EntityLootCodegen.getMobZEntityCode(entityName);

		Advancement advancement = parseResult.getOrThrow();
		Map<String, Criterion<?>> criteriaMap = advancement.criteria();
		DisplayInfo displayInfo = advancement.display().orElseThrow();
		AdvancementRewards rewards = advancement.rewards();
		String parentName = advancement.parent().orElseThrow().getPath().replace("mob/", "");

		ResourceLocation iconResLoc = BuiltInRegistries.ITEM.getKey(displayInfo.getIcon().getItem());
		String head_name = iconResLoc.getPath();
		if (!headExists(head_name) || !iconResLoc.getNamespace().equals(MobZ.MODID)) {
			throw new RuntimeException("Icon is missing for: " + entityName);
		}

		String iconName = head_name.equals("head_" + entityName) ? "null" : "\"" + head_name + "\"";

		if (criteriaMap.size() != 1) {
			throw new RuntimeException("Advancement for " + entityName + " has " + criteriaMap.size() + "criteria!!!");
		}

		Criterion<?> criteria = criteriaMap.values().stream().findFirst().get();

		String triggerName = TRIGGER_NAMES.get(criteria.trigger());
		if (triggerName == null) {
			throw new RuntimeException("Advancement for " + entityName + " has an unknown trigger: " + criteria.trigger());
		}

		// Line 1
		String code = "		AdvancementHolder " + entityName + " = " + triggerName + "(writer, " + entityCode +", " + iconName + ", ";
		// parent
		code += parentName;
		code += ",\n";

		// Line2
		code += "			AdvancementType." + displayInfo.getType().name() + ", ";
		code += displayInfo.shouldShowToast() + ", ";
		code += displayInfo.shouldAnnounceChat() + ", ";
		code += displayInfo.isHidden() + ",";

		if (rewards.experience() > 0) {
			code += "\n			AdvancementRewards.Builder.experience(" + rewards.experience() + ")";
			if (rewards.function().isPresent()) {
				code += ".runs(ResourceLocation.tryParse(\"" + rewards.function().get().getId() + "\"))";
			}
		} else {
			if (rewards.loot().size() > 0 || rewards.recipes().size() > 0) {
				throw new RuntimeException("Does not support loot and recipe for reward in " + entityName);
			}
			code += " null";
		}
		code += ");\n";

		return new GeneratedCode(entityName, parentName, code);
	}

	public static void parseAdvancements(RegistryOps<JsonElement> ops) {
		Set<GeneratedCode> codeBlocks = new HashSet<>();

		File folder = new File(rootDir);
		File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
		for (File jsonFile: jsonFiles) {
			String entityName = jsonFile.getName().replace(".json", "");
			codeBlocks.add(genCodeForEntity(ops, entityName));
		}

		// Sort the advancement tree to avoid premature references.
		List<GeneratedCode> results = new LinkedList<>();
		Set<GeneratedCode> parents = new HashSet<>();
		parents.add(new GeneratedCode("started", "", ""));
		while (!parents.isEmpty()) {
			List<String> parentNames = parents.stream().map(GeneratedCode::name).collect(Collectors.toUnmodifiableList());
			parents.clear();

			Iterator<GeneratedCode> iterator = codeBlocks.iterator();
			while (iterator.hasNext()) {
				GeneratedCode code = iterator.next();
				if (parentNames.contains(code.parentName)) {
					parents.add(code);
					results.add(code);
					iterator.remove();
				}
			}
		}

		// Print the generated code in the console
		for (GeneratedCode code: results) {
			System.out.println("		// " + code.name);
			System.out.println(code.code);
			System.out.println();
		}

		return;
	}

	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {
		return this.lookupProvider.thenAccept(provider -> {
			RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, provider);
			parseAdvancements(ops);
		});
	}

	@Override
	public String getName() {
		return "Code generator for mob advancements";
	}

}
