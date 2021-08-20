package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
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
			Supplier<Builder> attribModifierSupplier, SpawnEggItem spawnEggItem) {
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
