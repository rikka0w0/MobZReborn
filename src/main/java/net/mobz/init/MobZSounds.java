package net.mobz.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.mobz.MobZ;
import net.mobz.portable.StaticAPIWrapper;

public class MobZSounds {
    public static SoundEvent AMBIENTTANKEVENT = register("ambienttank");
    public static SoundEvent DEATHTANKEVENT = register("deathtank");
    public static SoundEvent HURTTANKEVENT = register("hurttank");
    public static SoundEvent STEPTANKEVENT = register("steptank");
    public static SoundEvent DEATHCREEPEVENT = register("creepdeath");
    public static SoundEvent SAYCREEPEVENT = register("creepsay");
    public static SoundEvent DEATHCRIPEVENT = register("cripdeath");
    public static SoundEvent SAYCRIPEVENT = register("cripsay");
    public static SoundEvent SAYPIGEVENT = register("pigsay");
    public static SoundEvent HURTPIGEVENT = register("pighurt");
    public static SoundEvent DEATHPIGEVENT = register("pigdeath");
    public static SoundEvent DEATHENDEVENT = register("enddeath");
    public static SoundEvent ENDHURTEVENT = register("endhurt");
    public static SoundEvent SAYENDEVENT = register("endsay");
    public static SoundEvent SAYSPEEDEVENT = register("speedsay");
    public static SoundEvent STEPSPEEDEVENT = register("speedstep");
    public static SoundEvent GOLEMHITEVENT = register("golemhit");
    public static SoundEvent GOLEMWALKEVENT = register("golemwalk");
    public static SoundEvent GOLEMDEATHEVENT = register("golemdeath");
    public static SoundEvent SKELIDEATHEVENT = register("skelideath");
    public static SoundEvent SKELADEATHEVENT = register("skeladeath");
    public static SoundEvent SKELIHURTEVENT = register("skelihurt");
    public static SoundEvent SKELAHURTEVENT = register("skelahurt");
    public static SoundEvent SKELISAYEVENT = register("skelisay");
    public static SoundEvent SKELASAYEVENT = register("skelasay" );
    public static SoundEvent SKELISTEPEVENT = register("skelistep" );
    public static SoundEvent SKELASTEPEVENT = register("skelastep");
    public static SoundEvent NOTHINGEVENT = register("nothing");
    public static SoundEvent ANGRYBATTLEHORNEVENT = register("angrybattlehorn");
    public static SoundEvent MEDIVEALSOUNDEVENT = register("medivealsound");
    public static SoundEvent MEDIVEALSOUND2EVENT = register("medivealsound2");
    public static SoundEvent EVADEATHEVENT = register("evadeath");
    public static SoundEvent EVAHURTEVENT = register("evahurt");
    public static SoundEvent EVAIDLEEVENT = register("evaidle");
    public static SoundEvent EVEDEATHEVENT = register("evedeath");
    public static SoundEvent EVEHURTEVENT = register("evehurt");
    public static SoundEvent EVEIDLEEVENT = register("eveidle");
    public static SoundEvent ILLUIDLEEVENT = register("illuidle");
    public static SoundEvent ILLUDEATHEVENT = register("illudeath");
    public static SoundEvent ILLUHURTEVENT = register("illuhurt");
    public static SoundEvent PBITEEVENT = register("pbite");
    public static SoundEvent BOARSAYEVENT = register("boarsay");
    public static SoundEvent BOARDEATHEVENT = register("boardeath");
    public static SoundEvent WITHDEATHEVENT = register("withdeath");
    public static SoundEvent WITHHURTEVENT = register("withhurt");
    public static SoundEvent WITHIDLEEVENT = register("withidle");
    public static SoundEvent DARKIDLEEVENT = register("darkidle");
    public static SoundEvent DARKDEATHEVENT = register("darkdeath");
    public static SoundEvent DARKHITEVENT = register("darkhit");
    public static SoundEvent WHURTEVENT = register("whurt");
    public static SoundEvent WDEATHEVENT = register("wdeath");
    public static SoundEvent WGROWLEVENT = register("wgrowl");
    public static SoundEvent ARMORWALKEVENT = register("armorwalk");
    public static SoundEvent LEATHERWALKEVENT = register("leatherwalk");
    public static SoundEvent LIGHTERARMORWALKEVENT = register("lighterarmorwalk");
    public static SoundEvent LESSHEAVYARMORWALKEVENT = register("lessheavyarmorwalk");
    public static SoundEvent RAVIDLEEVENT = register("ravidle");
    public static SoundEvent RAVHURTEVENT = register("ravhurt");
    public static SoundEvent RAVDEATHEVENT = register("ravdeath");
    public static SoundEvent MGOLEMHITEVENT = register("mgolemhit");
    public static SoundEvent MGOLEMBREAKEVENT = register("mgolembreak");
    public static SoundEvent UNSEALEVENT = register("unseal");

    private static SoundEvent register(String name) {
    	ResourceLocation resLoc = new ResourceLocation(MobZ.MODID, name);
    	return StaticAPIWrapper.instance.register(name, new SoundEvent(resLoc));
    }
}