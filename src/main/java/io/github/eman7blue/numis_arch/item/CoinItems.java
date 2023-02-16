package io.github.eman7blue.numis_arch.item;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;

public class CoinItems {
    public static final Item TURTLE_COIN =
            Registry.register(Registries.ITEM, new Identifier("numis_arch", "turtle_coin"),
                    new Item(new FabricItemSettings().requires(FeatureFlags.UPDATE_1_20)));

    public static void init() {
        NumismaticArcheology.LOGGER.info("Items initialized");
    }
}
