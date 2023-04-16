package com.ninni.uber.mixin;

import com.ninni.uber.UberTags;
import com.ninni.uber.registry.UberSoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public Level level;
    @Shadow public abstract void setDeltaMovement(Vec3 vec3);
    @Shadow public abstract Vec3 getDeltaMovement();
    @Shadow public abstract BlockState getFeetBlockState();
    @Shadow public abstract void resetFallDistance();
    @Shadow public abstract boolean isVehicle();
    @Shadow @Final protected RandomSource random;
    @Shadow protected boolean firstTick;
    protected boolean wasTouchingMana;

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void addManaEffects(CallbackInfo ci) {
        Entity $this = (Entity) (Object) this;

        //Mana movement effects
        if ((Entity) (Object) this instanceof Player player) {
            if (player.getAbilities().flying || player.isSpectator()) return;
        }
        if (this.getFeetBlockState().getFluidState().is(UberTags.MANA)) {
            if ((Entity) (Object) this instanceof Player player) {
                Iterable<ItemStack> iterable = player.getArmorSlots();
                for (ItemStack itemStack : iterable) {
                    Item item = itemStack.getItem();

                    //TODO replace gold with anchorzine
                    if (item instanceof ArmorItem armorItem && armorItem.getMaterial() == ArmorMaterials.GOLD) {
                        this.setDeltaMovement(this.getDeltaMovement().multiply(0.8f, 0.8f, 0.8f));
                        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.2f, 0));
                    }
                }
            }
            if (this.isVehicle()) this.setDeltaMovement(this.getDeltaMovement().multiply(0, 0, 0));
            this.setDeltaMovement(this.getDeltaMovement().add(0.0f, 0.2f, 0.0f));
            this.resetFallDistance();

            //Mana Splash sounds
            if (!$this.isEyeInFluid(UberTags.MANA)) {
                if (!this.wasTouchingMana && !this.firstTick) {
                    Entity entity = $this.isVehicle() && $this.getControllingPassenger() != null ? $this.getControllingPassenger() : $this;
                    float f = entity == $this ? 0.2f : 0.9f;
                    Vec3 vec3 = entity.getDeltaMovement();
                    float g = Math.min(1.0f, (float) Math.sqrt(vec3.x * vec3.x * (double) 0.2f + vec3.y * vec3.y + vec3.z * vec3.z * (double) 0.2f) * f);
                    if (g < 0.25f) {
                        $this.playSound(UberSoundEvents.MANA_SPLASH, g, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.4f);
                    }
                    else {
                        $this.playSound(UberSoundEvents.MANA_SPLASH_HEAVY, g, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.4f);
                    }
                    this.wasTouchingMana = true;
                } else this.wasTouchingMana = false;
            }
        }
    }

}
