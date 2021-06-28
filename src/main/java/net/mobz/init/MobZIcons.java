package net.mobz.init;

import net.minecraft.item.Item;
import net.mobz.common.IRegistryWrapper;
import net.mobz.item.SimpleItem;

public class MobZIcons {
	public static final SimpleItem ARCHERHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem BOSSHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem DWARFHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem FASTHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem KNIGHT2ENTITYHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem KNIGHT5ENTITYHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem LAVAGOLEMHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem MAGEENTITYHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem SPOHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem STARTHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem WITHENDERHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem BLAZEHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem CREEPHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ENDERHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem FRIENDHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem GOLEMHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem SKELIHEAD1 = new SimpleItem(new Item.Properties());
	public static final SimpleItem SKELIHEAD2 = new SimpleItem(new Item.Properties());
	public static final SimpleItem SKELIHEAD3 = new SimpleItem(new Item.Properties());
	public static final SimpleItem CRIPHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ENDERKNIGHTHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ENDERZOMBIEHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem FIORAHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ICEGOLEMHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem KNIGHTENTITYHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem SKELIHEAD4 = new SimpleItem(new Item.Properties());
	public static final SimpleItem SPIHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem STEVEHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem WITHERBLAZEHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem PILLAGERBOSSHEAD = new SimpleItem(new Item.Properties());

	public static final SimpleItem BABYRAVAGERHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ISLANDKINGHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ISLANDKNIGHTSPECIALHEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem ISLANDKNIGHTSPECIAL2HEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem METALGOLEMHEAD = new SimpleItem(new Item.Properties());

	public static final SimpleItem ARCHER2HEAD = new SimpleItem(new Item.Properties());
	public static final SimpleItem SCREEPERHEAD = new SimpleItem(new Item.Properties());
	
	public static void registerAll(IRegistryWrapper registry) {
        registry.register("archerhead", ARCHERHEAD);
        registry.register("bosshead", BOSSHEAD);
        registry.register("dwarfhead", DWARFHEAD);
        registry.register("fasthead", FASTHEAD);
        registry.register("knight2entityhead", KNIGHT2ENTITYHEAD);
        registry.register("knight5entityhead", KNIGHT5ENTITYHEAD);
        registry.register("lavagolemhead", LAVAGOLEMHEAD);
        registry.register("mageentityhead", MAGEENTITYHEAD);
        registry.register("spohead", SPOHEAD);
        registry.register("starthead", STARTHEAD);
        registry.register("withenderhead", WITHENDERHEAD);
        registry.register("blazehead", BLAZEHEAD);
        registry.register("creephead", CREEPHEAD);
        registry.register("enderhead", ENDERHEAD);
        registry.register("friendhead", FRIENDHEAD);
        registry.register("golemhead", GOLEMHEAD);
        registry.register("skelihead1", SKELIHEAD1);
        registry.register("skelihead2", SKELIHEAD2);
        registry.register("skelihead3", SKELIHEAD3);
        registry.register("criphead", CRIPHEAD);
        registry.register("enderknighthead", ENDERKNIGHTHEAD);
        registry.register("enderzombiehead", ENDERZOMBIEHEAD);
        registry.register("fiorahead", FIORAHEAD);
        registry.register("icegolemhead", ICEGOLEMHEAD);
        registry.register("knightentityhead", KNIGHTENTITYHEAD);
        registry.register("skelihead4", SKELIHEAD4);
        registry.register("spihead", SPIHEAD);
        registry.register("stevehead", STEVEHEAD);
        registry.register("witherblazehead", WITHERBLAZEHEAD);
        registry.register("pillagerbosshead", PILLAGERBOSSHEAD);
        registry.register("babyravagerhead", BABYRAVAGERHEAD);
        registry.register("islandkinghead", ISLANDKINGHEAD);
        registry.register("islandknightspecialhead",
                        ISLANDKNIGHTSPECIALHEAD);
        registry.register("islandknightspecial2head",
                        ISLANDKNIGHTSPECIAL2HEAD);
        registry.register("metalgolemhead", METALGOLEMHEAD);
        registry.register("archer2head", ARCHER2HEAD);
        registry.register("screeperhead", SCREEPERHEAD);
	}
}
