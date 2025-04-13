package net.mobz.init;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.material.Fluids;

import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.MobZRarity;
import net.mobz.data.JukeboxSongs;
import net.mobz.item.FrozenPowder;
import net.mobz.item.ImmunityOrb;
import net.mobz.item.LevitationOrb;
import net.mobz.item.PillagerStaff;
import net.mobz.item.SacrificeKnife;
import net.mobz.item.LilithBow;
import net.mobz.item.Shield;
import net.mobz.item.SimpleItem;
import net.mobz.item.WhiteBag;
import net.mobz.item.WitherPowder;

public class MobZItems {
	public static final FoodProperties FOOD_ROTTEN_FLESH = (new FoodProperties.Builder())
			.nutrition(5).saturationModifier(0.8F)
			.build();
	public static final Consumable CONSUMABLE_ROTTEN_FLESH = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.5F))
			.build();

	// Items
	public static final Supplier<SimpleItem> AMAT_INGOT =
			registerItem("amat_ingot", SimpleItem.ofTier(MobZRarity.RARE));
	public static final Supplier<SimpleItem> BEAR_LEATHER =
			registerItem("bear_leather", SimpleItem.ofTier(MobZRarity.COMMON));
	public static final Supplier<SimpleItem> BOSS_INGOT =
			registerItem("boss_ingot", SimpleItem.ofTier(MobZRarity.EPIC)
					.compose(props -> props.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

	public static final Supplier<FrozenPowder> FROZEN_POWDER =
			registerItem("frozen_power", FrozenPowder::new);
	public static final Supplier<SimpleItem> HARDENEDMETAL_INGOT =
			registerItem("hardened_metal_ingot", SimpleItem.ofTier(MobZRarity.UNCOMMON));

	public static final Supplier<ImmunityOrb> IMMUNITY_ORB =
			registerItem("immunity_orb", (props) -> new ImmunityOrb(
					props.stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<LevitationOrb> LEVITATION_ORB =
			registerItem("levitation_orb", (props) -> new LevitationOrb(
					props.durability(161).stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
	public static final Supplier<PillagerStaff> PILLAGER_STAFF =
			registerItem("pillager_staff", (props) -> new PillagerStaff(props.stacksTo(1)));
	public static final Supplier<SimpleItem> ROTTEN_FLESH =
			registerItem("rotten_flesh", SimpleItem.ofTier(MobZRarity.COMMON).compose(
					(props) -> props.food(FOOD_ROTTEN_FLESH, CONSUMABLE_ROTTEN_FLESH)));
	public static final Supplier<SacrificeKnife> SACRIFICE_KNIFE =
			registerItem("sacrifice_knife", SacrificeKnife::new);

	public static final Supplier<LilithBow> LILITH_BOW =
			registerItem("lilith_bow", (props) -> new LilithBow(props.durability(461)));
	public static final Supplier<SimpleItem> SEAL_KEY =
			registerItem("seal_key", SimpleItem.ofTier(MobZRarity.RARE).compose(props -> props.stacksTo(1)));
	public static final Supplier<Shield> SHIELD =
			registerItem("shield", props -> new Shield(props
				.durability(589)
				.repairable(ItemTags.WOODEN_TOOL_MATERIALS)
				.equippableUnswappable(EquipmentSlot.OFFHAND)
				.component(DataComponents.BLOCKS_ATTACKS,
					new BlocksAttacks(0.25F, 1.0F,
						List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
						new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
						Optional.of(DamageTypeTags.BYPASSES_SHIELD),
						Optional.of(SoundEvents.SHIELD_BLOCK),
						Optional.of(SoundEvents.SHIELD_BREAK)
					)
				)
				.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
			));
	public static final Supplier<Item> SPAWN_EGG =
			MobZ.platform.registerItem("spawn_egg", null, Item::new, null);
	public static final Supplier<WhiteBag> WHITE_BAG =
			registerItem("white_bag", (props) -> new WhiteBag(props.stacksTo(1)));

	public static final Supplier<WitherPowder> WITHER_POWDER =
			registerItem("wither_powder", WitherPowder::new);
	public static final Supplier<Item> MEDIVEAL_DISC =
			registerItem("mediveal_disc", (props) ->
				new Item(props.stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(JukeboxSongs.MEDIVEAL_SONG)));
	public static final Supplier<Item> MEDIVEAL_DISC_2 =
			registerItem("mediveal_disc_2", (props) ->
				new Item(props.stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(JukeboxSongs.MEDIVEAL_SONG_2)));
	public static final Supplier<MobBucketItem> TADPOLE_BUCKET = registerItem("tadpole_bucket", (props) ->
			MobZ.platform.newMobBucketItem(MobZEntities.TADPOLE, () -> Fluids.WATER,
					() -> SoundEvents.BUCKET_EMPTY_FISH, props.stacksTo(1)).get());

	public static <T extends Item> Supplier<T> registerItem(String name, Function<Item.Properties, T> constructor) {
		return MobZ.platform.registerItem(name, MobZTabs.MAIN, constructor, null);
	}
}