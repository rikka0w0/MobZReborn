package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.Level;
import net.mobz.Configs;
import net.mobz.init.MobZWeapons;

public class IslandVexEntity extends Vex {

  public IslandVexEntity(EntityType<? extends Vex> entityType, Level world) {
    super(entityType, world);

  }

  public static AttributeSupplier.Builder createIslandVexEntityAttributes() {
    return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,
            Configs.instance.DeathSpiritLife * Configs.instance.LifeMultiplicatorMob)
        .add(Attributes.ATTACK_DAMAGE,
            Configs.instance.DeathSpiritAttack * Configs.instance.DamageMultiplicatorMob)
        .add(Attributes.FOLLOW_RANGE, 18.0D);
  }

  @Override
  protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
    this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MobZWeapons.ArmoredSword));
    this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
  }

}