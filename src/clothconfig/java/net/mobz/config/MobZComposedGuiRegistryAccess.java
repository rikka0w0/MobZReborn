package net.mobz.config;

import java.lang.reflect.Field;
import java.util.List;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.ConfigScreenProvider;
import me.shedaniel.autoconfig.gui.DefaultGuiProviders;
import me.shedaniel.autoconfig.gui.DefaultGuiTransformers;
import me.shedaniel.autoconfig.gui.registry.ComposedGuiRegistryAccess;
import me.shedaniel.autoconfig.gui.registry.DefaultGuiRegistryAccess;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.client.gui.screens.Screen;

import net.mobz.Configs;

public class MobZComposedGuiRegistryAccess extends ComposedGuiRegistryAccess {
	public final static GuiRegistry GUI_REGISTRY =
			DefaultGuiTransformers.apply(DefaultGuiProviders.apply(AutoConfig.getGuiRegistry(Configs.class)));

	public MobZComposedGuiRegistryAccess(GuiRegistryAccess... children) {
		super(children);
	}

	public static void registerGuiProcessors() {
		GuiRegistry guiRegistry = AutoConfig.getGuiRegistry(Configs.class);
		DefaultGuiProviders.apply(guiRegistry);
		DefaultGuiTransformers.apply(guiRegistry);
	}

	public static <T extends ConfigData> Screen buildScreen(Screen parent) {
		Class<? extends ConfigData> cls = Configs.class;

		ConfigManager<? extends ConfigData> configManager =
			(ConfigManager<? extends ConfigData>) AutoConfig.getConfigHolder(cls);

		MobZComposedGuiRegistryAccess guiRegAccess = new MobZComposedGuiRegistryAccess(
                AutoConfig.getGuiRegistry(cls), GUI_REGISTRY, new DefaultGuiRegistryAccess());

		ConfigScreenProvider<? extends ConfigData> screenProvider =
			new ConfigScreenProvider<>(configManager, guiRegAccess, parent);

		return screenProvider.get();
	}

	private String mapI18n(String i18n, Field field) {
		if (field.isAnnotationPresent(ConfigEntry.Gui.CollapsibleObject.class)) {
			if (i18n.startsWith("text.autoconfig.mobz.option.")) {
				String[] i18nParts = i18n.split("\\.");
				//text.autoconfig.mobz.option.Alex
				if (i18nParts.length == 5) {
					return "entity.mobz." + i18nParts[4];	// entityName;
				}
			}
		} else if (i18n.startsWith("entity.mobz.")) {
			// entity.mobz.Alex.spawn
			String[] i18nParts = i18n.split("\\.");
			return "text.autoconfig.mobz.option." + i18nParts[3];	// suffix;
		}

		return i18n;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<AbstractConfigListEntry> get(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
		return super.get(mapI18n(i18n, field), field, config, defaults, registry);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<AbstractConfigListEntry> transform(List<AbstractConfigListEntry> guis, String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
		return super.transform(guis, mapI18n(i18n, field), field, config, defaults, registry);
	}
}
