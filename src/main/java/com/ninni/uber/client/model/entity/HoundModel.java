package com.ninni.uber.client.model.entity;

import com.ninni.uber.client.animation.HoundAnimations;
import com.ninni.uber.entity.Hound;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.definitions.CamelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

@Environment(EnvType.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class HoundModel<T extends Hound> extends HierarchicalModel<T> {
    public static final String MODEL = "model";
    public static final String LEFT_SCYTHE = "left_scythe";
    public static final String RIGHT_SCYTHE = "right_scythe";
    public static final String TAIL_BASE = "tail_base";
    public static final String TAIL_TIP = "tail_tip";

    private final ModelPart root;

    private final ModelPart model;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart leftScythe;
    private final ModelPart rightArm;
    private final ModelPart rightScythe;
    private final ModelPart head;
    private final ModelPart tailBase;
    private final ModelPart tail;
    private final ModelPart tailTip;


    public HoundModel(ModelPart root) {
        this.root = root;

        this.model = root.getChild(MODEL);

        this.body = model.getChild(PartNames.BODY);
        this.leftArm = model.getChild(PartNames.LEFT_ARM);
        this.rightArm = model.getChild(PartNames.RIGHT_ARM);

        this.leftScythe = leftArm.getChild(LEFT_SCYTHE);

        this.rightScythe = rightArm.getChild(RIGHT_SCYTHE);

        this.head = body.getChild(PartNames.HEAD);
        this.tailBase = body.getChild(TAIL_BASE);

        this.tail = tailBase.getChild(PartNames.TAIL);

        this.tailTip = tail.getChild(TAIL_TIP);

    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch)  {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(HoundAnimations.WALK, limbAngle, limbDistance, 2.0f, 2.5f);
        this.animate(entity.attackAnimationState, HoundAnimations.ATTACK, animationProgress);
        this.animate(entity.idleAnimationState, HoundAnimations.IDLE, animationProgress);
        this.animate(entity.emergeAnimationState, HoundAnimations.EMERGE, animationProgress);
        this.animate(entity.limpAnimationState, HoundAnimations.LIMP, animationProgress);
        this.head.xRot += headPitch * ((float) Math.PI / 180f);
        this.head.yRot += headYaw * ((float) Math.PI / 180f);

    }

    @Override
    public ModelPart root() {
        return root;
    }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition model = partdefinition.addOrReplaceChild(
                MODEL,
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition rightarm = model.addOrReplaceChild(
                PartNames.RIGHT_ARM,
                CubeListBuilder.create()
                        .texOffs(34, 0)
                        .mirror()
                        .addBox(-3.0F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F)
                        .mirror(false),
                PartPose.offset(-3.5F, -15.0F, 0.0F)
        );

        PartDefinition rightscythe = rightarm.addOrReplaceChild(
                RIGHT_SCYTHE,
                CubeListBuilder.create()
                        .texOffs(21, 9).mirror()
                        .addBox(-0.5F, -5.0F, 0.5F, 1.0F, 6.0F, 11.0F)
                        .mirror(false),
                PartPose.offset(-1.5F, 14.0F, 0.5F)
        );

        PartDefinition leftarm = model.addOrReplaceChild(
                PartNames.LEFT_ARM,
                CubeListBuilder.create()
                        .texOffs(34, 0)
                        .addBox(0.0F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F),
                PartPose.offset(3.5F, -15.0F, 0.0F)
        );

        PartDefinition leftscythe = leftarm.addOrReplaceChild(
                LEFT_SCYTHE,
                CubeListBuilder.create()
                        .texOffs(21, 9)
                        .addBox(-0.5F, -5.0F, 0.5F, 1.0F, 6.0F, 11.0F),
                PartPose.offset(1.5F, 14.0F, 0.5F)
        );

        PartDefinition body = model.addOrReplaceChild(
                PartNames.BODY,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -5.5F, -4.0F, 7.0F, 11.0F, 9.0F)
                        .texOffs(48, -8)
                        .addBox(1.5F, -9.5F, -4.0F, 0.0F, 4.0F, 8.0F)
                        .texOffs(48, -4)
                        .addBox(-1.5F, -9.5F, -4.0F, 0.0F, 4.0F, 8.0F),
                PartPose.offset(0.0F, -12.5F, -1.0F)
        );

        PartDefinition head = body.addOrReplaceChild(
                PartNames.HEAD,
                CubeListBuilder.create()
                        .texOffs(20, 38)
                        .addBox(-4.5F, -3.5F, -4.5F, 9.0F, 5.0F, 5.0F)
                        .texOffs(0, 35)
                        .addBox(-2.5F, 1.5F, -4.5F, 5.0F, 8.0F, 5.0F),
                PartPose.offset(0.0F, 0.0F, -3.5F)
        );

        PartDefinition tailbase = body.addOrReplaceChild(
                TAIL_BASE,
                CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-2.5F, -2.5F, -0.5F, 5.0F, 5.0F, 10.0F),
                PartPose.offset(0.0F, 0.0F, 4.5F)
        );

        PartDefinition tail = tailbase.addOrReplaceChild(
                PartNames.TAIL,
                CubeListBuilder.create()
                        .texOffs(21, 26)
                        .addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 9.0F),
                PartPose.offset(0.0F, 0.0F, 9.0F));

        PartDefinition tailtip = tail.addOrReplaceChild(
                TAIL_TIP,
                CubeListBuilder.create()
                        .texOffs(37, 17)
                        .addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 9.0F),
                PartPose.offset(0.0F, 0.0F, 7.5F)
        );

        return LayerDefinition.create(meshdefinition, 64, 48);
    }
}
