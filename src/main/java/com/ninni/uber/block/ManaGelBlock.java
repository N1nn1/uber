package com.ninni.uber.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class ManaGelBlock extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    protected static final VoxelShape SHAPE = Block.box(1.0, 1.0, 1.0, 15.0, 15.0, 15.0);

    public ManaGelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, state.setValue(ACTIVE, false), Block.UPDATE_ALL);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected Vec3 getJumpVelocity(Entity entity) {
        if (!entity.isSuppressingBounce()) {
            Vec3 vel = entity.getDeltaMovement();
            double x = vel.x() * 1.3f;
            double z = vel.z() * 1.3f;
            return new Vec3(x, 1, z);
        }
        return  Vec3.ZERO;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level.getBlockState(pos.above()).isAir() && !entity.isSuppressingBounce()) {
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
