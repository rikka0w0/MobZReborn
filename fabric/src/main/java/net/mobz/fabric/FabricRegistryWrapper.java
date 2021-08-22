package net.mobz.fabric;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;

public class FabricRegistryWrapper implements IRegistryWrapper {
	public final static IRegistryWrapper instance = new FabricRegistryWrapper();
	private FabricRegistryWrapper() {}

	private static ResourceLocation res(String name) {
		return new ResourceLocation(MobZ.MODID, name);
	}

	@Override
	public void register(String name, Item item) {
		Registry.register(Registry.ITEM, res(name), item);
	}

	@Override
	public void register(String name, BlockItem blockItem) {
		ResourceLocation regName = res(name);
		Registry.register(Registry.ITEM, regName, blockItem);
		Registry.register(Registry.BLOCK, regName, blockItem.getBlock());
	}

	@Override
	public <T extends LivingEntity> void register(String name, EntityType<T> entityType,
			Supplier<AttributeSupplier.Builder> attribModifierSupplier, SpawnEggItem spawnEggItem) {
		Registry.register(Registry.ENTITY_TYPE, res(name + "_entity"), entityType);

		if (spawnEggItem != null) {
			Registry.register(Registry.ITEM, res("spawn_" + name), spawnEggItem);
		}

		FabricDefaultAttributeRegistry.register(entityType, attribModifierSupplier.get());
	}

	@Override
	public void register(String name, SoundEvent sound) {
		Registry.register(Registry.SOUND_EVENT, res(name), sound);
	}
}
