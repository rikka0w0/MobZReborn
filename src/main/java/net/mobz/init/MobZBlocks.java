package net.mobz.init;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.MobZRarity;
import net.mobz.block.BossTrophy;
import net.mobz.block.EnderHeader;
import net.mobz.block.HardenedMetalBlock;
import net.mobz.block.TotemBase;
import net.mobz.block.TotemMiddle;
import net.mobz.block.TotemTop;
import net.mobz.item.BlockItemWithTooltip;

public class MobZBlocks {
	// See https://gist.github.com/GizmoTheMoonPig/77a90a48e0aeecd15b4c524e1c7f0a4a
	private static BlockBehaviour.Properties zombie_head(BlockBehaviour.Properties props) {
		// was Material.DECORATION
		return props.strength(1.0F);
	}

	private static BlockBehaviour.Properties metal(BlockBehaviour.Properties props) {
		// was Material.METAL, MaterialColor.METAL
		return props.mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL);
	}

	private static BlockBehaviour.Properties oak_log(BlockBehaviour.Properties props) {
		// was Material.WOOD
		return props.strength(2.0F).mapColor(MapColor.WOOD).ignitedByLava().sound(SoundType.WOOD);
	}

	public static final Supplier<Block> AMAT_BLOCK = register("amat_block",
			(props) -> new Block(metal(props).emissiveRendering((blockstate, world, pos) -> true)),
			(block, props) -> new BlockItemWithTooltip(block, props, MobZRarity.UNCOMMON::addToTooltip));

	public static final Supplier<Block> BOSS_BLOCK = register("boss_block",
			(props) -> new Block(metal(props).emissiveRendering((blockstate, world, pos) -> true)),
			(block, props) -> new BlockItemWithTooltip(block, props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true), MobZRarity.LEGENDARY::addToTooltip));

	public static final Supplier<BossTrophy> BOSS_TROPHY = register("boss_trophy",
			(props) -> new BossTrophy(zombie_head(props)));
	public static final Supplier<EnderHeader> ENDER_HEADER = register("ender_header",
			(props) -> new EnderHeader(zombie_head(props)));
	public static final Supplier<HardenedMetalBlock> HARDENED_METAL_BLOCK = register("hardened_metal_block",
			(props) -> new HardenedMetalBlock(metal(props)),
			(block, props) -> new BlockItemWithTooltip(block, props, MobZRarity.UNCOMMON::addToTooltip));

	public static final Supplier<TotemBase> TOTEM_BASE = register("totem_base",
			(props) -> new TotemBase(oak_log(props)),
			(block, props) -> new BlockItemWithTooltip(block, props, MobZRarity.RARE::addToTooltip));
	public static final Supplier<TotemMiddle> TOTEM_MIDDLE = register("totem_middle",
			(props) -> new TotemMiddle(oak_log(props)),
			(block, props) -> new BlockItemWithTooltip(block, props, MobZRarity.COMMON::addToTooltip));
	public static final Supplier<TotemTop> TOTEM_TOP = register("totem_top",
			(props) -> new TotemTop(oak_log(props)),
			(block, props) -> new BlockItemWithTooltip(block, props, MobZRarity.RARE::addToTooltip));

	// The unused treasure_block is RARE

	private static <T extends Block> Supplier<T> register(String name,
			Function<BlockBehaviour.Properties, T> constructor) {
		return register(name, constructor, UnaryOperator.identity());
	}

	private static <T extends Block> Supplier<T> register(String name,
			Function<BlockBehaviour.Properties, T> constructor,
			UnaryOperator<Item.Properties> blockItemProps) {
		return register(name, constructor, (block, props) -> new BlockItem(block, blockItemProps.apply(props)));
	}

	private static <T extends Block> Supplier<T> register(String name,
			Function<BlockBehaviour.Properties, T> constructor,
			BiFunction<T, Item.Properties, BlockItem> blockItemConstructor) {
		return MobZ.platform.registerBlock(name, MobZTabs.MAIN, constructor, blockItemConstructor, null);
	}
}
