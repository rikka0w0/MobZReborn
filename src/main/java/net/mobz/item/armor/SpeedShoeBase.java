package net.mobz.item.armor;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

public class SpeedShoeBase extends ArmorItem {
    private final double speedBoost;
    private static final UUID[] MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };

    public SpeedShoeBase(ArmorMaterial material, ArmorItem.Type armorItemType, Item.Properties properties, double speedBoost) {
        super(material, armorItemType, properties);
        this.speedBoost = speedBoost;
    }

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable("item.mobz.speed_boots.tooltip"));
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		Multimap<Attribute, AttributeModifier> multimap_1 = LinkedListMultimap.create(super.getDefaultAttributeModifiers(equipmentSlot));
		if (equipmentSlot == this.getEquipmentSlot()) {
			multimap_1.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MODIFIERS[equipmentSlot.getIndex()],
					"Speed", this.speedBoost, AttributeModifier.Operation.ADDITION));

		}

		return multimap_1;
	}
}
