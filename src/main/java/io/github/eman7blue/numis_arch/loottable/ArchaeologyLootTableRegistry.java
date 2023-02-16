package io.github.eman7blue.numis_arch.loottable;

import io.github.eman7blue.numis_arch.item.CoinItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.OneTwentyLootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ArchaeologyLootTableRegistry {

    private static LootTable rebuildDesertPyramidTable() {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                .with(ItemEntry.builder(Items.POTTERY_SHARD_ARCHER))
                .with(ItemEntry.builder(Items.POTTERY_SHARD_PRIZE))
                .with(ItemEntry.builder(Items.POTTERY_SHARD_SKULL))
                .with(ItemEntry.builder(Items.GUNPOWDER))
                .with(ItemEntry.builder(Items.TNT))
                .with(ItemEntry.builder(Items.DIAMOND))
                .with(ItemEntry.builder(Items.EMERALD))
                .with(ItemEntry.builder(CoinItems.TURTLE_COIN))).build();
    }
    public static void init(){
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (OneTwentyLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(id)) {
                return rebuildDesertPyramidTable();
            }
            return null;
        });

    }

}
