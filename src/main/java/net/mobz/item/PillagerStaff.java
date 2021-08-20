package net.mobz.item;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.UseAnim;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.mobz.block.EnderHeader;
import net.mobz.entity.Withender;
import net.mobz.init.MobZBlocks;
import net.mobz.init.MobZEntities;

public class PillagerStaff extends SimpleItem {
	public PillagerStaff(Item.Properties properties) {
        super(properties);
    }
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		Player player = context.getPlayer();

        if (world.isClientSide) {
            return InteractionResult.PASS;
        } else {
            BlockState state = world.getBlockState(context.getClickedPos());
          
            if (state.getBlock() == MobZBlocks.ENDERHEADER) {
                if (EnderHeader.isValid(world, context.getClickedPos(), state)) {
                    Withender wither = (Withender) MobZEntities.WITHENDER.create(world);
                    BlockPos oke = context.getClickedPos();
                    wither.moveTo(oke, 0.0F, 0.0F);
                    world.addFreshEntity(wither);
                    return InteractionResult.SUCCESS;
                } else {
                	// TODO: check this
                    player.sendMessage(new TranslatableComponent("text.mobz.withendermissing"), player.getUUID());
                }
            }

            return InteractionResult.PASS;
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 600;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (user instanceof Player) {
            Player playerEntity = (Player) user;
            if (!playerEntity.isCrouching()) {
                Random random = new Random();
                Random random2 = new Random();
                Random random3 = new Random();
                double z1 = (random.nextInt() % 50);
                double z2 = z1 / 100;
                double z3 = random2.nextInt() % 50;
                double z4 = z3 / 100;
                double z5 = random3.nextInt() % 180;
                double z6 = z5 / 100;
                Vec3 vec3d_1 = playerEntity.getViewVector(1.0F);
                double double_3 = vec3d_1.x;
                double double_4 = vec3d_1.y;
                double double_5 = vec3d_1.z;
                WitherSkull skull1 = new WitherSkull(world, playerEntity, double_3, double_4, double_5);
                skull1.setPos(playerEntity.getX() + vec3d_1.x, playerEntity.getY() + 1.2D,
                        playerEntity.getZ() + vec3d_1.z);
                world.addFreshEntity(skull1);
                playerEntity.hurt(DamageSource.WITHER, 2F);
                playerEntity.playSound(SoundEvents.WITHER_HURT, 1F, 1F);
                if (world.isClientSide) {
                    for (int i = 0; i < 16; ++i) {
                        world.addParticle(ParticleTypes.MYCELIUM, playerEntity.getX() + z2,
                                playerEntity.getY() + z6, playerEntity.getZ() + z4, 0D, 0D, 0D);
                    }
                }
                return InteractionResultHolder.success(user.getItemInHand(hand));
            } else {
                return InteractionResultHolder.pass(user.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }
}