package net.mobz.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mobz.init.MobZItems;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Redirect(method = {"hurtCurrentlyUsedShield(F)V"}, at = @At(value = "INVOKE", ordinal = 0,
			target = "net/minecraft/world/item/ItemStack.is(Lnet/minecraft/world/item/Item;)Z"))
	private boolean isShield(ItemStack stack, Item item) {
		return stack.is(item) || stack.is(MobZItems.SHIELD);
	}
}
