package io.github.eman7blue.numis_arch.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;

public class NumisArchBlocks {
    public static final CoinCollectorTrophyBlock COIN_COLLECTOR_TROPHY;

    public static void init() {

        Registry.register(Registries.BLOCK, new Identifier("numis_arch", "coin_collector_trophy"), COIN_COLLECTOR_TROPHY);
        Registry.register(Registries.ITEM, new Identifier("numis_arch", "coin_collector_trophy"),
                new BlockItem(COIN_COLLECTOR_TROPHY, new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));
    }

    static {
        COIN_COLLECTOR_TROPHY = new CoinCollectorTrophyBlock(FabricBlockSettings.of(Material.METAL).hardness(2).resistance(6).requiresTool().requires(FeatureFlags.UPDATE_1_20));
    }
}
