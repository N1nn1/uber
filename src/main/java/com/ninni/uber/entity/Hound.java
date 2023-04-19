package com.ninni.uber.entity;

import com.ninni.uber.entity.ai.goal.HoundAttackGoal;
import com.ninni.uber.entity.pose.UberPose;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class Hound extends Monster {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState limpAnimationState = new AnimationState();
    public final AnimationState emergeAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    private int attackTick;

    public Hound(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.75f, true));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createHoundAttributes() {
        return createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, 18.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("AttackTick", this.attackTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.attackTick = compoundTag.getInt("AttackTick");
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackTick > 0) {
            --this.attackTick;
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (DATA_POSE.equals(entityDataAccessor)) {
            if (this.getPose() == Pose.EMERGING) {
                this.emergeAnimationState.start(this.tickCount);
            } else this.emergeAnimationState.stop();

            if (this.getPose() == UberPose.LIMP.get()) {
                this.limpAnimationState.start(this.tickCount);
            } else this.limpAnimationState.stop();
        }
        super.onSyncedDataUpdated(entityDataAccessor);
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 4) {
            this.attackAnimationState.start(this.tickCount);
        }
        super.handleEntityEvent(b);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackTick = 20;
        this.level.broadcastEntityEvent(this, (byte)4);
        this.playSound(SoundEvents.RAVAGER_ATTACK, 1.0f, 1.0f);
        return super.doHurtTarget(entity);
    }

    @Override
    public void tick() {
        if (this.level.isClientSide()) {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        }
        super.tick();
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || this.attackTick > 0;
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType<Hound> type, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return false;
    }
}
