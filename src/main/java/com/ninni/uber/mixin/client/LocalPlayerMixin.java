package com.ninni.uber.mixin.client;

import com.mojang.authlib.GameProfile;
import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    @Shadow @Final protected Minecraft minecraft;
    protected boolean wasUnderMana;

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }


    @Inject(at = @At("HEAD"), method = "tick")
    private void addManaEffects(CallbackInfo ci) {
        this.updateIsUnderMana();
    }

    protected boolean isUnderMana() {
        this.wasUnderMana = this.isEyeInFluid(UberTags.MANA);
        return this.wasUnderMana;
    }

    protected void updateIsUnderMana() {
        boolean bl = this.wasUnderMana;
        boolean bl2 = isUnderMana();
        if (this.isSpectator()) {
            return;
        }
        if (!bl && bl2) {
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), UberSoundEvents.MANA_ENTER, SoundSource.AMBIENT, 0.5f, 1.0f, false);
        }
        if (bl && !bl2) {
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), UberSoundEvents.MANA_EXIT, SoundSource.AMBIENT, 1.0f, 1.0f, false);
        }
    }
}
