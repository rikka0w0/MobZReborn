package net.mobz.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.mobz.IRegistryWrapper;
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
			new Item.Properties().stacksTo(1));
	@SuppressWarnings("deprecation")
	public static final RecordItem MEDIVEAL_DISC2 = new RecordItem(0, MobZSounds.MEDIVEALSOUND2EVENT,
			new Item.Properties().stacksTo(1));

	public static void registerAll(IRegistryWrapper registry) {
		// Items
		registry.register("amat_ingot", AMAT_INGOT);
		registry.register("bearleather", BEARLEATHER);
		registry.register("boss_ingot", BOSS_INGOT);
		registry.register("frozenmeal", FROZENMEAL);
		registry.register("hardenedmetal_ingot", HARDENEDMETAL_INGOT);

		registry.register("orb", ORB);
		registry.register("orb2", ORB_2);
		registry.register("pillagerstaff", PILLAGERSTAFF);
		registry.register("rottenflesh", ROTTENFLESH);
		registry.register("sacrificeknife", SACRIFICEKNIFE);

		registry.register("sbow", SBOW);
		registry.register("sealitem", SEALITEM);
		registry.register("shield", SHIELD);
		registry.register("showegg", SHOWEGG);
		registry.register("whitebag", WHITEBAG);

		registry.register("withermeal", WITHERMEAL);

		// Disks
		registry.register("medivealdisc", MEDIVEAL_DISC);
		registry.register("medivealdisc2", MEDIVEAL_DISC2);
	}
}