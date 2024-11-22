package net.mobz.forge;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mobz.IAbstractedAPI;
import net.mobz.MobZ;

public class ForgeRegistryWrapper implements IAbstractedAPI {
	private final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobZ.MODID);
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobZ.MODID);
	private final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MobZ.MODID);
	private final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MobZ.MODID);
	private final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MobZ.MODID);

	private Set<Supplier<?>> setters = new HashSet<>();
	private Set<Consumer<BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier>>> attribSuppliers = new HashSet<>();
	private Map<CreativeModeTab, List<Supplier<? extends ItemLike>>> tabContents = new HashMap<>();

	public ForgeRegistryWrapper() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
		ENTITY_TYPES.register(eventBus);
		SOUNDS.register(eventBus);
		TABS.register(eventBus);
	}

	public <T extends Item> Supplier<T> registerItem(String name, CreativeModeTab tab,
			Function<Item.Properties, T> constructor, Consumer<T> setter) {
		RegistryObject<T> regObj = ITEMS.register(name,
				() -> constructor.apply(new Item.Properties()));

		if (tab != null) {
			tabContents.get(tab).add(regObj);
		}

		if (setter != null) {
			setters.add(() -> {
				T val = regObj.get();
				setter.accept(val);
				return val;
			});
		}
		return regObj;
	}

	@Override
	public <T extends Block> Supplier<T> registerBlock(String name, CreativeModeTab tab,
			Function<BlockBehaviour.Properties, T> blockConstructor,
			BiFunction<T, Item.Properties, BlockItem> blockItemConstructor,
			Consumer<T> setter) {
		RegistryObject<T> regObj = BLOCKS.register(name,
				() -> blockConstructor.apply(BlockBehaviour.Properties.of()));

		if (tab != null) {
			tabContents.get(tab).add(regObj);
		}

		if (setter != null) {
			setters.add(() -> {
				T val = regObj.get();
				setter.accept(val);
				return val;
			});
		}

		this.registerItem(name, null,
				(props) -> blockItemConstructor.apply(regObj.get(), props)
				, null);

		return regObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Entity, T extends EntityType<E>> Supplier<T> registerEntityType(String name,
			Supplier<T> constructor, Supplier<AttributeSupplier.Builder> attribModifierSupplier, Consumer<T> setter) {
		RegistryObject<T> regObj = ENTITY_TYPES.register(name, constructor);
		if (setter != null) {
			setters.add(() -> {
				T val = regObj.get();
				setter.accept(val);
				return val;
			});
		}
		if (attribModifierSupplier != null) {
			attribSuppliers.add((regFunc) -> {
				EntityType<? extends LivingEntity> livingEntity = (EntityType<? extends LivingEntity>) regObj.get();
				regFunc.accept(livingEntity, attribModifierSupplier.get().build());
			});
		}
		return regObj;
	}

	@Override
	public Supplier<Holder<SoundEvent>> registerSound(String name, ResourceLocation resloc, Consumer<SoundEvent> setter) {
		Supplier<SoundEvent> constructor = () -> SoundEvent.createVariableRangeEvent(resloc);
		RegistryObject<SoundEvent> regObj = SOUNDS.register(name, constructor);
		if (setter != null) {
			setters.add(() -> {
				SoundEvent val = regObj.get();
				setter.accept(val);
				return val;
			});
		}
		// Forge updates the internal holder reference later
		// We cannot ask for the holder instance now
		return () -> regObj.getHolder().get();
	}

	public void setAllRegistryObjectFields() {
		setters.forEach(Supplier::get);
		setters.clear();
		setters = null;
	}

	public void applyGlobalEntityAttrib(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> regFunc) {
		attribSuppliers.forEach((pair) -> pair.accept(regFunc));
		attribSuppliers.clear();
		attribSuppliers = null;
	}

	@Override
	public CreativeModeTab tab(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		String displayNameKey = "itemGroup." + resLoc.getNamespace() + "." + resLoc.getPath();

		List<Supplier<? extends ItemLike>> contents = new LinkedList<>();
		CreativeModeTab tab = CreativeModeTab.builder()
				.title(Component.translatable(displayNameKey))
				.icon(iconSupplier)
				.displayItems((params, output) -> {
					contents.stream().map(Supplier::get).forEach(output::accept);
				})
				.build();

		tabContents.put(tab, contents);
		TABS.register(resLoc.getPath(), () -> tab);
		return tab;
	}

	@Override
	public Supplier<SpawnEggItem> spawnEggOf(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor,
			int highlightColor, Item.Properties props) {
		return () -> new ForgeSpawnEgg(type, highlightColor, highlightColor, props);
	}

	@Override
	public Supplier<MobBucketItem> newMobBucketItem(Supplier<? extends EntityType<?>> entitySupplier,
			Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier,
			Item.Properties properties) {
		return () -> new MobBucketItem(entitySupplier, fluidSupplier, soundSupplier, properties);
	}

	@Override
	public FoodProperties getFoodProperties(ItemStack stack, LivingEntity entity) {
		return stack.getItem().components().getTyped(DataComponents.FOOD).value();
	}
}
