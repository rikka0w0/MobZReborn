package net.mobz.item.armor;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.mobz.MobZ;
import net.mobz.init.MobZArmors;
import net.mobz.init.MobZItems;

public class BossArmorBase extends ArmorItem {
	// boots, leggings, chestplate, helmet
	// DurabilityBase was { 18, 20, 22, 16 } * 25
	// Vanilla base: {13, 15, 16, 11};
	public static final EnumMap<Type, Integer> DURABILITY_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class),
		(map) -> {
			map.put(ArmorItem.Type.BOOTS, 35);
			map.put(ArmorItem.Type.LEGGINGS, 35);
			map.put(ArmorItem.Type.CHESTPLATE, 35);
			map.put(ArmorItem.Type.HELMET, 35);
	});

	public static final EnumMap<Type, Integer> DEFENSE_MAP = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 4);
		map.put(ArmorItem.Type.LEGGINGS, 7);
		map.put(ArmorItem.Type.CHESTPLATE, 9);
		map.put(ArmorItem.Type.HELMET, 4);
	});

	public static final Holder<ArmorMaterial> MATERIAL = Registry.registerForHolder(
		BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(MobZ.MODID, "boss"), new ArmorMaterial(
			DEFENSE_MAP,
       		25,		// getEnchantmentValue
      		SoundEvents.ARMOR_EQUIP_GOLD,
       		()->Ingredient.of(MobZItems.BOSS_INGOT.get()),
       		List.of(
   				new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "boss")),
    			new ArmorMaterial.Layer(ResourceLocation.tryBuild("mobz", "boss"))
   			),
       		2,	// getToughness
       		0	// getKnockbackResistance
		)
	);

    public BossArmorBase(ArmorItem.Type armorItemType, Item.Properties properties) {
        super(MATERIAL, armorItemType, properties.durability(DURABILITY_MAP.get(armorItemType)));
    }

	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.mobz.boss_armor.tooltip"));
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
        if (bob.getItemBySlot(EquipmentSlot.FEET).is(MobZArmors.BOSS_BOOTS.get())
                && bob.getItemBySlot(EquipmentSlot.LEGS).is(MobZArmors.BOSS_LEGGINGS.get())
                && bob.getItemBySlot(EquipmentSlot.CHEST).is(MobZArmors.BOSS_CHESTPLATE.get())
                && bob.getItemBySlot(EquipmentSlot.HEAD).is(MobZArmors.BOSS_HELMET.get())
                && !world.isClientSide) {
            bob.addEffect(str);
            if (bob.isSprinting()) {
                bob.addEffect(spd);
            }
        }
    }

}
