package io.github.eman7blue.numis_arch.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class ChargedThunderstoneItem extends Item {
    private static final DispenserBehavior DISPENSER_BEHAVIOR;
    public ChargedThunderstoneItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        if (!world.isClient) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
            lightningEntity.setCosmetic(false);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos.up()));
            world.spawnEntity(lightningEntity);
            stack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    static {
        DISPENSER_BEHAVIOR = new ItemDispenserBehavior(){
            @Override
            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                World world = pointer.world();
                BlockPos pos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING), 1);
                if (!world.isClient) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
                    lightningEntity.setCosmetic(false);
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos.up()));
                    world.spawnEntity(lightningEntity);
                    stack.decrement(1);
                }
                return stack;
            }

            @Override
            protected void playSound(BlockPointer pointer) {
                pointer.world().syncWorldEvent(WorldEvents.DISPENSER_DISPENSES, pointer.pos(), 0);
            }
        };
    }
}
