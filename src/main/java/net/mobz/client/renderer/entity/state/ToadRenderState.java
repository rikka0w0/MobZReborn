package net.mobz.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ToadRenderState extends LivingEntityRenderState {
	public boolean onGround = true;
	public float mouthDistance = 0;
	public float tongueDistance = 0;
	public float targetTongueDistance = 0;
}
