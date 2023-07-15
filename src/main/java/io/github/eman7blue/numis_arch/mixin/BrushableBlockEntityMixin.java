package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Direction;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrushableBlockEntity.class)
public abstract class BrushableBlockEntityMixin {

    @Shadow private int brushesCount;

    @Redirect(method = "brush",
            at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/BrushableBlockEntity;brushesCount:I", opcode = Opcodes.GETFIELD))
    private int injected(BrushableBlockEntity brushableBlockEntity, long worldTime, PlayerEntity player, Direction hitDirection) {
        if (player.getMainHandStack().isOf(Items.BRUSH)) {
            return brushesCount;
        } else if (player.getMainHandStack().isOf(NumisArchItems.DIAMOND_BRUSH)) {
            brushesCount = brushesCount + 1;
            return brushesCount;
        } else {
            return brushesCount;
        }

    }
}
