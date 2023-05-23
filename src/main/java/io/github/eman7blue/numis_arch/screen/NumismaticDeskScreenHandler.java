package io.github.eman7blue.numis_arch.screen;

import io.github.eman7blue.numis_arch.item.CoinItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.village.TradeOffers;

public class NumismaticDeskScreenHandler extends ScreenHandler {
    private final Slot input;
    private final Inventory inventory;
    protected final PropertyDelegate propertyDelegate;

    public NumismaticDeskScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(13),  new ArrayPropertyDelegate(1));
    }

    public NumismaticDeskScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(NumisArchScreenHandlerType.NUMISMATIC_DESK, syncId);
        checkSize(inventory, 13);
        if (propertyDelegate.size() == 1) {

        }
        this.propertyDelegate = propertyDelegate;
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.input = this.addSlot(new Slot(inventory, 0, 26, 35){
            @Override
            public boolean canInsert(ItemStack stack) {
                return CoinItem.isUnknown(stack);
            }
        });
        this.addSlots(playerInventory, inventory);
        this.addProperties(propertyDelegate);
    }

    private void addSlots(PlayerInventory playerInventory, Inventory inventory) {
        int m, l;
        for (m = 0; m < 3; m++) {
            for (l = 0; l < 4; l++) {
                this.addSlot(new Slot(inventory, 1 + l + m * 4, 80 + l * 18, 17 + m * 18){
                    @Override
                    public boolean canInsert(ItemStack stack) {
                        return stack.isIn(CoinItem.COIN_ITEM_TAG) && !CoinItem.isUnknown(stack);
                    }
                });
            }
        }
        for (m = 0; m < 3; m++) {
            for (l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; m++) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotNum) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotNum);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (!originalStack.isIn(CoinItem.COIN_ITEM_TAG)) {
                return ItemStack.EMPTY;
            }
            if (slotNum < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.canInsert(stack);
    }

    public int getGradeProgress() {
        return propertyDelegate.get(0);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
