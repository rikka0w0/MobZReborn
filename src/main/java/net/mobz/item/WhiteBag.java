package net.mobz.item;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;


public class WhiteBag extends SimpleItem {
    public WhiteBag(Properties settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        Random random = new Random();
        int randomNumber = (random.nextInt() + 7) % 4;
        if (randomNumber < 0) {
            randomNumber = randomNumber * (-1);
        }
        if (!world.isClientSide) {
            switch (randomNumber) {
                case 0:
                    return new ItemStack(MobZWeapons.Axe);
                case 1:
                    return new ItemStack(MobZWeapons.Sword);
                case 2:
                    return new ItemStack(MobZItems.ORB_2);
                case 3:
                    return new ItemStack(MobZItems.BOSS_INGOT, 2);
                default:
                    return stack;
            }
        }

        return stack;

    }

    @Override
    public int getUseDuration(ItemStack itemStack_1) {
        return 1;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
    	playerEntity.startUsingItem(hand);
        return new ActionResult<>(ActionResultType.SUCCESS, playerEntity.getItemInHand(hand));
    }
}