package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.mobz.common.IRegistryWrapper;
import net.mobz.common.MobZ;

public class ForgeRegistryWrapper implements IRegistryWrapper {
	private final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobZ.MODID);
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobZ.MODID);
	private final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobZ.MODID);
	private final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MobZ.MODID);
	private final Set<Pair<EntityType<? extends LivingEntity>, Supplier<MutableAttribute>>> attribSuppliers = new HashSet<>();

	public ForgeRegistryWrapper() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public void applyGlobalEntityAttrib(BiConsumer<EntityType<? extends LivingEntity>, AttributeModifierMap> regFunc) {
		attribSuppliers.forEach((pair) -> {
			regFunc.accept(pair.getLeft(), pair.getRight().get().build());
		});
	}

	@Override
	public void register(String name, Item item) {
		ITEMS.register(name, ()->item);
	}

	@Override
	public void register(String name, BlockItem blockItem) {
		ITEMS.register(name, ()->blockItem);
		BLOCKS.register(name, ()->blockItem.getBlock());
	}

	@Override
	public <T extends LivingEntity> void register(String name, EntityType<T> entityType,
			Supplier<MutableAttribute> attribModifierSupplier, SpawnEggItem spawnEggItem) {
		ENTITY_TYPES.register(name + "_entity", ()->entityType);
		if (spawnEggItem != null) {
			ITEMS.register("spawn_" + name, ()->spawnEggItem);
		}

		if (attribModifierSupplier != null) {
			attribSuppliers.add(Pair.of(entityType, attribModifierSupplier));
		}
	}

	@Override
	public void register(String name, SoundEvent sound) {
		SOUNDS.register(name, ()->sound);
	}
}
