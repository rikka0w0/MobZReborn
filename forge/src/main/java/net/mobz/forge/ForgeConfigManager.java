package net.mobz.forge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.mobz.Configs;
import net.mobz.MobZ;

// AutoConfig wrapper for Minecraft Forge
@Mod.EventBusSubscriber(modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeConfigManager {
	private final Configs configs = new Configs();
	private final List<Runnable> updaters = new LinkedList<>();
	private static ForgeConfigManager instance;
	private static ForgeConfigSpec configSpec;

    public static Configs register() {
        Pair<ForgeConfigManager, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ForgeConfigManager::new);
        ForgeConfigManager.instance = specPair.getLeft();
        ForgeConfigManager.configSpec = specPair.getRight();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ForgeConfigManager.configSpec);
        return ForgeConfigManager.instance.configs;
    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent.Loading configEvent) {
        if (configEvent.getConfig().getSpec() != ForgeConfigManager.configSpec) {
            return;
        }

        ForgeConfigManager.instance.updaters.forEach(Runnable::run);
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

	@SuppressWarnings("unchecked")
	private <V extends Enum<V>> ConfigValue<?> defineOption(ForgeConfigSpec.Builder builder, Object object, Field field, String entryName) {
    	ConfigValue<?> configEntry = null;
		try {
			Class<?> fieldType = field.getType();
			if (fieldType == Boolean.class || fieldType == boolean.class) {
				configEntry = builder.define(entryName, field.getBoolean(object));
			} else if (fieldType == Integer.class || fieldType == int.class) {
				ConfigEntry.BoundedDiscrete range = field.getAnnotation(ConfigEntry.BoundedDiscrete.class);
				if (range == null) {
					configEntry = builder.define(entryName, field.getInt(object));
				} else {
					int defaultValue = field.getInt(object);
					int min = (int) range.min();
					int max = (int) range.max();
					configEntry = builder.defineInRange(entryName, defaultValue, min, max);
				}
			} else if (fieldType == Double.class || fieldType == double.class) {
				configEntry = builder.define(entryName, field.getDouble(object));
			} else if (fieldType.isEnum()) {
				configEntry = builder.defineEnum(entryName, (V) field.get(object));
			}

			final ConfigValue<?> configEntryFinal = configEntry;
			this.updaters.add(() -> {
				try {
					field.set(object, configEntryFinal.get());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			configEntry = null;
		}
		return configEntry;
	}

    private ForgeConfigManager(ForgeConfigSpec.Builder builder) {
    	for (Field field : Configs.class.getDeclaredFields()) {
    		if (Modifier.isStatic(field.getModifiers()))
    			continue;

    		String baseName = field.getName();

    		ConfigEntry.Category category = field.getAnnotation(ConfigEntry.Category.class);
    		if (category != null) {
    			builder.push(category.value());
    		}
    		Comment comment = field.getAnnotation(Comment.class);
    		if (comment != null) {
    			builder.comment(comment.value());
    		}

    		// Assume that all CollapsibleObjects are entity configs
    		if (field.isAnnotationPresent(ConfigEntry.Gui.CollapsibleObject.class)) {
    			builder.push(baseName);
//    			builder.translation("entity.mobz." + baseName);
    			for (Field subField : traverseFields(field.getType(), true)) {
    				try {
        				String name = subField.getName();
    					Object fieldVal = field.get(this.configs);
        	        	builder.translation("text.autoconfig.mobz.option." + name);
        				defineOption(builder, fieldVal, subField, name);
    				} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
        		}
    			builder.pop();
    		} else {
    			builder.translation("text.autoconfig.mobz.option." + baseName);
    			defineOption(builder, this.configs, field, baseName);
    		}

    		if (category != null) {
    			builder.pop();
    		}
    	}
    }
}