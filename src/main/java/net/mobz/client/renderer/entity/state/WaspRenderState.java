package net.mobz.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class WaspRenderState extends LivingEntityRenderState {
    public float rollAmount;
    public boolean hasStinger = true;
    public boolean isOnGround;
    public boolean isAngry;
    public boolean hasNectar;

    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState restAnimationState = new AnimationState();
}
