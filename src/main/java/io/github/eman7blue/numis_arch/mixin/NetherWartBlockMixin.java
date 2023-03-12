package io.github.eman7blue.numis_arch.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin {
    @Redirect(method = "canPlantOnTop",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean brushUsageBrushable(BlockState blockState, Block block) {
        return blockState.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("numis_arch:nether_wart_plantable")));
    }
}