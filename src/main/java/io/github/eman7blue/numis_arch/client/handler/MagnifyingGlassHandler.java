package io.github.eman7blue.numis_arch.client.handler;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MagnifyingGlassHandler {

    public static void onRenderHud(DrawContext drawContext, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        PlayerEntity player = minecraftClient.player;
        if (player != null && player.getActiveItem().isOf(NumisArchItems.MAGNIFYING_GLASS)) {
            BrushableBlockEntity brushableBlock = getBrushableBlockEntity();
            if (brushableBlock != null) {
                ItemStack loot = brushableBlock.getItem();
                Window window = minecraftClient.getWindow();
                int x = window.getScaledWidth() / 2 + 10;
                int y = window.getScaledHeight() / 2 + 10;
                drawContext.drawItem(loot, x, y);
            }
        }
    }

    @Nullable
    private static BrushableBlockEntity getBrushableBlockEntity() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        HitResult hitResult = minecraftClient.crosshairTarget;

        if (minecraftClient.world != null && hitResult instanceof BlockHitResult blockHitResult) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockEntity blockEntity = minecraftClient.world.getBlockEntity(pos);

            if (blockEntity instanceof BrushableBlockEntity brushableBlockEntity) {
                return brushableBlockEntity;
            }
        }
        return null;
    }
}
