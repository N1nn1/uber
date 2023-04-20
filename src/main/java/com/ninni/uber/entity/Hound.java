package com.ninni.uber.entity;

import com.ninni.uber.entity.pose.UberPose;
import com.ninni.uber.registry.UberEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Hound extends Monster {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState limpAnimationState = new AnimationState();
    public final AnimationState emergeAnimationState = new AnimationState();
    private boolean spawnedAtDeath;
    public int idleAnimationTimeout = 0;
    private int attackTick;
    private int emergeTick;

    public Hound(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.75f, true));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EnderMan.class, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createHoundAttributes() {
        return createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("AttackTick", this.attackTick);
        compoundTag.putInt("EmergeTick", this.emergeTick);
        compoundTag.putBoolean("SpawnedAtDeath", this.spawnedAtDeath);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.attackTick = compoundTag.getInt("AttackTick");
        this.emergeTick = compoundTag.getInt("EmergeTick");
        this.spawnedAtDeath = compoundTag.getBoolean("SpawnedAtDeath");
    }
    public boolean isSpawnedAtDeath() {
        return this.spawnedAtDeath;
    }
    public void setSpawnedAtDeath(boolean bl) {
        this.spawnedAtDeath = bl;
    }


    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackTick > 0) this.attackTick--;
        if (this.emergeTick > 0 && !level.isClientSide) {
            this.setPose(Pose.EMERGING);
            this.emergeTick--;
            Player player = this.level.getNearestPlayer(this, 10);
            if (player != null){
                this.getLookControl().setLookAt(player);
            }
        }
        if (!level.isClientSide && this.emergeTick == 0) this.setPose(Pose.STANDING);
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
        if (b == 4) this.attackAnimationState.start(this.tickCount);
        super.handleEntityEvent(b);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof Player) this.attackTick = 20;
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
            } else --this.idleAnimationTimeout;
            if (this.getPose() == Pose.EMERGING) this.clientDiggingParticles(this.emergeAnimationState);
        }

        super.tick();
    }

    private void clientDiggingParticles(AnimationState animationState) {
        if ((float)animationState.getAccumulatedTime() < 4500.0f) {
            RandomSource randomSource = this.getRandom();
            BlockState blockState = this.getBlockStateOn();
            if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
                for (int i = 0; i < 5; ++i) {
                    double d = this.getX() + (double) Mth.randomBetween(randomSource, -0.7f, 0.7f);
                    double e = this.getY();
                    double f = this.getZ() + (double)Mth.randomBetween(randomSource, -0.7f, 0.7f);
                    this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState), d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void tickDeath() {
        if (this.deathTime < 10 && !this.level.isClientSide() && !this.isSpawnedAtDeath()) {

            Hound hound = UberEntityTypes.HOUND.create(this.level);
            assert hound != null;
            double x = this.getX() + Mth.randomBetween(hound.random, -3f, 3f);
            double y = this.getY() + this.random.nextInt(-1, 1);
            double z = this.getZ() + Mth.randomBetween(hound.random, -3f, 3f);
            BlockPos pos = new BlockPos((int)x, (int)y, (int)z);
            BlockPos belowPos = new BlockPos((int)x, (int)y - 1, (int)z);
            BlockState spawnState = this.level.getBlockState(pos);
            BlockState belowSpawnState = this.level.getBlockState(belowPos);

            boolean bl = !spawnState.getBlock().isCollisionShapeFullBlock(spawnState, this.level, pos);
            boolean bl2 = belowSpawnState.getBlock().isCollisionShapeFullBlock(belowSpawnState, this.level, belowPos);


            if (bl && bl2) hound.moveTo(x, y, z, this.getYRot(), this.getXRot());
            hound.emergeTick = 60;
            hound.setSpawnedAtDeath(true);
            this.level.addFreshEntity(hound);
        }
        super.tickDeath();
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || this.attackTick > 0;
    }

    @Override
    public void travel(Vec3 movementInput) {
        if (this.emergeTick > 0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0, 1.0, 0.0));
            movementInput = movementInput.multiply(0.0, 1.0, 0.0);
        }
        super.travel(movementInput);
    }

    @Override
    protected void doPush(Entity entity) {
        if (this.emergeTick > 0) super.doPush(entity);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (this.emergeTick > 0 && !damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return true;
        }
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public boolean isPushable() {
        return !(this.emergeTick > 0) && super.isPushable();
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType<Hound> type, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return false;
    }
}
