package net.mobz.neoforge;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.mobz.MobZ;
import net.mobz.init.MobZItems;

public class ItemTagCodegen implements DataProvider {
	public static String rootDir = "";

	public final CompletableFuture<HolderLookup.Provider> lookupProvider;

	public ItemTagCodegen(CompletableFuture<HolderLookup.Provider> lookupProvider) {
		this.lookupProvider = lookupProvider;
	}

	public static String pathOf(String entityName) {
		return rootDir + "\\" + entityName + ".json";
	}


	public static String genCodeForTag(RegistryOps<JsonElement> ops, String tagName) {
		TagKey<?> tagKey = TagKey.create(Registries.ITEM, ResourceLocation.tryBuild(MobZ.MODID, tagName));
		String tagKeyCode = EntityLootCodegen.getCodeName(tagKey, (a, b) -> a==b, MobZ.class);

		JsonElement e = EntityLootCodegen.readJson(pathOf(tagName));
		JsonArray values = e.getAsJsonObject().getAsJsonArray("values").getAsJsonArray();

		String code = "		this.tag(" + tagKeyCode + ")\n";

		for (JsonElement value: values) {
			String name = value.getAsString();
			ResourceLocation resLoc = ResourceLocation.tryParse(name);
			Item item = BuiltInRegistries.ITEM.get(resLoc);
			String codeName = EntityLootCodegen.getCodeName(item, (a,b) -> a==b, Items.class, MobZItems.class);
			code += "			.add(" + codeName + ".builtInRegistryHolder().key())\n";
		}
		code += "			;\n";

		return code;
	}

	public static void parseAdvancements(RegistryOps<JsonElement> ops) {
		File folder = new File(rootDir);
		File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
		for (File jsonFile: jsonFiles) {
			String tagName = jsonFile.getName().replace(".json", "");
			System.out.println("		// " + tagName);
			System.out.println(genCodeForTag(ops, tagName));
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
		return "Code generator for items";
	}

}
