package net.mobz.client.renderer.model;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.entity.ToadEntity;
import net.mobz.MathUtils;
import net.mobz.MobZ;

public class ToadEntityModel extends EntityModel<ToadEntity> {
	public static final Random random = new Random();

	private final ModelPart body;
	private final ModelPart backlege;
	private final ModelPart backlegw;
	private final ModelPart eyeballw;
	private final ModelPart eyeballe;
	private final ModelPart lips;
	private final ModelPart lipBottom;
	private final ModelPart lipTop;
	private final ModelPart frontlegw;
	private final ModelPart frontlege;
	private final ModelPart tongue;

	private float tongueDistance;

	public final static ModelLayerLocation modelResLoc = new ModelLayerLocation(
			new ResourceLocation(MobZ.MODID, "toad_model"), "main");

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder
				.create().texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -2.0F, 8.0F, 8.0F, 8.0F).texOffs(10, 24)
				.addBox(1.0F, -10.0F, -1.0F, 2.0F, 2.0F, 3.0F).texOffs(0, 24)
				.addBox(-3.0F, -10.0F, -1.0F, 2.0F, 2.0F, 3.0F),
				PartPose.offset(0.0F, 24.0F, -0.2727F));

		body.addOrReplaceChild("backlege", CubeListBuilder.create()
				.texOffs(8, 16).addBox(-2.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F)
				.texOffs(12, 16).addBox(-2.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F),
				PartPose.offset(-4.0F, -3.0F, 5.0F));

		body.addOrReplaceChild("backlegw", CubeListBuilder.create()
				.texOffs(0, 16).addBox(0.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F)
				.texOffs(24, 16).addBox(0.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F),
				PartPose.offset(4.0F, -3.0F, 5.0F));

		body.addOrReplaceChild("eyeballw", CubeListBuilder.create()
				.texOffs(18, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
				PartPose.offset(2.5F, -9.5F, 0.5F));

		body.addOrReplaceChild("eyeballe", CubeListBuilder.create()
				.texOffs(24, 3).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
				PartPose.offset(-2.5F, -9.5F, 0.5F));

		PartDefinition lips = body.addOrReplaceChild("lips", CubeListBuilder.create()
				, PartPose.offset(0.0F, -4.0F, -2.0F));

		lips.addOrReplaceChild("lip_bottom", CubeListBuilder.create()
				.texOffs(20, 18).addBox(-3.0F, 1.0F, -1.0F, 6.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		lips.addOrReplaceChild("lip_top", CubeListBuilder.create()
				.texOffs(20, 16).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));


		PartDefinition frontlegw = body.addOrReplaceChild("frontlegw", CubeListBuilder.create()
				, PartPose.offset(4.0F, -5.0F, -1.0F));

		frontlegw.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(22, 22).addBox(0.0F, 0.684F, -1.3794F, 2.0F, 6.0F, 2.0F)
				, PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition frontlege = body.addOrReplaceChild("frontlege", CubeListBuilder.create()
				, PartPose.offset(-4.0F, -5.0F, -1.0F));

		frontlege.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F)
				, PartPose.offsetAndRotation(0.0F, 5.0F, -2.0F, -0.3491F, 0.0F, 0.0F));

		body.addOrReplaceChild("tongue", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F)
				, PartPose.offset(0.1F, 0.0F, -4.1273F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public ToadEntityModel(ModelPart modelPart) {
		this.body = modelPart.getChild("body");
		this.backlege = body.getChild("backlege");
		this.backlegw = body.getChild("backlegw");
		this.eyeballw = body.getChild("eyeballw");
		this.eyeballe = body.getChild("eyeballe");
		this.lips = body.getChild("lips");
		this.lipBottom = lips.getChild("lip_bottom");
		this.lipTop = lips.getChild("lip_top");
		this.frontlegw = body.getChild("frontlegw");
		this.frontlege = body.getChild("frontlege");
		this.tongue = body.getChild("tongue");
	}

	@Override
	public void setupAnim(ToadEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float pi = (float) Math.PI;
		float legAmount = 1.4F;

		this.frontlege.xRot = Mth.cos(limbAngle * 1 + pi) * 1.4F * limbDistance;
		this.backlege.xRot = Mth.cos(limbAngle * 1) * legAmount * limbDistance;

		this.frontlegw.xRot = Mth.cos(limbAngle * 1) * 1.4F * limbDistance;
		this.backlegw.xRot = Mth.cos(limbAngle * 1 + pi) * legAmount * limbDistance;

		if(!entity.isOnGround()) {
			this.backlegw.xRot = 2F;
			this.backlege.xRot = 2F;
		}

		if(entity.hasTongueEntity()) {
			entity.mouthDistance = MathUtils.approachValue(entity.mouthDistance, 1, 0.5F);
		} else {
			entity.mouthDistance = MathUtils.approachValue(entity.mouthDistance, 0, 0.10F);
		}
		lipTop.y = -entity.mouthDistance;
		lipBottom.y = entity.mouthDistance;

		tongue.xRot = headPitch * 0.0175F;
		tongue.yRot = headYaw * 0.0175F;
		this.tongueDistance = entity.tongueDistance;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
			int overlay, float r, float g, float b, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, overlay, r, g, b, alpha);

		// Render tongue
		poseStack.pushPose();
		this.body.translateAndRotate(poseStack);
		this.renderTongue(poseStack, vertexConsumer, packedLight, overlay, r, g, b, alpha);
		poseStack.popPose();
	}

	public void renderTongue(PoseStack matrices, VertexConsumer vertexConsumer, int light,
			int overlay, float red, float green, float blue, float alpha) {
		matrices.pushPose();
		if (this.tongue.zRot != 0.0F) {
			matrices.mulPose(Vector3f.ZP.rotation(this.tongue.zRot));
		}

		if (this.tongue.yRot != 0.0F) {
			matrices.mulPose(Vector3f.YP.rotation(this.tongue.yRot));
		}

		if (this.tongue.xRot != 0.0F) {
			matrices.mulPose(Vector3f.XP.rotation(this.tongue.xRot));
		}

		matrices.translate(0, -0.25, 0);
		matrices.scale(1, 1, tongueDistance);
		ModelPart.Cube cuboid = new ModelPart.Cube(16, 0, -1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0, 0, 0, false, 64, 64);
		cuboid.compile(matrices.last(), vertexConsumer, light, overlay, 1, 0, 0, 0);

		matrices.popPose();
	}
}
