package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.mobz.MobZ;
import net.mobz.item.SimpleItem;

public class MobZIcons {
	public static final Supplier<SimpleItem> ARCHERHEAD = simpleItem("archerhead");
	public static final Supplier<SimpleItem> BOSSHEAD = simpleItem("bosshead");
	public static final Supplier<SimpleItem> DWARFHEAD = simpleItem("dwarfhead");
	public static final Supplier<SimpleItem> FASTHEAD = simpleItem("fasthead");
	public static final Supplier<SimpleItem> KNIGHT2ENTITYHEAD = simpleItem("knight2entityhead");
	public static final Supplier<SimpleItem> KNIGHT5ENTITYHEAD = simpleItem("knight5entityhead");
	public static final Supplier<SimpleItem> LAVAGOLEMHEAD = simpleItem("lavagolemhead");
	public static final Supplier<SimpleItem> MAGEENTITYHEAD = simpleItem("mageentityhead");
	public static final Supplier<SimpleItem> SPOHEAD = simpleItem("spohead");
	public static final Supplier<SimpleItem> STARTHEAD = simpleItem("starthead");
	public static final Supplier<SimpleItem> WITHENDERHEAD = simpleItem("withenderhead");
	public static final Supplier<SimpleItem> BLAZEHEAD = simpleItem("blazehead");
	public static final Supplier<SimpleItem> CREEPHEAD = simpleItem("creephead");
	public static final Supplier<SimpleItem> ENDERHEAD = simpleItem("enderhead");
	public static final Supplier<SimpleItem> FRIENDHEAD = simpleItem("friendhead");
	public static final Supplier<SimpleItem> GOLEMHEAD = simpleItem("golemhead");
	public static final Supplier<SimpleItem> SKELIHEAD1 = simpleItem("skelihead1");
	public static final Supplier<SimpleItem> SKELIHEAD2 = simpleItem("skelihead2");
	public static final Supplier<SimpleItem> SKELIHEAD3 = simpleItem("skelihead3");
	public static final Supplier<SimpleItem> CRIPHEAD = simpleItem("criphead");
	public static final Supplier<SimpleItem> ENDERKNIGHTHEAD = simpleItem("enderknighthead");
	public static final Supplier<SimpleItem> ENDERZOMBIEHEAD = simpleItem("enderzombiehead");
	public static final Supplier<SimpleItem> FIORAHEAD = simpleItem("fiorahead");
	public static final Supplier<SimpleItem> ICEGOLEMHEAD = simpleItem("icegolemhead");
	public static final Supplier<SimpleItem> KNIGHTENTITYHEAD = simpleItem("knightentityhead");
	public static final Supplier<SimpleItem> SKELIHEAD4 = simpleItem("skelihead4");
	public static final Supplier<SimpleItem> SPIHEAD = simpleItem("spihead");
	public static final Supplier<SimpleItem> STEVEHEAD = simpleItem("stevehead");
	public static final Supplier<SimpleItem> WITHERBLAZEHEAD = simpleItem("witherblazehead");
	public static final Supplier<SimpleItem> PILLAGERBOSSHEAD = simpleItem("pillagerbosshead");

	public static final Supplier<SimpleItem> BABYRAVAGERHEAD = simpleItem("babyravagerhead");
	public static final Supplier<SimpleItem> ISLANDKINGHEAD = simpleItem("islandkinghead");
	public static final Supplier<SimpleItem> ISLANDKNIGHTSPECIALHEAD = simpleItem("islandknightspecialhead");
	public static final Supplier<SimpleItem> ISLANDKNIGHTSPECIAL2HEAD = simpleItem("islandknightspecial2head");
	public static final Supplier<SimpleItem> METALGOLEMHEAD = simpleItem("metalgolemhead");

	public static final Supplier<SimpleItem> ARCHER2HEAD = simpleItem("archer2head");
	public static final Supplier<SimpleItem> SCREEPERHEAD = simpleItem("screeperhead");

	private static Supplier<SimpleItem> simpleItem(String name) {
		return MobZ.platform.registerItem(name, null, () -> new SimpleItem(new Item.Properties()));
	}
}
