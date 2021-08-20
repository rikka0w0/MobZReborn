package net.mobz.item.weapon;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class Debugo extends SwordItem {
    public Debugo(Tier IItemTier_1, Item.Properties properties) {
        super(IItemTier_1, 1, 6.0f, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        LivingEntity bob = (LivingEntity) entity;
        Player ok = (Player) bob;
        if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7
                || slot == 8 && !world.isClientSide) {
            ok.abilities.mayfly = true;
        }
    }

}
