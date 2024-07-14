package net.mobz.fabric.biome;

import java.util.function.Function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class BiomeModifierRegistry {
	public final static ResourceKey<Registry<Codec<? extends BiomeModifier>>> CODEC_REGISTRY_RESKEY =
			ResourceKey.createRegistryKey(new ResourceLocation("fabric", "biome_modifier_codec"));

	public final static MappedRegistry<Codec<? extends BiomeModifier>> CODEC_REGISTRY =
			FabricRegistryBuilder.createSimple(CODEC_REGISTRY_RESKEY).buildAndRegister();

	public final static ResourceKey<Registry<BiomeModifier>> REGISTRY_KEY
		= ResourceKey.createRegistryKey(new ResourceLocation("fabric", "biome_modifier"));

	public final static Codec<BiomeModifier> DIRECT_CODEC = CODEC_REGISTRY.byNameCodec()
			.dispatch(BiomeModifier::codec, Function.identity());

	public static void applyAll(RegistryAccess registryAccess) {
		RegistryLookup<BiomeModifier> registry = registryAccess.lookupOrThrow(BiomeModifierRegistry.REGISTRY_KEY);
		BiomeModification bm = BiomeModifications.create(REGISTRY_KEY.location());
		registry.listElements().map(Holder.Reference::value).forEach(result -> result.apply(bm));
	}

	static {
		DynamicRegistries.register(REGISTRY_KEY, DIRECT_CODEC);

		// Register biome modifier types here
		CODEC_REGISTRY.register(ResourceKey.create(CODEC_REGISTRY_RESKEY, AddSpawnsBiomeModifier.TYPE_NAME),
				AddSpawnsBiomeModifier.CODEC, Lifecycle.stable());
	}
}
