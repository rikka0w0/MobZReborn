package net.mobz.forge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

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
	private final Map<Field, ConfigValue<?>> fieldMap = new HashMap<>();
	private static ForgeConfigManager instance;
	private static ForgeConfigSpec configSpec;

    public static void register() {
        Pair<ForgeConfigManager, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ForgeConfigManager::new);
        ForgeConfigManager.instance = specPair.getLeft();
        ForgeConfigManager.configSpec = specPair.getRight();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfigManager.configSpec);
    }

    private void updateFields() {
        fieldMap.forEach((field, configValue) -> {
			try {
				field.set(configs, configValue.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() != ForgeConfigManager.configSpec) {
            return;
        }

        ForgeConfigManager.instance.updateFields();
    }

    private ForgeConfigManager(ForgeConfigSpec.Builder builder) {
    	for (Field field : Configs.class.getDeclaredFields()) {
    	    if (!Modifier.isStatic(field.getModifiers())) {
    	    	ConfigEntry.Category category = field.getAnnotation(ConfigEntry.Category.class);
    	        if (category != null) {
    	        	String categoryName = category.value();
    	        	String entryName = field.getName();
    	        	Comment commentAnnotation = field.getAnnotation(Comment.class);
    	        	String comment = commentAnnotation == null ? "No Comment!" : commentAnnotation.value();

    	        	builder.push(categoryName);
    	        	builder.comment(comment);
    	        	builder.translation("text.autoconfig.mobz.option." + entryName);

    	        	ConfigValue<?> configEntry = null;
					try {
						Class<?> fieldType = field.getType();
						if (fieldType == Boolean.class || fieldType == boolean.class) {
							configEntry = builder.define(entryName, field.getBoolean(configs));
						} else if (fieldType == Integer.class || fieldType == int.class) {
							ConfigEntry.BoundedDiscrete range = field.getAnnotation(ConfigEntry.BoundedDiscrete.class);
							if (range == null) {
								configEntry = builder.define(entryName, field.getInt(configs));
							} else {
								int defaultValue = field.getInt(configs);
								int min = (int) range.min();
								int max = (int) range.max();
								configEntry = builder.defineInRange(entryName, defaultValue, min, max);
							}
						} else if (fieldType == Double.class || fieldType == double.class) {
							configEntry = builder.define(entryName, field.getDouble(configs));
						}
					} catch (Exception e) {
						e.printStackTrace();
						configEntry = null;
					}

					if (configEntry != null) {
						fieldMap.put(field, configEntry);
					}
					builder.pop();
    	        }
    	    }
    	}

    	Configs.instance = this.configs;
    }
}
