package net.mobz.fabric;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;

import net.mobz.IAbstractedAPI;
import net.mobz.MobZ;

public class FabricRegistryWrapper implements IAbstractedAPI {
	private Set<Supplier<?>> setters = new HashSet<>();
	private Map<CreativeModeTab, List<Supplier<? extends ItemLike>>> tabContents = new HashMap<>();

	@Override
	public <T extends Item> Supplier<T> registerItem(String name, CreativeModeTab tab,
			Function<Item.Properties, T> constructor, Consumer<T> setter) {
		ResourceKey<Item> resKey = MobZ.resKey(Registries.ITEM, name);
		T item = constructor.apply(new Item.Properties().setId(resKey));
		Registry.register(BuiltInRegistries.ITEM, resKey, item);

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
	public <T extends Block> Supplier<T> registerBlock(String name, CreativeModeTab tab,
			Function<BlockBehaviour.Properties, T> blockConstructor,
			BiFunction<T, Item.Properties, BlockItem> blockItemConstructor,
			Consumer<T> setter) {
		ResourceKey<Block> resKey = MobZ.resKey(Registries.BLOCK, name);
		T block = blockConstructor.apply(BlockBehaviour.Properties.of().setId(resKey));

		Registry.register(BuiltInRegistries.BLOCK, resKey, block);
		Supplier<BlockItem> blockItemSupplier = this.registerItem(name, null,
				(props) -> blockItemConstructor.apply(block, props.useBlockDescriptionPrefix()), null);

		if (tab != null) {
			tabContents.get(tab).add(blockItemSupplier);
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
		Registry.register(BuiltInRegistries.ENTITY_TYPE, MobZ.resLoc(name), entityType);
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
	public Supplier<Holder<SoundEvent>> registerSound(String name, ResourceLocation resloc, Consumer<SoundEvent> setter) {
		SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(resloc);
		Holder<SoundEvent> holder = Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, MobZ.resLoc(name), soundEvent);
		if (setter != null) {
			setters.add(() -> {
				setter.accept(soundEvent);
				return soundEvent;
			});
		}
		return () -> holder;
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
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MobZ.resLoc(resLoc.getPath()), tab);
		return tab;
	}

	@Override
	public Supplier<MobBucketItem> newMobBucketItem(Supplier<? extends EntityType<? extends Mob>> entitySupplier,
			Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier,
			Item.Properties properties) {

		return () -> new MobBucketItem(entitySupplier.get(), fluidSupplier.get(), soundSupplier.get(), properties);
	}

	@Override
	public <T> Supplier<DataComponentType<T>> registerDataComponentType(String name,
			UnaryOperator<net.minecraft.core.component.DataComponentType.Builder<T>> builder,
			Consumer<DataComponentType<T>> setter) {
		DataComponentType<T> dataComponent = builder.apply(DataComponentType.builder()).build();
		Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, MobZ.resLoc(name), dataComponent);

		if (setter != null) {
			setters.add(() -> {
				setter.accept(dataComponent);
				return dataComponent;
			});
		}

		return () -> dataComponent;
	}
}
