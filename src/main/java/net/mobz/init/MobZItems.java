package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.material.Fluids;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.item.FrozenMeal;
import net.mobz.item.Orb;
import net.mobz.item.Orb2;
import net.mobz.item.PillagerStaff;
import net.mobz.item.Rottenflesh;
import net.mobz.item.SacrificeKnife;
import net.mobz.item.Sbow;
import net.mobz.item.Shield;
import net.mobz.item.SimpleItem;
import net.mobz.item.WhiteBag;
import net.mobz.item.WitherMeal;

public class MobZItems {
	public static Item.Properties defItemProp() {
		return new Item.Properties();
	}

	public static Item.Properties nonStackable() {
		return defItemProp().stacksTo(1);
	}

	// Items
	public static final Supplier<SimpleItem> AMAT_INGOT = MobZ.platform.registerItem("amat_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()));
	public static final Supplier<SimpleItem> BEARLEATHER = MobZ.platform.registerItem("bearleather", MobZTabs.tab, () -> new SimpleItem(defItemProp()));
	public static final Supplier<SimpleItem> BOSS_INGOT = MobZ.platform.registerItem("boss_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()) {
		@Override
		public boolean isFoil(ItemStack itemStack) {
			return true;
		}
	});
	public static final Supplier<FrozenMeal> FROZENMEAL = MobZ.platform.registerItem("frozenmeal", MobZTabs.tab, () -> new FrozenMeal(defItemProp()));
	public static final Supplier<SimpleItem> HARDENEDMETAL_INGOT = MobZ.platform.registerItem("hardenedmetal_ingot", MobZTabs.tab, () -> new SimpleItem(defItemProp()));

	public static final Supplier<Orb> ORB = MobZ.platform.registerItem("orb", MobZTabs.tab, () -> new Orb(defItemProp().stacksTo(1)));
	public static final Supplier<Orb2> ORB_2 = MobZ.platform.registerItem("orb2", MobZTabs.tab, () -> new Orb2(defItemProp().stacksTo(1)));
	public static final Supplier<PillagerStaff> PILLAGERSTAFF = MobZ.platform.registerItem("pillagerstaff", MobZTabs.tab, () -> new PillagerStaff(nonStackable()));;
	public static final Supplier<Rottenflesh> ROTTENFLESH = MobZ.platform.registerItem("rottenflesh", MobZTabs.tab, () -> new Rottenflesh(defItemProp()));
	public static final Supplier<SacrificeKnife> SACRIFICEKNIFE = MobZ.platform.registerItem("sacrificeknife", MobZTabs.tab, () -> new SacrificeKnife(nonStackable()));

	public static final Supplier<Sbow> SBOW = MobZ.platform.registerItem("sbow", MobZTabs.tab, () -> new Sbow(defItemProp().durability(461)));
	public static final Supplier<SimpleItem> SEALITEM = MobZ.platform.registerItem("sealitem", MobZTabs.tab, () -> new SimpleItem(nonStackable()));
	public static final Supplier<Shield> SHIELD = MobZ.platform.registerItem("shield", MobZTabs.tab, () -> new Shield(defItemProp().durability(589)));
	public static final Supplier<SimpleItem> SHOWEGG = MobZ.platform.registerItem("showegg", MobZTabs.tab, () -> new SimpleItem(new Item.Properties()));
	public static final Supplier<WhiteBag> WHITEBAG = MobZ.platform.registerItem("whitebag", MobZTabs.tab, () -> new WhiteBag(nonStackable()));

	public static final Supplier<WitherMeal> WITHERMEAL = MobZ.platform.registerItem("withermeal", MobZTabs.tab,
			() -> new WitherMeal(defItemProp()));
	public static final Supplier<RecordItem> MEDIVEAL_DISC = MobZ.platform.registerItem("medivealdisc", MobZTabs.tab,
			MobZ.platform.newRecordItem(1, MobZSounds.MEDIVEALSOUNDEVENT, new Item.Properties().stacksTo(1)));
	public static final Supplier<RecordItem> MEDIVEAL_DISC2 = MobZ.platform.registerItem("medivealdisc2", MobZTabs.tab,
			MobZ.platform.newRecordItem(0, MobZSounds.MEDIVEALSOUND2EVENT, new Item.Properties().stacksTo(1)));
	public static final Supplier<MobBucketItem> TADPOLE_BUCKET = MobZ.platform.registerItem("tadpole_bucket", MobZTabs.tab,
			MobZ.platform.newMobBucketItem(MobZEntities.TADPOLE, () -> Fluids.WATER,
					() -> SoundEvents.BUCKET_EMPTY_FISH, nonStackable()));
}