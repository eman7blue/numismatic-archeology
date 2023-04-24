package io.github.eman7blue.numis_arch.client.render.block.entity;

import io.github.eman7blue.numis_arch.block.entity.AncientActivatorBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;
@Environment(EnvType.CLIENT)
public class AncientActivatorBlockEntityRenderer implements BlockEntityRenderer<AncientActivatorBlockEntity> {
    private final BlockRenderManager renderManager;
    private final ItemRenderer itemRenderer;

    public AncientActivatorBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.renderManager = ctx.getRenderManager();
        this.itemRenderer = MinecraftClient.getInstance().getItemRenderer();
    }

    @Override
    public void render(AncientActivatorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().up());
        ItemStack itemStack = entity.getStack(0);
        double offset = Math.sin((Objects.requireNonNull(entity.getWorld()).getTime() + tickDelta) / 16.0) / 8.0;
        matrices.translate(0.5, 1.25 + offset, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.getWorld().getTime() + tickDelta) * 4));
        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, null, 0);
        matrices.pop();
    }
}
