package net.mobz;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.SoundEvent;

public interface IRegistryWrapper {	
	/**
	 * Register an Item to current namespace
	 * @param name
	 * @param item
	 */
	void register(String name, Item item);

	/**
	 * Register an BlockItem to current namespace 
	 * @param name
	 * @param blockItem Block instance is passed in the blockItem
	 */
	void register(String name, BlockItem blockItem);

	/**
	 * Block registration helper method
	 */
	default void register(String name, Block block, ItemGroup group) {
		register(name, new BlockItem(block, new Item.Properties().tab(group)));
	}

	/**
	 * Block registration helper method
	 */
	default void register(String name, Block block, ItemGroup group, int maxStackSize) {
		register(name, new BlockItem(block, new Item.Properties().tab(group).stacksTo(maxStackSize)));
	}

	/**
	 * Register an entity type
	 * @param <T> must be a super class of LivingEntity
	 * @param name registration name, e.g. if set to "boss", name of the entity type will be "boss_entity",
	 * name of the spawn egg will be "spawn_boss".
	 * @param entityType
	 * @param attribModifierSupplier set to null to skip custom attrib modifier injection
	 * @param spawnEggItem set to null to disable spawn egg
	 */
	<T extends LivingEntity> void register(String name, EntityType<T> entityType, 
			@Nullable Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier,
			@Nullable SpawnEggItem spawnEggItem);

	/**
	 * Entity registration helper method
	 */
	default <T extends LivingEntity> void register(String name, EntityType<T> entityType, 
			Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier,
			int eggColor1, int eggColor2, ItemGroup eggGroup) {
		register(name, entityType, attribModifierSupplier,
				new SpawnEggItem(entityType, eggColor1, eggColor2, new Item.Properties().tab(eggGroup)));
	}

	/**
	 * Entity registration helper method
	 */
	default <T extends LivingEntity> void register(String name, EntityType.Builder<T> entityTypeBuilder, 
			Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier,
			int eggColor1, int eggColor2, ItemGroup eggGroup) {
		EntityType<T> entityType = entityTypeBuilder.build(name + "_entity");
		register(name, entityType, attribModifierSupplier, eggColor1, eggColor2, eggGroup);
	}

	void register(String name, SoundEvent sound);
}
