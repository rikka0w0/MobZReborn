package net.mobz.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.mobz.MobZ;
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
import net.mobz.portable.StaticAPIWrapper;

public class MobZItems {
    public static Item.Properties defItemProp() {
    	return new Item.Properties().tab(MobZ.tab);
    }

    public static Item.Properties nonStackable() {
    	return defItemProp().stacksTo(1);
    }

	// Items
	public static final SimpleItem AMAT_INGOT = new SimpleItem(defItemProp());
	public static final SimpleItem BEARLEATHER = new SimpleItem(defItemProp());
	public static final SimpleItem BOSS_INGOT = new SimpleItem(defItemProp()) {
		@Override
		public boolean isFoil(ItemStack itemStack) {
			return true;
		}
	};
	public static final FrozenMeal FROZENMEAL = new FrozenMeal(defItemProp());
	public static final SimpleItem HARDENEDMETAL_INGOT = new SimpleItem(defItemProp());
	public static final Orb ORB = new Orb(defItemProp().stacksTo(1));
	public static final Orb2 ORB_2 = new Orb2(defItemProp().stacksTo(1));
	public static final PillagerStaff PILLAGERSTAFF = new PillagerStaff(nonStackable());
	public static final Rottenflesh ROTTENFLESH = new Rottenflesh(defItemProp());
	public static final SacrificeKnife SACRIFICEKNIFE = new SacrificeKnife(nonStackable());
	public static final Sbow SBOW = new Sbow(defItemProp().durability(461));
	public static final SimpleItem SEALITEM = new SimpleItem(nonStackable());
	public static final Shield SHIELD = new Shield(defItemProp().durability(589));
	public static final SimpleItem SHOWEGG = new SimpleItem(nonStackable());
	public static final WhiteBag WHITEBAG = new WhiteBag(nonStackable());
	public static final WitherMeal WITHERMEAL = new WitherMeal(defItemProp());

    // Disks
	@SuppressWarnings("deprecation")
	public static final RecordItem MEDIVEAL_DISC = new RecordItem(1, MobZSounds.MEDIVEALSOUNDEVENT,
			new Item.Properties().stacksTo(1)) {};
	@SuppressWarnings("deprecation")
	public static final RecordItem MEDIVEAL_DISC2 = new RecordItem(0, MobZSounds.MEDIVEALSOUND2EVENT,
			new Item.Properties().stacksTo(1)) {};

	static {
		// Items
		StaticAPIWrapper.instance.register("amat_ingot", AMAT_INGOT);
		StaticAPIWrapper.instance.register("bearleather", BEARLEATHER);
		StaticAPIWrapper.instance.register("boss_ingot", BOSS_INGOT);
		StaticAPIWrapper.instance.register("frozenmeal", FROZENMEAL);
		StaticAPIWrapper.instance.register("hardenedmetal_ingot", HARDENEDMETAL_INGOT);

		StaticAPIWrapper.instance.register("orb", ORB);
		StaticAPIWrapper.instance.register("orb2", ORB_2);
		StaticAPIWrapper.instance.register("pillagerstaff", PILLAGERSTAFF);
		StaticAPIWrapper.instance.register("rottenflesh", ROTTENFLESH);
		StaticAPIWrapper.instance.register("sacrificeknife", SACRIFICEKNIFE);

		StaticAPIWrapper.instance.register("sbow", SBOW);
		StaticAPIWrapper.instance.register("sealitem", SEALITEM);
		StaticAPIWrapper.instance.register("shield", SHIELD);
		StaticAPIWrapper.instance.register("showegg", SHOWEGG);
		StaticAPIWrapper.instance.register("whitebag", WHITEBAG);

		StaticAPIWrapper.instance.register("withermeal", WITHERMEAL);

		// Disks
		StaticAPIWrapper.instance.register("medivealdisc", MEDIVEAL_DISC);
		StaticAPIWrapper.instance.register("medivealdisc2", MEDIVEAL_DISC2);
	}
}