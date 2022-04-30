package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SpawnEggItem;
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
	private final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobZ.MODID);
	private final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MobZ.MODID);

	private Set<Supplier<?>> setters = new HashSet<>();
	private Set<Consumer<BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier>>> attribSuppliers = new HashSet<>();

	public ForgeRegistryWrapper() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
		ENTITY_TYPES.register(eventBus);
		SOUNDS.register(eventBus);
	}

	@Override
	public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> constructor, Consumer<T> setter) {
		RegistryObject<T> regObj = ITEMS.register(name, constructor);
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
	public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> constructor,
			Function<T, BlockItem> blockItemConstructor, Consumer<T> setter) {
		RegistryObject<T> regObj = BLOCKS.register(name, constructor);
		if (setter != null) {
			setters.add(() -> {
				T val = regObj.get();
				setter.accept(val);
				return val;
			});
		}
		ITEMS.register(name, () -> blockItemConstructor.apply(regObj.get()));
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
	public Supplier<SoundEvent> registerSound(String name, ResourceLocation resloc, Consumer<SoundEvent> setter) {
		Supplier<SoundEvent> constructor = () -> new SoundEvent(resloc);
		RegistryObject<SoundEvent> regObj = SOUNDS.register(name, constructor);
		if (setter != null) {
			setters.add(() -> {
				SoundEvent val = regObj.get();
				setter.accept(val);
				return val;
			});
		}
		return regObj;
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
		return new CreativeModeTab(resLoc.getNamespace() + "." + resLoc.getPath()) {
			@Override
			public ItemStack makeIcon() {
				return iconSupplier.get();
			}
		};
	}

	@Override
	public Supplier<SpawnEggItem> spawnEggOf(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor,
			int highlightColor, Item.Properties props) {
		return () -> new ForgeSpawnEgg(type, highlightColor, highlightColor, props);
	}

	@Override
	public Supplier<RecordItem> newRecordItem(int comparatorValue, Supplier<SoundEvent> soundSupplier,
			Item.Properties builder) {
		return () -> new RecordItem(comparatorValue, soundSupplier, builder);
	}

	@Override
	public Supplier<MobBucketItem> newMobBucketItem(Supplier<? extends EntityType<?>> entitySupplier,
			Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier,
			Item.Properties properties) {
		return () -> new MobBucketItem(entitySupplier, fluidSupplier, soundSupplier, properties);
	}
}
