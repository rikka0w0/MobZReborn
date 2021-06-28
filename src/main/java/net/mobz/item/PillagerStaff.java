package net.mobz.item;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class PillagerStaff extends SimpleItem {
	public PillagerStaff(Item.Properties properties) {
        super(properties);
    }
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();

        if (world.isClientSide) {
            return ActionResultType.PASS;
        } else {
            BlockState state = world.getBlockState(context.getClickedPos());
            // TODO: Impl this
            /*
            if (state.getBlock() == Blockinit.ENDERHEADER) {

                if (Enderheader.isValid(world, context.getClickedPos(), state)) {
                    Withender wither = (Withender) Entityinit.WITHENDER.create(world);
                    BlockPos oke = context.getClickedPos();
                    wither.refreshPositionAndAngles(oke, 0.0F, 0.0F);
                    world.spawnEntity(wither);
                    return ActionResultType.SUCCESS;
                } else {
                	// TODO: check this
                    player.sendMessage(new TranslationTextComponent("text.mobz.withendermissing"), player.getUUID());
                }
            }
            */
            return ActionResultType.PASS;
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 600;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
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
                Vector3d vec3d_1 = playerEntity.getViewVector(1.0F);
                double double_3 = vec3d_1.x;
                double double_4 = vec3d_1.y;
                double double_5 = vec3d_1.z;
                WitherSkullEntity skull1 = new WitherSkullEntity(world, playerEntity, double_3, double_4, double_5);
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
                return ActionResult.success(user.getItemInHand(hand));
            } else {
                return ActionResult.pass(user.getItemInHand(hand));
            }
        }
        return ActionResult.pass(user.getItemInHand(hand));
    }
}