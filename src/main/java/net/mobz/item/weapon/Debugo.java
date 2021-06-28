package net.mobz.item.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.world.World;

public class Debugo extends SwordItem {
    public Debugo(IItemTier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, 6.0f, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        PlayerEntity ok = (PlayerEntity) bob;
        if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7
                || slot == 8 && !world.isClientSide) {
            ok.abilities.mayfly = true;
        }
    }

}
