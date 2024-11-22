package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class EasyHumanoidRenderer<T extends Mob> extends HumanoidMobRenderer<T, PlayerRenderState, PlayerModel> {
	private final ResourceLocation texture;

    public EasyHumanoidRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context, new PlayerModel(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(PlayerRenderState renderState) {
        return this.texture;
    }

	@Override
	public PlayerRenderState createRenderState() {
		return new PlayerRenderState();
	}

    public void extractRenderState(T entity, PlayerRenderState renderState, float particalTicks) {
        super.extractRenderState(entity, renderState, particalTicks);
        HumanoidMobRenderer.extractHumanoidRenderState(entity, renderState, particalTicks);
        renderState.arrowCount = entity.getArrowCount();
        renderState.stingerCount = entity.getStingerCount();
        renderState.useItemRemainingTicks = entity.getUseItemRemainingTicks();
        renderState.swinging = entity.swinging;
        renderState.isSpectator = entity.isSpectator();
//        this.extractHandState(p_366577_, p_364437_.mainHandState, InteractionHand.MAIN_HAND);
//        this.extractHandState(p_366577_, p_364437_.offhandState, InteractionHand.OFF_HAND);
        renderState.id = entity.getId();
    }

	@Override
	protected void scale(PlayerRenderState renderState, PoseStack matrixStack) {
		if (renderState.isBaby)
			matrixStack.scale(0.5F, 0.5F, 0.5F);
	}
}