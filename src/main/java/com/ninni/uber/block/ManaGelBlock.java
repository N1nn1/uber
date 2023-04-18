package com.ninni.uber.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;

public class ManaGelBlock extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public ManaGelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, state.setValue(ACTIVE, false), Block.UPDATE_ALL);
    }

    protected double getJumpHeight(Entity entity) {
        double height = 1.2;
        if (entity instanceof LivingEntity mob && mob.isFallFlying()) height += 0.2;
        return height;
    }

    protected Vec3 getJumpVelocity(Entity entity) {
        Vec3 vel = entity.getDeltaMovement();
        double x = vel.x();
        double z = vel.z();
        x = Mth.clamp(x * 2, -23, 23);
        z = Mth.clamp(z * 2, -23, 23);
        return new Vec3(x, getJumpHeight(entity), z);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide() && level.getBlockState(pos.above()).isAir()) {
            //world.playSound(null, pos, UberSoundEvents.MANA_GEL_BOUNCE, SoundSource.BLOCKS, 1, 1.2F);
            level.setBlock(pos, state.setValue(ACTIVE, true), Block.UPDATE_ALL);
            level.scheduleTick(pos, state.getBlock(), 2);
        }
        entity.setDeltaMovement(getJumpVelocity(entity));
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 0, entity.damageSources().fall());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }
}
