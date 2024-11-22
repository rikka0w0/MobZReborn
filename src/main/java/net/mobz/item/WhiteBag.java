package net.mobz.item;

import java.util.Random;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.mobz.MobZRarity;
import net.mobz.init.MobZItems;
import net.mobz.init.MobZWeapons;

public class WhiteBag extends SimpleItem {
    public WhiteBag(Properties settings) {
        super(settings, MobZRarity.LEGENDARY);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        Random random = new Random();
        int randomNumber = (random.nextInt() + 7) % 4;
        if (randomNumber < 0) {
            randomNumber = randomNumber * (-1);
        }
        if (!world.isClientSide) {
            switch (randomNumber) {
                case 0:
                    return new ItemStack(MobZWeapons.ERAGONS_AXE.get());
                case 1:
                    return new ItemStack(MobZWeapons.RAINBOW_SWORD.get());
                case 2:
                    return new ItemStack(MobZItems.LEVITATION_ORB.get());
                case 3:
                    return new ItemStack(MobZItems.BOSS_INGOT.get(), 2);
                default:
                    return stack;
            }
        }

        return stack;

    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 1;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
    	playerEntity.startUsingItem(hand);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerEntity.getItemInHand(hand));
    }
}