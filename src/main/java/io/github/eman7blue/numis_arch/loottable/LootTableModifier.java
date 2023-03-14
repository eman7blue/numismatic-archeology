package io.github.eman7blue.numis_arch.loottable;

import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.OneTwentyLootTables;
import net.minecraft.loot.entry.ItemEntry;

public class LootTableModifier {

    private static void addCoinToExistingLootTable(Item coin, LootTable.Builder tableBuilder) {
        tableBuilder.modifyPools((poolBuilder) -> poolBuilder.with(ItemEntry.builder((coin))));
    }

    public static void init() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (OneTwentyLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(id))
                addCoinToExistingLootTable(NumisArchItems.TURTLE_COIN, tableBuilder);
            if (OneTwentyLootTables.DESERT_WELL_ARCHAEOLOGY.equals(id))
                addCoinToExistingLootTable(NumisArchItems.SNIFFER_COIN, tableBuilder);
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id))
                addCoinToExistingLootTable(NumisArchItems.ENDER_COIN, tableBuilder);
            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id))
                addCoinToExistingLootTable(NumisArchItems.PARROT_COIN, tableBuilder);
        }));
    }
}
