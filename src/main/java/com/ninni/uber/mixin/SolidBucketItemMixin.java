package com.ninni.uber.mixin;

import com.ninni.uber.registry.UberBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SolidBucketItem.class)
public abstract class SolidBucketItemMixin extends BlockItem implements DispensibleContainerItem {
    public SolidBucketItemMixin(Block block, Properties properties) {
        super(block, properties);
    }

    @Inject(at = @At("HEAD"), method = "useOn", cancellable = true)
    public void U$use(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = useOnContext.getLevel();
        Player player = useOnContext.getPlayer();
        BlockPos pos = useOnContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        ItemStack item = useOnContext.getItemInHand();

        if (state.is(UberBlocks.MANA_CAULDRON) && item.is(Items.POWDER_SNOW_BUCKET)) {
            level.setBlock(pos, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(BlockStateProperties.LEVEL_CAULDRON, 3), 3);
            level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
            level.playSound(player, pos, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, SoundSource.BLOCKS, 1.0f, 1.0f);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
