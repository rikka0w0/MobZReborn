package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.MobZ;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.entity.TadpoleEntity;

public class TadpoleRender extends MobRenderer<TadpoleEntity, TadpoleEntityModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(MobZ.MODID, "textures/entity/tadpole.png");

	public TadpoleRender(EntityRendererProvider.Context context)
	{
		super(context, new TadpoleEntityModel(context.bakeLayer(TadpoleEntityModel.modelResLoc)), 0.15F);
	}

	protected void setupTransforms(TadpoleEntity tadpole, PoseStack matrixStack, float f, float g, float h)
	{
		super.setupRotations(tadpole, matrixStack, f, g, h);
		float i = 4.3F * Mth.sin(0.6F * f);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(i));
		if(!tadpole.isInWater())
		{
			matrixStack.translate(0.10000000149011612D, 0.10000000149011612D, -0.10000000149011612D);
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(TadpoleEntity entity) {
		return TEXTURE;
	}
}
