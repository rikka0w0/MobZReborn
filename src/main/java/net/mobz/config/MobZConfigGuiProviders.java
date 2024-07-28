package net.mobz.config;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import me.shedaniel.autoconfig.gui.DefaultGuiProviders;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

import static me.shedaniel.autoconfig.util.Utils.getUnsafely;

public class MobZConfigGuiProviders {
	private static final ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

	public static GuiRegistry applyDefault() {
		return MobZConfigGuiProviders.apply(DefaultGuiProviders.apply(new GuiRegistry()));
	}

	public static GuiRegistry apply(GuiRegistry registry) {
        registry.registerAnnotationProvider(
                (i18n, field, config, defaults, guiProvider) -> Collections.singletonList(
                        ENTRY_BUILDER.startSubCategory(
                                        Component.translatable(i18n),
                                        getChildren(i18n, field, config, defaults, guiProvider)
                                )
                                .setExpanded(field.getAnnotation(CollapsibleObjectEx.class).startExpanded())
                                .build()
                ),
                field -> !field.getType().isPrimitive(),
                CollapsibleObjectEx.class
        );

		return registry;
	}

	private static List<Field> traverseFields(Class<?> fieldType, boolean includeSuper) {
		List<Field> result = new LinkedList<>();
		Class<?> cls = fieldType;
		while (cls != null && !cls.equals(Object.class)) {
			result.addAll(Arrays.asList(cls.getDeclaredFields()));

			if (!includeSuper) {
				break;
			}

			cls = cls.getSuperclass();
		}
		return result;
	}

    @SuppressWarnings("rawtypes")
	private static List<AbstractConfigListEntry> getChildren(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess guiProvider) {
        return getChildren(i18n, field.getType(), getUnsafely(field, config), getUnsafely(field, defaults), guiProvider);
    }

    @SuppressWarnings("rawtypes")
	private static List<AbstractConfigListEntry> getChildren(String i18n, Class<?> fieldType, Object iConfig, Object iDefaults, GuiRegistryAccess guiProvider) {
//        return Arrays.stream(fieldType.getDeclaredFields())
    	return traverseFields(fieldType, true).stream()
                .map(
                        iField -> {
                            String iI13n = String.format("%s.%s", i18n, iField.getName());
                            return guiProvider.getAndTransform(iI13n, iField, iConfig, iDefaults, guiProvider);
                        }
                )
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
