package net.mobz.client;

import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.mobz.init.MobZItems;

public class VanillaClientRegistry {
	public static void registerItemModelProperties() {
		// Ref: https://mcreator.net/forum/74855/tutorial-how-animate-custom-bow-pulling-116
		ItemModelsProperties.register(MobZItems.SBOW, new ResourceLocation("pull"), 
				(itemstack, world, entity) -> {
					if (entity == null) {
						return 0.0F;
					} else {
						return entity.getUseItem() != itemstack ? 0.0F : (float) (itemstack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
					}
				}
			);

		ItemModelsProperties.register(MobZItems.SBOW, new ResourceLocation("pulling"),
				(itemstack, world, entity) -> {
					return entity != null && entity.isUsingItem() && entity.getUseItem() == itemstack ? 1.0F : 0.0F;
				}
			);
	}
}
