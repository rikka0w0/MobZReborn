package net.mobz.client;

import net.minecraft.resources.ResourceLocation;
import net.mobz.IItemModelPredicateWrapper;
import net.mobz.init.MobZItems;
import net.mobz.item.SacrificeKnife;

public class VanillaClientRegistry {
	public static void registerItemModelProperties(IItemModelPredicateWrapper itemProperties) {
		// Ref: https://mcreator.net/forum/74855/tutorial-how-animate-custom-bow-pulling-116
		itemProperties.register(MobZItems.LILITH_BOW.get(), ResourceLocation.withDefaultNamespace("pull"),
				(itemstack, world, entity, seed) -> {
					if (entity == null) {
						return 0.0F;
					} else {
						return entity.getUseItem() != itemstack ? 0.0F : (itemstack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
					}
				}
			);

		itemProperties.register(MobZItems.LILITH_BOW.get(), ResourceLocation.withDefaultNamespace("pulling"),
				(itemstack, world, entity, seed) -> {
					return entity != null && entity.isUsingItem() && entity.getUseItem() == itemstack ? 1.0F : 0.0F;
				}
			);

		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("pulling"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter < 1000 && bloodCounter > 600 && dryingNumber == 1) {
						return 0.11F;
			        }
					return 0F;
				}
			);
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood2"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter < 2000 && bloodCounter >= 1400 && dryingNumber == 2) {
						return 0.22F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood3"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter < 3000 && bloodCounter >= 2000 && dryingNumber == 3) {
						return 0.33F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood4"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter > 3000 && dryingNumber == 4) {
						return 0.44F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood1dry1"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 600 && bloodCounter > 300 && dryingNumber == 1) {
						return 0.15F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood1dry2"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 300 && bloodCounter > 0 && dryingNumber == 1) {
						return 0.19F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood2dry1"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 1400 && bloodCounter > 600 && dryingNumber == 2) {
						return 0.25F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood2dry2"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 600 && bloodCounter > 0 && dryingNumber == 2) {
						return 0.29F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood3dry1"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 2000 && bloodCounter > 1000 && dryingNumber == 3) {
						return 0.35F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood3dry2"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 1000 && bloodCounter > 0 && dryingNumber == 3) {
						return 0.39F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood4dry1"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 3000 && bloodCounter > 1500 && dryingNumber == 4) {
						return 0.45F;
					}
					return 0F;
				});
		itemProperties.register(MobZItems.SACRIFICE_KNIFE.get(), ResourceLocation.withDefaultNamespace("blood4dry2"),
				(itemStack, world, entity, seed) -> {
					int bloodCounter = SacrificeKnife.getBloodCounter(itemStack);
					int dryingNumber = SacrificeKnife.getDryingNumber(itemStack);
					if (bloodCounter <= 1500 && bloodCounter > 0 && dryingNumber == 4) {
						return 0.49F;
					}
					return 0F;
				});

		itemProperties.register(MobZItems.SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (itemStack, world, entity, seed) ->
			entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);
	}
}
