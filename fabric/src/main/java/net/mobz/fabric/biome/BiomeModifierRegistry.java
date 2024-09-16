package net.mobz.fabric.biome;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class BiomeModifierRegistry {
	public final static ResourceKey<Registry<MapCodec<? extends BiomeModifier>>> CODEC_REGISTRY_RESKEY =
			ResourceKey.createRegistryKey(ResourceLocation.tryBuild("fabric", "biome_modifier_codec"));

	public final static MappedRegistry<MapCodec<? extends BiomeModifier>> CODEC_REGISTRY =
			FabricRegistryBuilder.createSimple(CODEC_REGISTRY_RESKEY).buildAndRegister();

	public final static ResourceKey<Registry<BiomeModifier>> REGISTRY_KEY
		= ResourceKey.createRegistryKey(ResourceLocation.tryBuild("fabric", "biome_modifier"));

	public final static Codec<BiomeModifier> DIRECT_CODEC = CODEC_REGISTRY.byNameCodec()
			.dispatch(BiomeModifier::codec, Function.identity());

	public static void applyAll(RegistryAccess registryAccess) {
		RegistryLookup<BiomeModifier> registry = registryAccess.lookupOrThrow(BiomeModifierRegistry.REGISTRY_KEY);
		Map<ResourceLocation, BiomeModification> biomeModificationMap = new HashMap<>();
		registry.listElements().forEach(holder -> {
			// Per-mod based BiomeModification
			ResourceLocation resLoc = ResourceLocation.tryBuild(holder.key().location().getNamespace(),
					BiomeModifierRegistry.REGISTRY_KEY.location().getPath());
			BiomeModification biomeModification =
					biomeModificationMap.computeIfAbsent(resLoc, (key) -> BiomeModifications.create(key));
			holder.value().apply(biomeModification);
		});
//		biomeModificationMap.keySet().forEach(System.out::println);
	}

	static {
		DynamicRegistries.register(REGISTRY_KEY, DIRECT_CODEC);

		// Register biome modifier types here
		CODEC_REGISTRY.register(ResourceKey.create(CODEC_REGISTRY_RESKEY, AddSpawnsBiomeModifier.TYPE_NAME),
				AddSpawnsBiomeModifier.CODEC, new RegistrationInfo(Optional.empty(), Lifecycle.stable()));
	}
}
