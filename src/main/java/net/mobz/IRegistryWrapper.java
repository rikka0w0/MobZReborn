package net.mobz;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.mobz.item.SpawnEgg;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface IRegistryWrapper {
	CreativeModeTab tab(ResourceLocation resLoc, Supplier<ItemStack> iconSupplier);

	/**
	 * Register an Item to current namespace
	 * @param name
	 * @param item
	 */
	Item register(String name, Item item);

	/**
	 * Register an BlockItem to current namespace
	 * @param name
	 * @param blockItem Block instance is passed in the blockItem
	 */
	BlockItem register(String name, BlockItem blockItem);

	/**
	 * Block registration helper method
	 */
	default BlockItem register(String name, Block block, CreativeModeTab group) {
		return register(name, new BlockItem(block, new Item.Properties().tab(group)));
	}

	/**
	 * Block registration helper method
	 */
	default BlockItem register(String name, Block block, CreativeModeTab group, int maxStackSize) {
		return register(name, new BlockItem(block, new Item.Properties().tab(group).stacksTo(maxStackSize)));
	}

	/**
	 * Register an EntityType
	 * @param name
	 * @param entityType
	 */
	<T extends Entity> EntityType<T> register(String name, EntityType<T> entityType);

	/**
	 * Assign attribute modifier to a registered EntityType
	 * @param <T>
	 * @param entityType
	 * @param attribModifierSupplier
	 */
	<T extends LivingEntity> EntityType<T> entityAttribModifier(EntityType<T> entityType,
			Supplier<Builder> attribModifierSupplier);

	/**
	 * Register a LivingEntity type
	 * @param <T> must be a super class of LivingEntity
	 * @param name registration name, e.g. if set to "boss", name of the entity type will be "boss_entity",
	 * name of the spawn egg will be "spawn_boss".
	 * @param entityType
	 * @param attribModifierSupplier set to null to skip custom attrib modifier injection
	 * @param spawnEggItem set to null to disable spawn egg
	 */
	default <T extends LivingEntity> void register(String name, EntityType<T> entityType,
			@Nullable Supplier<AttributeSupplier.Builder> attribModifierSupplier,
			@Nullable SpawnEggItem spawnEggItem) {
		register(name, entityType);
		if (spawnEggItem != null) {
			register("spawn_" + name, spawnEggItem);
		}
		if (attribModifierSupplier != null) {
			entityAttribModifier(entityType, attribModifierSupplier);
		}
	}

	/**
	 * Entity registration helper method
	 */
	default <T extends Mob> void register(String name, EntityType<T> entityType,
			Supplier<AttributeSupplier.Builder> attribModifierSupplier,
			int eggColor1, int eggColor2, CreativeModeTab eggGroup) {
		register(name, entityType, attribModifierSupplier,
				new SpawnEgg(entityType, eggColor1, eggColor2, new Item.Properties().tab(eggGroup)));
	}

	/**
	 * Entity registration helper method
	 */
	default <T extends Mob> void register(String name, EntityType.Builder<T> entityTypeBuilder,
			Supplier<AttributeSupplier.Builder> attribModifierSupplier,
			int eggColor1, int eggColor2, CreativeModeTab eggGroup) {
		EntityType<T> entityType = entityTypeBuilder.build(name);
		register(name, entityType, attribModifierSupplier, eggColor1, eggColor2, eggGroup);
	}

	SoundEvent register(String name, SoundEvent sound);
}
