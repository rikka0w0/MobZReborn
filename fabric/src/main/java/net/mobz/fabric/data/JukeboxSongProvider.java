package net.mobz.fabric.data;

import java.util.concurrent.CompletableFuture;

import com.mojang.serialization.Lifecycle;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

import net.mobz.data.JukeboxSongs;

public class JukeboxSongProvider extends FabricDynamicRegistryProvider {
	public JukeboxSongProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registeries) {
		super(output, registeries);
	}

	@Override
	public String getName() {
		return "MobZ Jukebox songs";
	}

	@Override
	protected void configure(HolderLookup.Provider registries,
			FabricDynamicRegistryProvider.Entries entries) {
		new JukeboxSongs().run(new Wrapper(entries));
	}

	private static record Wrapper(FabricDynamicRegistryProvider.Entries entries) implements BootstrapContext<JukeboxSong> {
		@Override
		public <S> HolderGetter<S> lookup(ResourceKey<? extends Registry<? extends S>> resKey) {
			throw new RuntimeException("JukeboxSongProvider$Wrapper is not implemented!!!");
		}

		@Override
		public Holder.Reference<JukeboxSong> register(ResourceKey<JukeboxSong> resKey, JukeboxSong song, Lifecycle lifecycle) {
			return (Holder.Reference<JukeboxSong>) entries.add(resKey, song);
		}
	}
}
