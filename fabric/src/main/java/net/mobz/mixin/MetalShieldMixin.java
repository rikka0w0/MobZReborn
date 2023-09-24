package net.mobz.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShield;

import net.mobz.item.Shield;

@Mixin(Shield.class)
public abstract class MetalShieldMixin implements FabricShield {
	@Override
	public int getCoolDownTicks() {
		return 100;
	}

	@Override
	public boolean supportsBanner() {
		return false;
	}
}
