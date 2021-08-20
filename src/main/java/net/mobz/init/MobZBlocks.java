package net.mobz.init;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;
import net.mobz.block.BossTrophy;
import net.mobz.block.EnderHeader;
import net.mobz.block.HardenedMetalblock;
import net.mobz.block.TotemBase;
import net.mobz.block.TotemMiddle;
import net.mobz.block.TotemTop;

public class MobZBlocks {
	private static final BlockBehaviour.Properties ZOMBIE_HEAD_PROP = BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F);
	private static final BlockBehaviour.Properties IRON_BLOCK_PROP = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
			.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL);
	private static final BlockBehaviour.Properties OAK_LOG_PROP = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);

	public static final Block AMAT_BLOCK = new Block(IRON_BLOCK_PROP.emissiveRendering((blockstate, world, pos) -> true));
	public static final Block BOSS_BLOCK = new Block(IRON_BLOCK_PROP.emissiveRendering((blockstate, world, pos) -> true));
	public static final BossTrophy BOSS_TROPHY = new BossTrophy(ZOMBIE_HEAD_PROP);
	public static final EnderHeader ENDERHEADER = new EnderHeader(ZOMBIE_HEAD_PROP);
	public static final HardenedMetalblock HARDENED_METALBLOCK = new HardenedMetalblock(IRON_BLOCK_PROP);
    public static final TotemBase TOTEM_BASE = new TotemBase(OAK_LOG_PROP);
    public static final TotemMiddle TOTEM_MIDDLE = new TotemMiddle(OAK_LOG_PROP);
    public static final TotemTop TOTEM_TOP = new TotemTop(OAK_LOG_PROP);

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("amat_block", AMAT_BLOCK, MobZ.tab);
		registry.register("boss_block", BOSS_BLOCK, MobZ.tab);
		registry.register("bosstrophy", BOSS_TROPHY, MobZ.tab);
		registry.register("enderheader", ENDERHEADER, MobZ.tab);
		registry.register("hardenedmetal_block", HARDENED_METALBLOCK, MobZ.tab);

		registry.register("totembase", TOTEM_BASE, MobZ.tab);
		registry.register("totemmiddle", TOTEM_MIDDLE, MobZ.tab);
		registry.register("totemtop", TOTEM_TOP, MobZ.tab);
	}
}
