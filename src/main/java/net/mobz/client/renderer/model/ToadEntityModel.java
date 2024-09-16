package net.mobz.client.renderer.model;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.mobz.entity.ToadEntity;
import net.mobz.MathUtils;
import net.mobz.MobZ;

public class ToadEntityModel extends EntityModel<ToadEntity> {
	private static final Set<Direction> ALL_VISIBLE = EnumSet.allOf(Direction.class);

	public static final Random random = new Random();

	private final float bodyScale;
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

	private float tongue_xRot;
	private float tongue_yRot;
	private float tongueDistance;
	private float targetTongueDistance;

	// Eye to bottom of the model, with respect to the real world
	private float eyeHeight;

	public final static ModelLayerLocation modelResLoc = new ModelLayerLocation(
			ResourceLocation.tryBuild(MobZ.MODID, "toad_model"), "main");

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

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public ToadEntityModel(ModelPart modelPart, float bodyScale) {
		this.bodyScale = bodyScale;
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
	}

	@Override
	public void setupAnim(ToadEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float pi = (float) Math.PI;
		float legAmount = 1.4F;

		this.frontlege.xRot = Mth.cos(limbAngle * 1 + pi) * 1.4F * limbDistance;
		this.backlege.xRot = Mth.cos(limbAngle * 1) * legAmount * limbDistance;

		this.frontlegw.xRot = Mth.cos(limbAngle * 1) * 1.4F * limbDistance;
		this.backlegw.xRot = Mth.cos(limbAngle * 1 + pi) * legAmount * limbDistance;

		if(!entity.onGround()) {
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

		this.tongue_xRot = headPitch * 0.0175F;
		this.tongue_yRot = headYaw * 0.0175F;
		this.tongueDistance = entity.tongueDistance;
		this.targetTongueDistance = entity.targetTongueDistance;
		this.eyeHeight = entity.getEyeHeight();
		this.renderToBuffer(null, null, 0, 0, 0);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
			int overlayCoord, int packedColor) {
		this.body.render(poseStack, vertexConsumer, packedLight, overlayCoord, packedColor);

		// Render tongue
		if (this.tongueDistance <= 0.01F)
			return;

		poseStack.pushPose();
		this.body.translateAndRotate(poseStack);
		this.renderTongue(poseStack, vertexConsumer, packedLight, overlayCoord, packedColor);
		poseStack.popPose();
	}

	public void renderTongue(PoseStack matrices, VertexConsumer vertexConsumer, int packedLight,
			int overlayCoord, int packedColor) {
		matrices.pushPose();

		float mouthHeightRelative = 3F/16F;  // Mouth to bottom of the model
		float mouthHorizontalOffsetRelative = 2F/16F;  // Horizontal distance between mouth and eye
		// Distance between the target and eye
		float tongueDistance = this.targetTongueDistance;

		// Model parameters, with respect to the real world
		// Mouth to bottom of the model
		float mouthHeight = mouthHeightRelative * this.bodyScale;
		// Mouth to eye horizontal and vertical distance
		float mouthHorizontalOffset = mouthHorizontalOffsetRelative * this.bodyScale;
		float mouthVerticalOffset = eyeHeight - mouthHeight;
		float mouthEyeDistanceSqr = mouthVerticalOffset * mouthVerticalOffset
				+ mouthHorizontalOffset * mouthHorizontalOffset;
		float mouthEyeDistance = Mth.sqrt(mouthEyeDistanceSqr);

		// Angle between eye-mouth and vertical-down
		float angleMouthEye = (float) Mth.atan2(mouthHorizontalOffset, mouthVerticalOffset);
		// Distance between tongue and target
		float cosEM_ET = Mth.sin(angleMouthEye + tongue_xRot);
		float lenSqr = mouthEyeDistanceSqr + tongueDistance * tongueDistance -
				2 * mouthEyeDistance * tongueDistance * cosEM_ET;
		float len = Mth.sqrt(lenSqr);
		// Actual pitch
		float cosME_MT = (mouthEyeDistanceSqr + lenSqr - tongueDistance * tongueDistance) /
				(2 * mouthEyeDistance * len);
		float xRot = (float) (Math.acos(cosME_MT) - Mth.HALF_PI - angleMouthEye);

		// Smooth animation
		len *= this.tongueDistance / this.targetTongueDistance;

		//matrices.translate(0, -eyeHeight / this.bodyScale, 0);
		matrices.translate(0, -mouthHeightRelative, -mouthHorizontalOffsetRelative);

		//matrices.mulPose(Vector3f.XP.rotation(tongue_xRot));
		if (xRot != 0.0F) {
			matrices.mulPose(Axis.XP.rotation(xRot));
		}

		if (tongue_yRot != 0.0F) {
			matrices.mulPose(Axis.YP.rotation(tongue_yRot));
		}

		//matrices.scale(1, 1, tongueDistance / this.bodyScale * 16F);
		matrices.scale(1, 1, len / this.bodyScale * 16F);

		ModelPart.Cube cuboid = new ModelPart.Cube(16, 0, -1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0, 0, 0, false, 64, 64, ALL_VISIBLE);
		cuboid.compile(matrices.last(), vertexConsumer, packedLight, overlayCoord, packedColor);

		matrices.popPose();
	}
}
