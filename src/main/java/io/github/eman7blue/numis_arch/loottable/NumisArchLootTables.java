package io.github.eman7blue.numis_arch.loottable;

import net.minecraft.util.Identifier;

public class NumisArchLootTables {
    public static final Identifier BURIED_HOARD_LOOT;
    public static final Identifier BURIED_HOARD_ARCHEOLOGY;

    static {
        BURIED_HOARD_LOOT = new Identifier("numis_arch", "chests/buried_hoard");
        BURIED_HOARD_ARCHEOLOGY = new Identifier("numis_arch", "archeology/buried_hoard");
    }

}
