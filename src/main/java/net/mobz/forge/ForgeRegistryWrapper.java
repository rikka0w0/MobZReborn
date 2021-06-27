package net.mobz.forge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.mobz.common.BlockDefinition;
import net.mobz.common.EntityDefinition;
import net.mobz.common.IRegistrable;
import net.mobz.common.IRegistryWrapper;

public class ForgeRegistryWrapper implements IRegistryWrapper {
	private final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MobZ.MODID);
	private final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobZ.MODID);
	private final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobZ.MODID);
	private final Set<EntityDefinition<?>> entityDefinitions = new HashSet<>();

	public ForgeRegistryWrapper() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public void applyGlobalEntityAttrib(BiConsumer<EntityType<? extends LivingEntity>, AttributeModifierMap> regFunc) {
		entityDefinitions.forEach((ed) -> {
			regFunc.accept(ed.entityType, ed.attribModifierSupplier.get().build());
		});
	}

	@Override
	public void register(IRegistrable object) {
		String rawName = object.Mobz$getRegistryName();
		if (object instanceof Item) {
			ITEMS.register(rawName, ()->((Item)object));
		} else if (object instanceof BlockDefinition) {
			BlockDefinition<?> bd = (BlockDefinition<?>) object;
			ITEMS.register(rawName, ()->(bd.blockItem));
			BLOCKS.register(rawName, ()->(bd.block));
		} else if (object instanceof EntityDefinition) {
			EntityDefinition<?> ed = (EntityDefinition<?>) object;
			// ResourceLocation regName = GameData.checkPrefix(rawName, true);

			ENTITY_TYPES.register(ed.getEntityName(), ()->ed.entityType);
			if (ed.spawnEggItem != null) {
				ITEMS.register(ed.getSpawnEggName(), ()->ed.spawnEggItem);
			}

			entityDefinitions.add(ed);
		}
	}
}
