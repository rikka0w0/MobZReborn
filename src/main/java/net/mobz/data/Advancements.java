package net.mobz.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

public class Advancements {
	public static AdvancementProvider all(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		return new AdvancementProvider(output, registries,
				List.of(new MobAdvancements()));
	}
}
