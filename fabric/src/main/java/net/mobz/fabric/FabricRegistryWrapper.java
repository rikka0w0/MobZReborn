package net.mobz.fabric;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.mobz.IAbstractedAPI;
import net.mobz.MobZ;

public class FabricRegistryWrapper implements IAbstractedAPI {
	private Set<Supplier<?>> setters = new HashSet<>();
	private Map<CreativeModeTab, List<Supplier<? extends ItemLike>>> tabContents = new HashMap<>();

	private static ResourceLocation res(String name) {
		return new ResourceLocation(MobZ.MODID, name);
	}

	@Override
	public <T extends Item> Supplier<T> registerItem(String name, CreativeModeTab tab, Supplier<T> constructor, Consumer<T> setter) {
		T item = constructor.get();
		Registry.register(BuiltInRegistries.ITEM, res(name), item);

		if (tab != null) {
			tabContents.get(tab).add(() -> item);
		}

		if (setter != null) {
			setters.add(() -> {
				setter.accept(item);
				return item;
			});
		}
		return () -> item;
	}

	@Override
	public <T extends Block> Supplier<T> registerBlock(String name, CreativeModeTab tab, Supplier<T> constructor,
			Function<T, BlockItem> blockItemConstructor, Consumer<T> setter) {
		T block = constructor.get();
		BlockItem blockItem = blockItemConstructor.apply(block);
		ResourceLocation regName = res(name);
		Registry.register(BuiltInRegistries.BLOCK, regName, block);
		Registry.register(BuiltInRegistries.ITEM, regName, blockItem);

		if (tab != null) {
			tabContents.get(tab).add(() -> blockItem);
		}

		if (setter != null) {
			setters.add(() -> {
				setter.accept(block);
				return block;
			});
		}
		return () -> block;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity, T extends EntityType<E>> Supplier<T> registerEntityType(String name,
			Supplier<T> constructor, Supplier<Builder> attribModifierSupplier, Consumer<T> setter) {
		T entityType = constructor.get();
		Registry.register(BuiltInRegistries.ENTITY_TYPE, res(name), entityType);
		if (attribModifierSupplier != null) {
			FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) entityType,
					attribModifierSupplier.get());
		}
		if (setter != null) {
			setters.add(() -> {
				setter.accept(entityType);
				return entityType;
			});
		}
		return () -> entityType;
	}

	@Override
	public Supplier<SoundEvent> registerSound(String name, ResourceLocation resloc, Consumer<SoundEvent> setter) {
		SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(resloc);
		Registry.register(BuiltInRegistries.SOUND_EVENT, res(name), soundEvent);
		if (setter != null) {
			setters.add(() -> {
				setter.accept(soundEvent);
				return soundEvent;
			});
		}
		return () -> soundEvent;
	}

	@Override
	public CreativeModeTab tab(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		String displayNameKey = "itemGroup." + resLoc.getNamespace() + "." + resLoc.getPath();

		List<Supplier<? extends ItemLike>> contents = new LinkedList<>();
		CreativeModeTab tab =  FabricItemGroup.builder()
				.title(Component.translatable(displayNameKey))
				.icon(iconSupplier)
				.displayItems((params, output) -> {
					contents.stream().map(Supplier::get).forEach(output::accept);
				})
				.build();

		tabContents.put(tab, contents);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, res(resLoc.getPath()), tab);
		return tab;
	}

	@Override
	public Supplier<SpawnEggItem> spawnEggOf(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor,
			int highlightColor, Item.Properties props) {
		return () -> new FabricSpawnEgg(type.get(), backgroundColor, highlightColor, props);
	}

	@Override
	public Supplier<RecordItem> newRecordItem(int comparatorValue, Supplier<SoundEvent> soundSupplier,
			Item.Properties builder, int lengthInTicks) {
		return () -> new RecordItem(comparatorValue, soundSupplier.get(), builder, lengthInTicks) {};
	}

	@Override
	public Supplier<MobBucketItem> newMobBucketItem(Supplier<? extends EntityType<?>> entitySupplier,
			Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier,
			Item.Properties properties) {

		return () -> new MobBucketItem(entitySupplier.get(), fluidSupplier.get(), soundSupplier.get(), properties);
	}

	@Override
	public FoodProperties getFoodProperties(ItemStack stack, LivingEntity entity) {
		return stack.getItem().getFoodProperties();
	}
}
