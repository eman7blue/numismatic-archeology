package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyArg(method = "<clinit>", index = 1, at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/block/entity/BlockEntityType$Builder;create(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;[Lnet/minecraft/block/Block;)Lnet/minecraft/block/entity/BlockEntityType$Builder;"), slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=brushable_block")))
    private static Block[] injected(Block[] blocks){
        return new Block[]{NumisArchBlocks.SUSPICIOUS_RED_SAND, Blocks.SUSPICIOUS_SAND, NumisArchBlocks.SUSPICIOUS_SOUL_SAND, Blocks.SUSPICIOUS_GRAVEL, NumisArchBlocks.SUSPICIOUS_END_STONE};
    }
}
