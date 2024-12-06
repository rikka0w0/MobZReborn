package net.mobz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.mobz.init.MobZItems;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	/**
	 *
	 * @param item
	 * @return true if the item should be considered as a shield
	 */
	@ModifyExpressionValue(
			allow = 1,
			require = 1,
			method = "isDamageSourceBlocked(Lnet/minecraft/world/damagesource/DamageSource;)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;")
		)
	private Item instanceOfShieldItem(Item item) {
		return item == Items.SHIELD || item == MobZItems.SHIELD.get() ? Items.SHIELD : Items.AIR;
	}
}
