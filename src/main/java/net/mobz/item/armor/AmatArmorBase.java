package net.mobz.item.armor;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
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

public class AmatArmorBase extends ArmorItem {
    double attackSpeedBonus = 0.1D;
    private static final UUID[] MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };

    public AmatArmorBase(IArmorMaterial material, EquipmentSlotType slot, Item.Properties properties) {
        super(material, slot, properties);
    }

    @Override
	public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.mobz.amat_armor.tooltip"));
    }

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot_1) {
		Multimap<Attribute, AttributeModifier> multimap_1 = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot_1));
		if (equipmentSlot_1 == this.slot) {
			multimap_1.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MODIFIERS[equipmentSlot_1.getIndex()],
					"amatattackbonus", this.attackSpeedBonus, AttributeModifier.Operation.ADDITION));
		}

		return multimap_1;
	}

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        EffectInstance fireResistance = new EffectInstance(Effects.FIRE_RESISTANCE, 9, 0, false, false);
        if (bob.getItemBySlot(EquipmentSlotType.FEET).sameItem(new ItemStack(MobZArmors.amat_boots))
                && bob.getItemBySlot(EquipmentSlotType.LEGS).sameItem(new ItemStack(MobZArmors.amat_leggings))
                && bob.getItemBySlot(EquipmentSlotType.CHEST).sameItem(new ItemStack(MobZArmors.amat_chestplate))
                && bob.getItemBySlot(EquipmentSlotType.HEAD).sameItem(new ItemStack(MobZArmors.amat_helmet))
                && !world.isClientSide) {
            bob.addEffect(fireResistance);
        }
    }
}
