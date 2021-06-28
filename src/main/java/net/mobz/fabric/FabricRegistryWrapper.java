package net.mobz.fabric;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import net.mobz.common.IRegistryWrapper;

public class FabricRegistryWrapper implements IRegistryWrapper {
	public final static IRegistryWrapper instance = new FabricRegistryWrapper("mobz");

	private final String modId;
	public FabricRegistryWrapper(String modId) {
		this.modId = modId;
	}

	@Override
	public void register(String name, Item item) {
		ResourceLocation regName = new ResourceLocation(modId, name);
		Registry.register(Registry.ITEM, regName, item);
	}

	@Override
	public void register(String name, BlockItem blockItem) {
		ResourceLocation regName = new ResourceLocation(modId, name);
		Registry.register(Registry.ITEM, regName, blockItem);
		Registry.register(Registry.BLOCK, regName, blockItem.getBlock());
	}
	
	@Override
	public <T extends LivingEntity> void register(String name, EntityType<T> entityType,
			Supplier<MutableAttribute> attribModifierSupplier, SpawnEggItem spawnEggItem) {
		Registry.register(Registry.ENTITY_TYPE, name + "_entity", entityType);

		if (spawnEggItem != null) {
			Registry.register(Registry.ITEM, "spawn_" + name, spawnEggItem);
		}

		// FabricDefaultAttributeRegistry.register(entityType, attribModifierSupplier.get());
	}
}
