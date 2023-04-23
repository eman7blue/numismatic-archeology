package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(at = @At("HEAD"), method = "afterBreak")
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if(!world.isClient && blockEntity instanceof BrushableBlockEntity brushableBlockEntity) {
            NumismaticArcheology.ARCHEOLOGY_BLOCK_DESTROYED.trigger((ServerPlayerEntity) player, state, ((BrushableBlockEntityAccessor) brushableBlockEntity).getLootTable());
        }
    }
}
