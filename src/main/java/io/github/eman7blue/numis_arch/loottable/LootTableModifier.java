package io.github.eman7blue.numis_arch.loottable;

import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;

public class LootTableModifier {

    private static void addCoinToExistingLootTable(Item coin, LootTable.Builder tableBuilder, int weight) {
        tableBuilder.modifyPools((poolBuilder) -> poolBuilder.with(ItemEntry.builder(coin).weight(weight)));
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (LootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(id))
                addCoinToExistingLootTable(NumisArchItems.TURTLE_COIN, tableBuilder, 1);
            if (LootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(id))
                addCoinToExistingLootTable(NumisArchItems.SNIFFER_COIN, tableBuilder, 2);
        }));
    }
}
