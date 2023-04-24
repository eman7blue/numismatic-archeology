package io.github.eman7blue.numis_arch.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AncientActivatorBlockEntity extends BlockEntity implements SidedInventory {
    private DefaultedList<ItemStack> storedItem;
    private final int[] SLOTS = {0};
    private static final int maxSize = 1;
    public AncientActivatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(NumisArchBlockEntityTypes.ANCIENT_ACTIVATOR, blockPos, blockState);
        this.storedItem = DefaultedList.ofSize(maxSize, ItemStack.EMPTY);
    }

    public void insertStack(ItemStack itemStack) {
        storedItem.set(0, itemStack.copyWithCount(1));
    }

    public BlockState struckByLightning(BlockState state, World world, BlockPos pos, LightningEntity entity) {
        return state;
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.storedItem = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.storedItem);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.storedItem);
    }

    @Override
    public int size() {
        return this.storedItem.size();
    }

    @Override
    public boolean isEmpty() {
        return this.storedItem.get(0).isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= 0 && slot < maxSize)
            return this.storedItem.get(slot);
        else
            return ItemStack.EMPTY;
    }

    public ItemStack removeStack() {
        return storedItem.set(0, ItemStack.EMPTY);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.storedItem, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.storedItem, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < maxSize)
            this.storedItem.set(slot, stack.copyWithCount(1));
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        this.storedItem.set(0, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return SLOTS;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }
}
