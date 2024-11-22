package net.mobz.client.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

import net.mobz.MobZ;

public class TadpoleEntityModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart finw;
	private final ModelPart fine;

	public final static ModelLayerLocation MODEL_LAYER_LOC = new ModelLayerLocation(
			MobZ.resLoc("tadpole_model"), "main");

	public static LayerDefinition createBodyLayer() {
	      MeshDefinition meshdefinition = new MeshDefinition();
	      PartDefinition root = meshdefinition.getRoot();

	      PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
	    		  .texOffs(0, 0).addBox(-4.0F, -1.0F, -1.0F, 3.0F, 2.0F, 3.0F)
	    		  .texOffs(0, 11).addBox(-4.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F)
	    		  .texOffs(8, 9).addBox(-2.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F)
	    		  , PartPose.offset(2.0F, 23.0F, -3.0F));

	      body.addOrReplaceChild("tail", CubeListBuilder.create()
	    		  .texOffs(0, 0).addBox(0.0F, -1.6F, -1.0F, 0.0F, 3.0F, 6.0F)
	    		  .texOffs(4, 9).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F)
	    		  , PartPose.offset(-2.5F, 0.0F, 2.0F));

	      body.addOrReplaceChild("finw", CubeListBuilder.create()
	    		  .texOffs(0, 9).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.01F, 2.0F)
	    		  , PartPose.offset(-1.0F, 0.0F, 0.0F));

	      PartDefinition fine = body.addOrReplaceChild("fine", CubeListBuilder.create()
	    		  , PartPose.offset(-4.0F, 0.0F, 0.0F));

	      fine.addOrReplaceChild("cube_r1", CubeListBuilder.create()
	    		  .texOffs(7, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.01F, 2.0F), PartPose.rotation(0.0F, 0.0F, 0.0436F));

	      return LayerDefinition.create(meshdefinition, 16, 16);
	}

	public TadpoleEntityModel(ModelPart modelPart) {
		super(modelPart);
		this.body = modelPart.getChild("body");
		this.tail = body.getChild("tail");
		this.finw = body.getChild("finw");
		this.fine = body.getChild("fine");
	}

	@Override
	public void setupAnim(LivingEntityRenderState renderState) {
		float f = renderState.isInWater ? 1.0F : 1.5F;

		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * renderState.ageInTicks);
		this.finw.xRot = f * 0.10F * Mth.sin(0.8F * renderState.ageInTicks);
		this.fine.xRot = f * 0.10F * Mth.sin(0.8F * renderState.ageInTicks);
	}
}