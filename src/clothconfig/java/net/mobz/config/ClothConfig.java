package net.mobz.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.mobz.Configs;

public class ClothConfig {
	public final static ConfigHolder<Configs> configHolder =
			AutoConfig.register(Configs.class, JanksonConfigSerializer::new);

	public static Configs get() {
		return configHolder.get();
	}
}
