package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;

public class ForgeRegistryWrapper implements IRegistryWrapper {
	private final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobZ.MODID);
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobZ.MODID);
	private final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobZ.MODID);
	private final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MobZ.MODID);

	private Set<Pair<EntityType<? extends LivingEntity>, Supplier<Builder>>> attribSuppliers = new HashSet<>();

	public ForgeRegistryWrapper() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public void applyGlobalEntityAttrib(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> regFunc) {
		attribSuppliers.forEach((pair) -> {
			regFunc.accept(pair.getLeft(), pair.getRight().get().build());
		});
		attribSuppliers.clear();
		attribSuppliers = null;
	}

	@Override
	public CreativeModeTab tab(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		return new CreativeModeTab(resLoc.getNamespace() + "." + resLoc.getPath()) {
			public ItemStack makeIcon() {
				return iconSupplier.get();
			}
		};
	}

	@Override
	public Item register(String name, Item item) {
		ITEMS.register(name, ()->item);
		return item;
	}

	@Override
	public BlockItem register(String name, BlockItem blockItem) {
		ITEMS.register(name, ()->blockItem);
		BLOCKS.register(name, ()->blockItem.getBlock());
		return blockItem;
	}

	@Override
	public <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
		ENTITY_TYPES.register(name, ()->entityType);
		return entityType;
	}

	@Override
	public <T extends LivingEntity> EntityType<T> entityAttribModifier(EntityType<T> entityType,
			Supplier<Builder> attribModifierSupplier) {
		attribSuppliers.add(Pair.of(entityType, attribModifierSupplier));
		return entityType;
	}

	@Override
	public SoundEvent register(String name, SoundEvent sound) {
		SOUNDS.register(name, ()->sound);
		return sound;
	}
}
