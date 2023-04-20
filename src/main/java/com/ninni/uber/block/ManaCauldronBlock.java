package com.ninni.uber.block;

import com.ninni.uber.registry.UberParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class ManaCauldronBlock extends Block {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public ManaCauldronBlock(Properties properties) {
        super(properties);
    }


    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        int l = getLevel(state);
        double x = pos.getX() + 0.5D + (0.5D - random.nextDouble());
        double y = pos.getY() + (0.7D + (l - 1) * 0.2D);
        double z = pos.getZ() + 0.5D + (0.5D - random.nextDouble());
        if (random.nextInt(5) == 0) level.addParticle(UberParticleTypes.MANA_CAULDRON, x, y, z, 0, 0, 0);
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (isEntityInsideContent(pos, entity)) {
            if (entity instanceof Player player) {
                Iterable<ItemStack> iterable = player.getArmorSlots();
                for (ItemStack itemStack : iterable) {
                    Item item = itemStack.getItem();

                    //TODO replace gold with anchorzine
                    if (item instanceof ArmorItem armorItem && armorItem.getMaterial() == ArmorMaterials.GOLD) {
                        entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.8f, 0.8f, 0.8f));
                        entity.setDeltaMovement(entity.getDeltaMovement().add(0, -(0.2f * (getLevel(state) + 1)), 0));
                    }
                }
            }
            if (entity.isVehicle()) entity.setDeltaMovement(entity.getDeltaMovement().multiply(0, 0, 0));
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0f, (0.2f * (getLevel(state) + 1)), 0.0f));
            entity.resetFallDistance();
        }
    }

    protected boolean isEntityInsideContent(BlockPos pos, Entity entity) {
        return entity.getY() < pos.getY() + 0.4F && entity.getBoundingBox().maxY > pos.getY() + 0.25;
    }

    public static int getLightLevel(BlockState state) {
        return state.getValue(LEVEL) * 3;
    }

    public static void setLevel(Level world, BlockPos pos, BlockState state, int level) {
        world.setBlock(pos, state.setValue(LEVEL, level), UPDATE_ALL);
    }

    public static int getLevel(BlockState state) {
        return state.getValue(LEVEL);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return box(2, 4, 2, 14, 16, 14);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.join(Shapes.block(), Shapes.or(box(0, 0, 4, 16, 3, 12), box(4, 0, 0, 12, 3, 16), box(2, 0, 2, 14, 3, 14), getInteractionShape(state, level, pos)), BooleanOp.ONLY_FIRST);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.CAULDRON);
    }
    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LEVEL);
    }
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }
    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}

