package net.mobz.init;

import java.util.function.Supplier;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.mobz.MobZ;

public class MobZSounds {
	public static final Supplier<SoundEvent> AMBIENTTANKEVENT = register("ambienttank");
	public static final Supplier<SoundEvent> DEATHTANKEVENT = register("deathtank");
	public static final Supplier<SoundEvent> HURTTANKEVENT = register("hurttank");
	public static final Supplier<SoundEvent> STEPTANKEVENT = register("steptank");
	public static final Supplier<SoundEvent> DEATHCREEPEVENT = register("creepdeath");
	public static final Supplier<SoundEvent> SAYCREEPEVENT = register("creepsay");
	public static final Supplier<SoundEvent> DEATHCRIPEVENT = register("cripdeath");
	public static final Supplier<SoundEvent> SAYCRIPEVENT = register("cripsay");
	public static final Supplier<SoundEvent> SAYPIGEVENT = register("pigsay");
	public static final Supplier<SoundEvent> HURTPIGEVENT = register("pighurt");
	public static final Supplier<SoundEvent> DEATHPIGEVENT = register("pigdeath");
	public static final Supplier<SoundEvent> DEATHENDEVENT = register("enddeath");
	public static final Supplier<SoundEvent> ENDHURTEVENT = register("endhurt");
	public static final Supplier<SoundEvent> SAYENDEVENT = register("endsay");
	public static final Supplier<SoundEvent> SAYSPEEDEVENT = register("speedsay");
	public static final Supplier<SoundEvent> STEPSPEEDEVENT = register("speedstep");
	public static final Supplier<SoundEvent> GOLEMHITEVENT = register("golemhit");
	public static final Supplier<SoundEvent> GOLEMWALKEVENT = register("golemwalk");
	public static final Supplier<SoundEvent> GOLEMDEATHEVENT = register("golemdeath");
	public static final Supplier<SoundEvent> SKELIDEATHEVENT = register("skelideath");
	public static final Supplier<SoundEvent> SKELADEATHEVENT = register("skeladeath");
	public static final Supplier<SoundEvent> SKELIHURTEVENT = register("skelihurt");
	public static final Supplier<SoundEvent> SKELAHURTEVENT = register("skelahurt");
	public static final Supplier<SoundEvent> SKELISAYEVENT = register("skelisay");
	public static final Supplier<SoundEvent> SKELASAYEVENT = register("skelasay");
	public static final Supplier<SoundEvent> SKELISTEPEVENT = register("skelistep");
	public static final Supplier<SoundEvent> SKELASTEPEVENT = register("skelastep");
	public static final Supplier<SoundEvent> NOTHINGEVENT = register("nothing");
	public static final Supplier<SoundEvent> ANGRYBATTLEHORNEVENT = register("angrybattlehorn");
	public static final Supplier<Holder<SoundEvent>> MEDIVEAL_MUSIC = registerAsHolder("medivealsound");
	public static final Supplier<Holder<SoundEvent>> MEDIVEAL_MUSIC_2 = registerAsHolder("medivealsound2");
	public static final Supplier<SoundEvent> EVADEATHEVENT = register("evadeath");
	public static final Supplier<SoundEvent> EVAHURTEVENT = register("evahurt");
	public static final Supplier<SoundEvent> EVAIDLEEVENT = register("evaidle");
	public static final Supplier<SoundEvent> EVEDEATHEVENT = register("evedeath");
	public static final Supplier<SoundEvent> EVEHURTEVENT = register("evehurt");
	public static final Supplier<SoundEvent> EVEIDLEEVENT = register("eveidle");
	public static final Supplier<SoundEvent> ILLUIDLEEVENT = register("illuidle");
	public static final Supplier<SoundEvent> ILLUDEATHEVENT = register("illudeath");
	public static final Supplier<SoundEvent> ILLUHURTEVENT = register("illuhurt");
	public static final Supplier<SoundEvent> PBITEEVENT = register("pbite");
	public static final Supplier<SoundEvent> BOARSAYEVENT = register("boarsay");
	public static final Supplier<SoundEvent> BOARDEATHEVENT = register("boardeath");
	public static final Supplier<SoundEvent> WITHDEATHEVENT = register("withdeath");
	public static final Supplier<SoundEvent> WITHHURTEVENT = register("withhurt");
	public static final Supplier<SoundEvent> WITHIDLEEVENT = register("withidle");
	public static final Supplier<SoundEvent> DARKIDLEEVENT = register("darkidle");
	public static final Supplier<SoundEvent> DARKDEATHEVENT = register("darkdeath");
	public static final Supplier<SoundEvent> DARKHITEVENT = register("darkhit");
	public static final Supplier<SoundEvent> WHURTEVENT = register("whurt");
	public static final Supplier<SoundEvent> WDEATHEVENT = register("wdeath");
	public static final Supplier<SoundEvent> WGROWLEVENT = register("wgrowl");
	public static final Supplier<SoundEvent> ARMORWALKEVENT = register("armorwalk");
	public static final Supplier<SoundEvent> LEATHERWALKEVENT = register("leatherwalk");
	public static final Supplier<SoundEvent> LIGHTERARMORWALKEVENT = register("lighterarmorwalk");
	public static final Supplier<SoundEvent> LESSHEAVYARMORWALKEVENT = register("lessheavyarmorwalk");
	public static final Supplier<SoundEvent> RAVIDLEEVENT = register("ravidle");
	public static final Supplier<SoundEvent> RAVHURTEVENT = register("ravhurt");
	public static final Supplier<SoundEvent> RAVDEATHEVENT = register("ravdeath");
	public static final Supplier<SoundEvent> MGOLEMHITEVENT = register("mgolemhit");
	public static final Supplier<SoundEvent> MGOLEMBREAKEVENT = register("mgolembreak");
	public static final Supplier<SoundEvent> UNSEALEVENT = register("unseal");

	public static final Supplier<SoundEvent> TOAD_MOUTH = register("entity.toad.mouth");
	public static final Supplier<SoundEvent> TOAD_SWALLOW = register("entity.toad.swallow");
	public static final Supplier<SoundEvent> TOAD_HAVE_BABY = register("entity.toad.have_baby");
	public static final Supplier<SoundEvent> TOAD_CROAK = register("entity.toad.croak");
	public static final Supplier<SoundEvent> TOAD_DEATH = register("entity.toad.death");
	public static final Supplier<SoundEvent> TOAD_HURT = register("entity.toad.hurt");
	public static final Supplier<SoundEvent> TOAD_JUMP = register("entity.toad.jump");

	private static Supplier<Holder<SoundEvent>> registerAsHolder(String name) {
		return MobZ.platform.registerSound(name, MobZ.resLoc(name), null);
	}

	private static Supplier<SoundEvent> register(String name) {
		Supplier<Holder<SoundEvent>> holderSupplier = registerAsHolder(name);
		return () -> holderSupplier.get().value();
	}
}