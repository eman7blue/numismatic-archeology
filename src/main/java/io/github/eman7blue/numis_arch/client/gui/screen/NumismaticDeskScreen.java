package io.github.eman7blue.numis_arch.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.eman7blue.numis_arch.screen.NumismaticDeskScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumismaticDeskScreen extends HandledScreen<NumismaticDeskScreenHandler> {
    private static final Identifier TEXTURE = id("textures/gui/container/numismatic_desk.png");

    public NumismaticDeskScreen(NumismaticDeskScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        if (!this.handler.getStacks().get(0).isEmpty()) {
            int gradeTime = this.handler.getGradeProgress();
            context.drawTexture(TEXTURE, x + 49, y + 35, 176, 0, (gradeTime * 24) / 20 + 1, 16);
        }
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
