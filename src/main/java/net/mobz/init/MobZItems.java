package net.mobz.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.mobz.common.IRegistryWrapper;
import net.mobz.common.MobZ;
import net.mobz.item.*;

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
	/*public static final MusicDiscItem MEDIVEAL_DISC = new MusicDiscItem(1, Soundinit.MEDIVEALSOUNDEVENT,
			new Item.Properties().stacksTo(1));
	public static final MusicDiscItem MEDIVEAL_DISC2 = new MusicDiscItem(0, Soundinit.MEDIVEALSOUND2EVENT,
			new Item.Properties().stacksTo(1));*/
        
        
	// Heads
	/*
	public static final SimpleItem ARCHERHEAD = new SimpleItem("archerhead", nonStackable());
	public static final SimpleItem BOSSHEAD = new SimpleItem("bosshead", nonStackable());
	public static final SimpleItem DWARFHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem FASTHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem KNIGHT2ENTITYHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem KNIGHT5ENTITYHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem LAVAGOLEMHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem MAGEENTITYHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SPOHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem STARTHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem WITHENDERHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem BLAZEHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem CREEPHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ENDERHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem FRIENDHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem GOLEMHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SKELIHEAD1 = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SKELIHEAD2 = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SKELIHEAD3 = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem CRIPHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ENDERKNIGHTHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ENDERZOMBIEHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem FIORAHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ICEGOLEMHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem KNIGHTENTITYHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SKELIHEAD4 = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SPIHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem STEVEHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem WITHERBLAZEHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem PILLAGERBOSSHEAD = new SimpleItem("showegg", nonStackable());

	public static final SimpleItem BABYRAVAGERHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ISLANDKINGHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ISLANDKNIGHTSPECIALHEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem ISLANDKNIGHTSPECIAL2HEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem METALGOLEMHEAD = new SimpleItem("showegg", nonStackable());

	public static final SimpleItem ARCHER2HEAD = new SimpleItem("showegg", nonStackable());
	public static final SimpleItem SCREEPERHEAD = new SimpleItem("showegg", nonStackable());
    */

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
	}
        
        public static void init() {
/*
                Registry.register(Registry.ITEM, new Identifier("mobz", "medivealdisc"), MEDIVEAL_DISC);
                Registry.register(Registry.ITEM, new Identifier("mobz", "medivealdisc2"), MEDIVEAL_DISC2);
                Registry.register(Registry.ITEM, new Identifier("mobz", "boss_ingot"), BOSS_INGOT);
                Registry.register(Registry.ITEM, new Identifier("mobz", "hardenedmetal_ingot"), HARDENEDMETAL_INGOT);
                Registry.register(Registry.ITEM, new Identifier("mobz", "shield"), SHIELD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "rottenflesh"), ROTTENFLESH);
                Registry.register(Registry.ITEM, new Identifier("mobz", "amat_ingot"), AMAT_INGOT);
                Registry.register(Registry.ITEM, new Identifier("mobz", "withermeal"), WITHERMEAL);
                Registry.register(Registry.ITEM, new Identifier("mobz", "frozenmeal"), FROZENMEAL);
                Registry.register(Registry.ITEM, new Identifier("mobz", "bearleather"), BEARLEATHER);
                Registry.register(Registry.ITEM, new Identifier("mobz", "sbow"), SBOW);
                Registry.register(Registry.ITEM, new Identifier("mobz", "orb"), ORB);
                Registry.register(Registry.ITEM, new Identifier("mobz", "whitebag"), WHITEBAG);
                Registry.register(Registry.ITEM, new Identifier("mobz", "orb2"), ORB_2);
                Registry.register(Registry.ITEM, new Identifier("mobz", "sacrificeknife"), SACRIFICEKNIFE);
                Registry.register(Registry.ITEM, new Identifier("mobz", "showegg"), SHOWEGG);
                Registry.register(Registry.ITEM, new Identifier("mobz", "archerhead"), ARCHERHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "bosshead"), BOSSHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "dwarfhead"), DWARFHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "fasthead"), FASTHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "knight2entityhead"), KNIGHT2ENTITYHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "knight5entityhead"), KNIGHT5ENTITYHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "lavagolemhead"), LAVAGOLEMHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "mageentityhead"), MAGEENTITYHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "spohead"), SPOHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "starthead"), STARTHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "withenderhead"), WITHENDERHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "blazehead"), BLAZEHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "creephead"), CREEPHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "enderhead"), ENDERHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "friendhead"), FRIENDHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "golemhead"), GOLEMHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "skelihead1"), SKELIHEAD1);
                Registry.register(Registry.ITEM, new Identifier("mobz", "skelihead2"), SKELIHEAD2);
                Registry.register(Registry.ITEM, new Identifier("mobz", "skelihead3"), SKELIHEAD3);
                Registry.register(Registry.ITEM, new Identifier("mobz", "criphead"), CRIPHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "enderknighthead"), ENDERKNIGHTHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "enderzombiehead"), ENDERZOMBIEHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "fiorahead"), FIORAHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "icegolemhead"), ICEGOLEMHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "knightentityhead"), KNIGHTENTITYHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "skelihead4"), SKELIHEAD4);
                Registry.register(Registry.ITEM, new Identifier("mobz", "spihead"), SPIHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "stevehead"), STEVEHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "witherblazehead"), WITHERBLAZEHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "pillagerbosshead"), PILLAGERBOSSHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "pillagerstaff"), PILLAGERSTAFF);
                Registry.register(Registry.ITEM, new Identifier("mobz", "babyravagerhead"), BABYRAVAGERHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "islandkinghead"), ISLANDKINGHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "islandknightspecialhead"),
                                ISLANDKNIGHTSPECIALHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "islandknightspecial2head"),
                                ISLANDKNIGHTSPECIAL2HEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "metalgolemhead"), METALGOLEMHEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "sealitem"), SEALITEM);
                Registry.register(Registry.ITEM, new Identifier("mobz", "archer2head"), ARCHER2HEAD);
                Registry.register(Registry.ITEM, new Identifier("mobz", "screeperhead"), SCREEPERHEAD);
                */
        }

}