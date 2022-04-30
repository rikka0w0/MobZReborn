package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.block.BossTrophy;
import net.mobz.block.EnderHeader;
import net.mobz.block.HardenedMetalblock;
import net.mobz.block.TotemBase;
import net.mobz.block.TotemMiddle;
import net.mobz.block.TotemTop;

public class MobZBlocks {
	private static final Item.Properties itemPropTabMobz = (new Item.Properties()).tab(MobZTabs.tab);

	private static final BlockBehaviour.Properties ZOMBIE_HEAD_PROP = BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F);
	private static final BlockBehaviour.Properties IRON_BLOCK_PROP = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
			.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL);
	private static final BlockBehaviour.Properties OAK_LOG_PROP = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);

	public static final Supplier<Block> AMAT_BLOCK = register("amat_block", () -> new Block(IRON_BLOCK_PROP.emissiveRendering((blockstate, world, pos) -> true)), itemPropTabMobz);
	public static final Supplier<Block> BOSS_BLOCK = register("boss_block", () -> new Block(IRON_BLOCK_PROP.emissiveRendering((blockstate, world, pos) -> true)), itemPropTabMobz);;
	public static final Supplier<BossTrophy> BOSS_TROPHY = register("bosstrophy", () -> new BossTrophy(ZOMBIE_HEAD_PROP), itemPropTabMobz);
	public static final Supplier<EnderHeader> ENDERHEADER = register("enderheader", () -> new EnderHeader(ZOMBIE_HEAD_PROP), itemPropTabMobz);
	public static final Supplier<HardenedMetalblock> HARDENED_METALBLOCK = register("hardenedmetal_block", () -> new HardenedMetalblock(IRON_BLOCK_PROP), itemPropTabMobz);

	public static final Supplier<TotemBase> TOTEM_BASE = register("totembase",  () -> new TotemBase(OAK_LOG_PROP), itemPropTabMobz);
	public static final Supplier<TotemMiddle> TOTEM_MIDDLE = register("totemmiddle", () -> new TotemMiddle(OAK_LOG_PROP), itemPropTabMobz);
	public static final Supplier<TotemTop> TOTEM_TOP = register("totemtop", () -> new TotemTop(OAK_LOG_PROP), itemPropTabMobz);

	private static <T extends Block> Supplier<T> register(String name, Supplier<T> constructor, Item.Properties blockItemProps) {
		return MobZ.platform.registerBlock(name, constructor, (block) -> new BlockItem(block, blockItemProps));
	}
}
