package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class MagnifyingGlassItem extends Item {

    public MagnifyingGlassItem(Settings settings){
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient()) {
            return super.use(world, player, hand);
        }
        BlockHitResult blockHitResult = MagnifyingGlassItem.raycast(world, player, RaycastContext.FluidHandling.NONE);
        BlockPos pos = blockHitResult.getBlockPos();
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BrushableBlockEntity brushableBlockEntity) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (brushableBlockEntity.getItem().isEmpty()) {
                brushableBlockEntity.generateItem(player);
                String item = brushableBlockEntity.getItem().getItem().toString();
                NumismaticArcheology.LOGGER.info((!world.isClient() ? "server: " + item  : "client: " + item));
            }
        }
        return ItemUsage.consumeHeldItem(world, player, hand);
    }
}
