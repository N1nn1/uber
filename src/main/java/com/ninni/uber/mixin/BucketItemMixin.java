package com.ninni.uber.mixin;

import com.ninni.uber.registry.UberBlocks;
import com.ninni.uber.registry.UberSoundEvents;
import com.ninni.uber.registry.secondary.UberFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends Item implements DispensibleContainerItem {
    @Shadow @Final private Fluid content;

    public BucketItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void U$use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        BlockHitResult blockHitResult = BucketItem.getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        ItemStack itemStack = player.getItemInHand(interactionHand);
        BlockPos pos = blockHitResult.getBlockPos();
        BlockState state = level.getBlockState(pos);

        if (this.content == Fluids.EMPTY && state.is(UberBlocks.MANA_CAULDRON)) {
            level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
            level.playSound(player, pos, UberSoundEvents.BUCKET_FILL_MANA, SoundSource.BLOCKS, 1.0f, 1.0f);
            cir.setReturnValue(InteractionResultHolder.success(itemStack));
        }
        if (state.is(BlockTags.CAULDRONS)) {
            if (this.content == Fluids.LAVA) level.setBlock(pos, Blocks.LAVA_CAULDRON.defaultBlockState(), 3);
            else if (this.content == Fluids.WATER) level.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState().setValue(BlockStateProperties.LEVEL_CAULDRON, 3), 3);
            else if (this.content == UberFluids.MANA) level.setBlock(pos, UberBlocks.MANA_CAULDRON.defaultBlockState().setValue(BlockStateProperties.LEVEL_CAULDRON, 3), 3);
            else cir.setReturnValue(InteractionResultHolder.pass(itemStack));
            level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
            level.playSound(player, pos, UberSoundEvents.BUCKET_EMPTY_MANA, SoundSource.BLOCKS, 1.0f, 1.0f);
            cir.setReturnValue(InteractionResultHolder.success(itemStack));
        }
    }
}
