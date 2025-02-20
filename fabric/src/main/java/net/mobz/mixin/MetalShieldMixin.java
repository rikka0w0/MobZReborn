package net.mobz.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShield;

import net.mobz.item.Shield;

@Mixin(Shield.class)
public abstract class MetalShieldMixin implements FabricShield {
}
