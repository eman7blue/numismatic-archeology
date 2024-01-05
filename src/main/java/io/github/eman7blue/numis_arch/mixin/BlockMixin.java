package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.advancements.NumisArchCriteria;
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
public abstract class BlockMixin {
    @Inject(at = @At("HEAD"), method = "onBreak")
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if (world.getBlockEntity(pos) != null) {
            if(!world.isClient && world.getBlockEntity(pos) instanceof BrushableBlockEntity brushableBlockEntity) {
                NumisArchCriteria.ARCHEOLOGY_BLOCK_DESTROYED.trigger((ServerPlayerEntity) player, state, ((BrushableBlockEntityAccessor) brushableBlockEntity).getLootTable());
            }
        }
    }
}
