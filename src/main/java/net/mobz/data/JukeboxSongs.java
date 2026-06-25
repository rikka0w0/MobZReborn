package net.mobz.data;

import net.minecraft.core.RegistrySetBuilder.RegistryBootstrap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

import net.mobz.MobZ;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZSounds;

public record JukeboxSongs() implements RegistryBootstrap<JukeboxSong> {
	public static final ResourceKey<JukeboxSong> MEDIVEAL_SONG = MobZ.resKey(Registries.JUKEBOX_SONG, "mediveal_song");
	public static final ResourceKey<JukeboxSong> MEDIVEAL_SONG_2 = MobZ.resKey(Registries.JUKEBOX_SONG, "mediveal_song_2");

	@Override
	public void run(BootstrapContext<JukeboxSong> bootstrap) {
		bootstrap.register(MEDIVEAL_SONG, new JukeboxSong(MobZSounds.MEDIVEAL_MUSIC.get(), Component.translatable("item.mobz.mediveal_disc"), 4680, 1));
		bootstrap.register(MEDIVEAL_SONG_2, new JukeboxSong(MobZSounds.MEDIVEAL_MUSIC_2.get(), Component.translatable("item.mobz.mediveal_disc_2"), 660, 0));
	}
}
