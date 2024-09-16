package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Fluids;

import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.data.JukeboxSongs;
import net.mobz.item.DiscItem;
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
			.effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0, true, false), 0.5F).build();

	public static Item.Properties defItemProp() {
		return new Item.Properties();
	}

	public static Item.Properties nonStackable() {
		return defItemProp().stacksTo(1);
	}

	// Items
	public static final Supplier<SimpleItem> AMAT_INGOT = MobZ.platform.registerItem("amat_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()));
	public static final Supplier<SimpleItem> BEAR_LEATHER = MobZ.platform.registerItem("bear_leather", MobZTabs.tab, () -> new SimpleItem(defItemProp()));
	public static final Supplier<SimpleItem> BOSS_INGOT = MobZ.platform.registerItem("boss_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()) {
		@Override
		public boolean isFoil(ItemStack itemStack) {
			return true;
		}
	});
	public static final Supplier<FrozenPowder> FROZEN_POWDER = MobZ.platform.registerItem("frozen_power", MobZTabs.tab, () -> new FrozenPowder(defItemProp()));
	public static final Supplier<SimpleItem> HARDENEDMETAL_INGOT = MobZ.platform.registerItem("hardened_metal_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()));

	public static final Supplier<ImmunityOrb> IMMUNITY_ORB = MobZ.platform.registerItem("immunity_orb", MobZTabs.tab, () -> new ImmunityOrb(defItemProp().stacksTo(1)));
	public static final Supplier<LevitationOrb> LEVITATION_ORB = MobZ.platform.registerItem("levitation_orb", MobZTabs.tab, () -> new LevitationOrb(defItemProp().stacksTo(1)));
	public static final Supplier<PillagerStaff> PILLAGER_STAFF = MobZ.platform.registerItem("pillager_staff", MobZTabs.tab, () -> new PillagerStaff(nonStackable()));;
	public static final Supplier<SimpleItem> ROTTEN_FLESH = MobZ.platform.registerItem("rotten_flesh", MobZTabs.tab, () -> new SimpleItem(defItemProp().food(FOOD_ROTTEN_FLESH)));
	public static final Supplier<SacrificeKnife> SACRIFICE_KNIFE = MobZ.platform.registerItem("sacrifice_knife", MobZTabs.tab, () -> new SacrificeKnife(nonStackable()));

	public static final Supplier<LilithBow> LILITH_BOW = MobZ.platform.registerItem("lilith_bow", MobZTabs.tab, () -> new LilithBow(defItemProp().durability(461)));
	public static final Supplier<SimpleItem> SEAL_KEY = MobZ.platform.registerItem("seal_key", MobZTabs.tab, () -> new SimpleItem(nonStackable()));
	public static final Supplier<Shield> SHIELD = MobZ.platform.registerItem("shield", MobZTabs.tab, () -> new Shield(defItemProp().durability(589)));
	public static final Supplier<SimpleItem> SPAWN_EGG = MobZ.platform.registerItem("spawn_egg", null, () -> new SimpleItem(new Item.Properties()));
	public static final Supplier<WhiteBag> WHITE_BAG = MobZ.platform.registerItem("white_bag", MobZTabs.tab, () -> new WhiteBag(nonStackable()));

	public static final Supplier<WitherPowder> WITHER_POWDER = MobZ.platform.registerItem("wither_powder", MobZTabs.tab,
			() -> new WitherPowder(defItemProp()));
	public static final Supplier<DiscItem> MEDIVEAL_DISC = MobZ.platform.registerItem("mediveal_disc", MobZTabs.tab,
			() -> new DiscItem(Rarity.RARE, JukeboxSongs.MEDIVEAL_SONG));
	public static final Supplier<DiscItem> MEDIVEAL_DISC_2 = MobZ.platform.registerItem("mediveal_disc_2", MobZTabs.tab,
			() -> new DiscItem(Rarity.RARE, JukeboxSongs.MEDIVEAL_SONG_2));
	public static final Supplier<MobBucketItem> TADPOLE_BUCKET = MobZ.platform.registerItem("tadpole_bucket", MobZTabs.tab,
			MobZ.platform.newMobBucketItem(MobZEntities.TADPOLE, () -> Fluids.WATER,
					() -> SoundEvents.BUCKET_EMPTY_FISH, nonStackable()));
}