package net.mobz.common;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

public class EntityDefinition<T extends LivingEntity> implements IRegistrable {
	public final String entityRawName;
	public final EntityType<T> entityType;
	public final Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier;
	public final SpawnEggItem spawnEggItem;

	public EntityDefinition(String name, EntityType.Builder<T> entityTypeBuilder, 
			Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier,
			int eggColor1, int eggColor2, ItemGroup eggTab) {
		this.entityRawName = name;
		this.entityType = entityTypeBuilder.build(getEntityName());
		this.attribModifierSupplier = attribModifierSupplier;
		this.spawnEggItem = new SpawnEggItem(this.entityType, eggColor1, eggColor2, new Item.Properties().tab(eggTab));
	}
	
	public EntityDefinition(String name, EntityType.Builder<T> entityTypeBuilder, 
			Supplier<AttributeModifierMap.MutableAttribute> attribModifierSupplier,
			SpawnEggItem spawnEggItem) {
		this.entityRawName = name;
		this.entityType = entityTypeBuilder.build(getEntityName());
		this.attribModifierSupplier = attribModifierSupplier;
		this.spawnEggItem = spawnEggItem;
	}

	public String getSpawnEggName() {
		return "spawn_" + this.entityRawName;
	}

	public String getEntityName() {
		return this.entityRawName + "_entity";
	}

	@Override
	public String Mobz$getRegistryName() {
		return this.entityRawName; // Not used
	}
}
