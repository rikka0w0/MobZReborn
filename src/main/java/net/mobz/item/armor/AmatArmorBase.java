package net.mobz.item.armor;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mobz.init.MobZArmors;

public class AmatArmorBase extends ArmorItem {
    double attackSpeedBonus = 0.1D;
    private static final UUID[] MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };

    public AmatArmorBase(ArmorMaterial material, ArmorItem.Type armorItemType, Item.Properties properties) {
        super(material, armorItemType, properties);
    }

    @Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.amat_armor.tooltip"));
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Multimap<Attribute, AttributeModifier> multimap_1 = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot));
		if (equipmentSlot == this.getEquipmentSlot()) {
			multimap_1.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIERS[equipmentSlot.getIndex()],
					"amatattackbonus", this.attackSpeedBonus, AttributeModifier.Operation.ADDITION));
		}

		return multimap_1;
	}

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        MobEffectInstance fireResistance = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9, 0, false, false);
        if (bob.getItemBySlot(EquipmentSlot.FEET).is(MobZArmors.amat_boots.get())
                && bob.getItemBySlot(EquipmentSlot.LEGS).is(MobZArmors.amat_leggings.get())
                && bob.getItemBySlot(EquipmentSlot.CHEST).is(MobZArmors.amat_chestplate.get())
                && bob.getItemBySlot(EquipmentSlot.HEAD).is(MobZArmors.amat_helmet.get())
                && !world.isClientSide) {
            bob.addEffect(fireResistance);
        }
    }
}
