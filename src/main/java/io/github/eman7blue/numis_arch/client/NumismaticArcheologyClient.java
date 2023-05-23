package io.github.eman7blue.numis_arch.client;

import io.github.eman7blue.numis_arch.block.entity.NumisArchBlockEntityTypes;
import io.github.eman7blue.numis_arch.client.gui.MagnifyingGlassHandler;
import io.github.eman7blue.numis_arch.client.gui.screen.NumismaticDeskScreen;
import io.github.eman7blue.numis_arch.client.render.block.entity.AncientActivatorBlockEntityRenderer;
import io.github.eman7blue.numis_arch.screen.NumisArchScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class NumismaticArcheologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(NumisArchBlockEntityTypes.ANCIENT_ACTIVATOR, AncientActivatorBlockEntityRenderer::new);
        HudRenderCallback.EVENT.register(MagnifyingGlassHandler::onRenderHud);
        HandledScreens.register(NumisArchScreenHandlerType.NUMISMATIC_DESK, NumismaticDeskScreen::new);
    }
}
