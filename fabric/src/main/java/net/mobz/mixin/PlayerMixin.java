package net.mobz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.mobz.init.MobZItems;

@Mixin(Player.class)
public abstract class PlayerMixin {
	/**
	 * <blockquote>
	 *
	 * <pre>
	 *     protected void hurtCurrentlyUsedShield(float amount) {
	 * -       if (this.useItem.is(Items.SHIELD))
	 * +       if (hurtCurrentlyUsedShield(this.useItem.is(Items.SHIELD), amount))
	 * </pre>
	 *
	 * </blockquote>
	 */
	@ModifyExpressionValue(
			allow = 1,
			require = 1,
			method = "hurtCurrentlyUsedShield(F)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
		)
	private boolean hurtCurrentlyUsedShield(boolean isVanillaShield) {
		Player player = (Player) (Object) this;
		ItemStack activeItemStack = player.getUseItem();
		return isVanillaShield || activeItemStack.is(MobZItems.SHIELD.get());
	}
}
