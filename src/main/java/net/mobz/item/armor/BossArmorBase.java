package net.mobz.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.mobz.init.MobZArmors;

public class BossArmorBase extends ArmorItem {
    public BossArmorBase(ArmorMaterial material, EquipmentSlot slot, Item.Properties properties) {
        super(material, slot, properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent("item.mobz.boss_armor.tooltip"));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        MobEffectInstance spd = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 9, 0, false, false);
        MobEffectInstance str = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 9, 0, false, false);
        if (bob.getItemBySlot(EquipmentSlot.FEET).sameItem(new ItemStack(MobZArmors.boss_boots.get()))
                && bob.getItemBySlot(EquipmentSlot.LEGS).sameItem(new ItemStack(MobZArmors.boss_leggings.get()))
                && bob.getItemBySlot(EquipmentSlot.CHEST).sameItem(new ItemStack(MobZArmors.boss_chestplate.get()))
                && bob.getItemBySlot(EquipmentSlot.HEAD).sameItem(new ItemStack(MobZArmors.boss_helmet.get()))
                && !world.isClientSide) {
            bob.addEffect(str);
            if (bob.isSprinting()) {
                bob.addEffect(spd);
            }
        }
    }

}
