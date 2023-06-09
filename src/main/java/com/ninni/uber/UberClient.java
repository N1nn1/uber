package com.ninni.uber;

import com.google.common.reflect.Reflection;
import com.ninni.uber.client.model.entity.UberEntityModelLayers;
import com.ninni.uber.client.particle.*;
import com.ninni.uber.client.renderer.entity.HoundRenderer;
import com.ninni.uber.registry.UberBlocks;
import com.ninni.uber.registry.UberEntityTypes;
import com.ninni.uber.registry.UberParticleTypes;
import com.ninni.uber.registry.secondary.UberFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import static com.ninni.uber.Uber.MOD_ID;

@Environment(EnvType.CLIENT)
public class UberClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(UberFluids.MANA, UberFluids.FLOWING_MANA, new SimpleFluidRenderHandler(
                new ResourceLocation(MOD_ID, "block/mana_still"),
                new ResourceLocation(MOD_ID, "block/mana_flow")
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), UberFluids.MANA, UberFluids.FLOWING_MANA);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                UberBlocks.PHASMOFOLLICLE,
                UberBlocks.ISTALKS,
                UberBlocks.POTTED_ISTALKS,
                UberBlocks.TALL_ISTALKS,
                UberBlocks.ELYSIAVELD,
                UberBlocks.CLOTTONBALL
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                UberBlocks.MANA_GEL
        );

        Reflection.initialize(UberEntityModelLayers.class);
        EntityRendererRegistry.register(UberEntityTypes.HOUND, HoundRenderer::new);

        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.BLOWING_WIND, BlowingWindParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.WIND, WindParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.CALM_WIND, CalmWindParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.MANA, ManaParticle.NormalFactory::new);
        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.MANA_CAULDRON, ManaParticle.CauldronFactory::new);
        ParticleFactoryRegistry.getInstance().register(UberParticleTypes.CLOTTON, ClottonParticle.Factory::new);
    }
}
