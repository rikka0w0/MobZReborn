package net.mobz.client.renderer.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.mobz.MobZ;
import net.mobz.client.renderer.entity.state.WaspRenderState;

public class WaspModel extends EntityModel<WaspRenderState> {
	public final static ModelLayerLocation MODEL_LAYER_LOC = new ModelLayerLocation(MobZ.resLoc("wasp"), "main");

	public static final AnimationDefinition FLYING_WINGS_ANIMATION = AnimationDefinition.Builder.withLength(0.1F).looping()
			.addAnimation("right_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -60.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.05F, KeyframeAnimations.degreeVec(15.0F, 15.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -60.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 60.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.05F, KeyframeAnimations.degreeVec(15.0F, -15.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 60.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final AnimationDefinition RESTING_WINGS_ANIMATION = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("right_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4583F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4583F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
									AnimationChannel.Interpolations.LINEAR)))
			.build();

	public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);
	private final ModelPart bone;

	private final ModelPart leftAntenna;
	private final ModelPart rightAntenna;

	private final ModelPart frontLeg;
	private final ModelPart midLeg;
	private final ModelPart backLeg;

	private final ModelPart tail;
	private final ModelPart tailPart1;
	private final ModelPart tailPart2;
	private final ModelPart stinger;

	private float rollAmount;

	private final KeyframeAnimation flying_wings_animation;
	private final KeyframeAnimation resting_wings_animation;

	public WaspModel(ModelPart root) {
		super(root);
		this.bone = root.getChild("bone");

		ModelPart head = this.bone.getChild("head");
		this.leftAntenna = head.getChild("left_antenna");
		this.rightAntenna = head.getChild("right_antenna");

		ModelPart body = this.bone.getChild("body");
		this.frontLeg = body.getChild("front_legs");
		this.midLeg = body.getChild("middle_legs");
		this.backLeg = body.getChild("back_legs");

		this.tail = this.bone.getChild("tail");
		this.tailPart1 = this.tail.getChild("subpart1");
		this.tailPart2 = this.tailPart1.getChild("subpart2");
		this.stinger = this.tailPart2.getChild("stinger");

		this.flying_wings_animation = FLYING_WINGS_ANIMATION.bake(root);
		this.resting_wings_animation = RESTING_WINGS_ANIMATION.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -5.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-3.0F, -7.0F, 1.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 18).addBox(-1.5F, -4.0F, 7.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_legs = body.addOrReplaceChild("front_legs", CubeListBuilder.create().texOffs(50, 14).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition middle_legs = body.addOrReplaceChild("middle_legs", CubeListBuilder.create().texOffs(50, 18).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition back_legs = body.addOrReplaceChild("back_legs", CubeListBuilder.create().texOffs(50, 22).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 7.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(22, 7).addBox(-17.0F, 0.0F, -5.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -7.0F, 4.0F, 0.3054F, 1.1345F, 0.0F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, 0.0F, -5.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -7.0F, 4.0F, 0.3054F, -1.1345F, 0.0F));

		PartDefinition head = bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 0).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_antenna = head.addOrReplaceChild("left_antenna", CubeListBuilder.create().texOffs(28, 22).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -7.0F, -3.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition right_antenna = head.addOrReplaceChild("right_antenna", CubeListBuilder.create().texOffs(37, 22).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -7.0F, -3.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition right_fang = head.addOrReplaceChild("right_fang", CubeListBuilder.create().texOffs(37, 29).addBox(-1.5F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.0F, -3.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition left_fang = head.addOrReplaceChild("left_fang", CubeListBuilder.create().texOffs(30, 29).addBox(-0.5F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -3.0F, -3.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition tail = bone.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(2, 24).addBox(-3.0F, -6.0F, 0.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 7.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition subpart1 = tail.addOrReplaceChild("subpart1", CubeListBuilder.create().texOffs(4, 35).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition subpart2 = subpart1.addOrReplaceChild("subpart2", CubeListBuilder.create().texOffs(7, 44).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition stinger = subpart2.addOrReplaceChild("stinger", CubeListBuilder.create().texOffs(10, 49).addBox(0.0F, -5.0F, 18.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -15.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(WaspRenderState renderState) {
		super.setupAnim(renderState);

		this.flying_wings_animation.apply(renderState.flyAnimationState, renderState.ageInTicks);
		this.resting_wings_animation.apply(renderState.restAnimationState, renderState.ageInTicks);

		this.rollAmount = renderState.rollAmount;
		this.stinger.visible = renderState.hasStinger;

		if (!renderState.isOnGround) {
			this.frontLeg.xRot = (float) (Math.PI / 4);
			this.midLeg.xRot = (float) (Math.PI / 4);
			this.backLeg.xRot = (float) (Math.PI / 4);
		} else {
			tail.xRot = -7.5F * (float) (Math.PI / 180);
			tailPart1.xRot = -7.5F * (float) (Math.PI / 180);
			tailPart2.xRot = -7.5F * (float) (Math.PI / 180);
		}

		if (this.rollAmount > 0.0F) {
			this.bone.xRot = Mth.rotLerpRad(this.rollAmount, this.bone.xRot, 3.0915928F);
		}
	}
}
