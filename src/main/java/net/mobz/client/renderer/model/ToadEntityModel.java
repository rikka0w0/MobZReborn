package net.mobz.client.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import net.mobz.MobZ;
import net.mobz.client.renderer.entity.state.ToadRenderState;

public class ToadEntityModel extends EntityModel<ToadRenderState> {
	public final static ModelLayerLocation MODEL_LAYER_LOC = new ModelLayerLocation(MobZ.resLoc("toad_model"), "main");

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
	private final ModelPart tongue;

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
				.texOffs(10*64, 12*64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, CubeDeformation.NONE, 64, 64)
				, PartPose.offset(0, -3, 0));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public ToadEntityModel(ModelPart modelPart, float bodyScale) {
		super(modelPart);
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
		this.tongue = body.getChild("tongue");
	}

	@Override
	public void setupAnim(ToadRenderState renderState) {
		super.setupAnim(renderState);

		this.backlege.xRot = Mth.cos(renderState.walkAnimationPos) * 1.4F * renderState.walkAnimationSpeed;
		this.frontlege.xRot = -this.backlege.xRot;

		this.frontlegw.xRot = this.backlege.xRot;
		this.backlegw.xRot = this.frontlege.xRot;

		if(!renderState.onGround) {
			this.backlegw.xRot = 2F;
			this.backlege.xRot = 2F;
		}

		lipTop.y = -renderState.mouthDistance;
		lipBottom.y = renderState.mouthDistance;

		// Configure tongue
		if (renderState.tongueDistance <= 0.01F)
			return;

		float mouthHeightRelative = 3F/16F;  // Mouth to bottom of the model
		float mouthHorizontalOffsetRelative = 2F/16F;  // Horizontal distance between mouth and eye
		// Distance between the target and eye
		float tongueDistance = renderState.targetTongueDistance;

		// Model parameters, with respect to the real world
		// Mouth to bottom of the model
		float mouthHeight = mouthHeightRelative * this.bodyScale;
		// Mouth to eye horizontal and vertical distance
		float mouthHorizontalOffset = mouthHorizontalOffsetRelative * this.bodyScale;
		float mouthVerticalOffset = renderState.eyeHeight - mouthHeight;
		float mouthEyeDistanceSqr = mouthVerticalOffset * mouthVerticalOffset
				+ mouthHorizontalOffset * mouthHorizontalOffset;
		float mouthEyeDistance = Mth.sqrt(mouthEyeDistanceSqr);

		// Angle between eye-mouth and vertical-down
		float angleMouthEye = (float) Mth.atan2(mouthHorizontalOffset, mouthVerticalOffset);
		// Distance between tongue and target
		float cosEM_ET = Mth.sin(angleMouthEye + renderState.xRot * 0.0175F);
		float lenSqr = mouthEyeDistanceSqr + tongueDistance * tongueDistance -
				2 * mouthEyeDistance * tongueDistance * cosEM_ET;
		float len = Mth.sqrt(lenSqr);
		// Actual pitch
		float cosME_MT = (mouthEyeDistanceSqr + lenSqr - tongueDistance * tongueDistance) /
				(2 * mouthEyeDistance * len);
		float xRot = (float) (Math.acos(cosME_MT) - Mth.HALF_PI - angleMouthEye);

		// Smooth animation
		len *= renderState.tongueDistance / renderState.targetTongueDistance;

		this.tongue.zScale = len / this.bodyScale * 16F;
		this.tongue.xRot = xRot;
//		this.tongue.yRot = renderState.yRot * 0.0175F;
	}
}
