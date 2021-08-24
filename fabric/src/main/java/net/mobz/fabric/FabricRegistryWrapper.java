package net.mobz.fabric;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;

public class FabricRegistryWrapper implements IRegistryWrapper {
	private static ResourceLocation res(String name) {
		return new ResourceLocation(MobZ.MODID, name);
	}

	@Override
	public CreativeModeTab tab(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier) {
		return FabricItemGroupBuilder.build(resLoc, iconSupplier);
	}

	@Override
	public Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, res(name), item);
	}

	@Override
	public BlockItem register(String name, BlockItem blockItem) {
		ResourceLocation regName = res(name);
		Registry.register(Registry.ITEM, regName, blockItem);
		Registry.register(Registry.BLOCK, regName, blockItem.getBlock());
		return blockItem;
	}

	@Override
	public <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
		return Registry.register(Registry.ENTITY_TYPE, res(name), entityType);
	}

	@Override
	public <T extends LivingEntity> EntityType<T> entityAttribModifier(EntityType<T> entityType,
			Supplier<Builder> attribModifierSupplier) {
		FabricDefaultAttributeRegistry.register(entityType, attribModifierSupplier.get());
		return entityType;
	}

	@Override
	public SoundEvent register(String name, SoundEvent sound) {
		return Registry.register(Registry.SOUND_EVENT, res(name), sound);
	}
}
