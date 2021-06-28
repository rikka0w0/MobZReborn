package net.mobz.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.mobz.init.MobZArmors;

public class BossArmorBase extends ArmorItem {
    public BossArmorBase(IArmorMaterial material, EquipmentSlotType slot, Item.Properties properties) {
        super(material, slot, properties);
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.boss_armor.tooltip"));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        EffectInstance spd = new EffectInstance(Effects.MOVEMENT_SPEED, 9, 0, false, false);
        EffectInstance str = new EffectInstance(Effects.DAMAGE_BOOST, 9, 0, false, false);
        if (bob.getItemBySlot(EquipmentSlotType.FEET).sameItem(new ItemStack(MobZArmors.boss_boots))
                && bob.getItemBySlot(EquipmentSlotType.LEGS).sameItem(new ItemStack(MobZArmors.boss_leggings))
                && bob.getItemBySlot(EquipmentSlotType.CHEST).sameItem(new ItemStack(MobZArmors.boss_chestplate))
                && bob.getItemBySlot(EquipmentSlotType.HEAD).sameItem(new ItemStack(MobZArmors.boss_helmet))
                && !world.isClientSide) {
            bob.addEffect(str);
            if (bob.isSprinting()) {
                bob.addEffect(spd);
            }
        }
    }

}
