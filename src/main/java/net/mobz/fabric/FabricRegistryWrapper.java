package net.mobz.fabric;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.mobz.common.BlockDefinition;
import net.mobz.common.EntityDefinition;
import net.mobz.common.IRegistrable;
import net.mobz.common.IRegistryWrapper;

public class FabricRegistryWrapper implements IRegistryWrapper {
	public final static IRegistryWrapper instance = new FabricRegistryWrapper("mobz");

	private final String modId;
	public FabricRegistryWrapper(String modId) {
		this.modId = modId;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void register(IRegistrable object) {
		ResourceLocation name = new ResourceLocation(modId, object.Mobz$getRegistryName());
		if (object instanceof Item) {
			Registry.register(Registry.ITEM, name, (Item)object);
		} else if (object instanceof BlockDefinition) {
			BlockDefinition<?> bd = (BlockDefinition<?>) object;
			Registry.register(Registry.ITEM, name, bd.blockItem);
			Registry.register(Registry.BLOCK, name, bd.block);
		} else if (object instanceof EntityDefinition) {
			EntityDefinition<?> ed = (EntityDefinition<?>) object;
			
			Registry.register(Registry.ENTITY_TYPE, ed.getEntityName(), ed.entityType);
			if (ed.spawnEggItem != null) {
				Registry.register(Registry.ITEM, ed.getSpawnEggName(), ed.spawnEggItem);
			}

			// FabricDefaultAttributeRegistry.register(ed.entityType, ed.attribModifierSupplier.get());
		}
	}
}
