package net.mobz.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.mobz.Configs;
import net.mobz.init.MobZWeapons;

public class IslandVexEntity extends VexEntity {

  public IslandVexEntity(EntityType<? extends VexEntity> entityType, World world) {
    super(entityType, world);

  }

  public static AttributeModifierMap.MutableAttribute createIslandVexEntityAttributes() {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.DeathSpiritLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.ATTACK_DAMAGE,
            Configs.instance.DeathSpiritAttack * Configs.instance.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 18.0D);
  }

  @Override
  protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
    this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
    this.setDropChance(EquipmentSlotType.MAINHAND, 0.0F);
  }

}