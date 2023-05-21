package io.github.eman7blue.numis_arch.client;

import io.github.eman7blue.numis_arch.block.entity.NumisArchBlockEntityTypes;
import io.github.eman7blue.numis_arch.client.handler.MagnifyingGlassHandler;
import io.github.eman7blue.numis_arch.client.render.block.entity.AncientActivatorBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class NumismaticArcheologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(NumisArchBlockEntityTypes.ANCIENT_ACTIVATOR, AncientActivatorBlockEntityRenderer::new);
        HudRenderCallback.EVENT.register(MagnifyingGlassHandler::onRenderHud);

    }
}
