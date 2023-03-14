package io.github.eman7blue.numis_arch.loottable;

import net.minecraft.util.Identifier;

public class NumisArchLootTables {
    public static final Identifier BURIED_HOARD_LOOT;
    public static final Identifier BURIED_HOARD_DESERT_ARCHEOLOGY;
    public static final Identifier BURIED_HOARD_BADLANDS_ARCHEOLOGY;
    public static final Identifier BASTION_STABLE_ARCHEOLOGY;
    public static final Identifier BASTION_HOUSING_ARCHEOLOGY;
    public static final Identifier BASTION_GARDEN_ARCHEOLOGY;

    static {
        BURIED_HOARD_LOOT = new Identifier("numis_arch", "chests/buried_hoard");
        BURIED_HOARD_DESERT_ARCHEOLOGY = new Identifier("numis_arch", "archeology/buried_hoard_desert");
        BURIED_HOARD_BADLANDS_ARCHEOLOGY = new Identifier("numis_arch", "archeology/buried_hoard_badlands");
        BASTION_STABLE_ARCHEOLOGY = new Identifier("numis_arch", "archeology/bastion_stable");
        BASTION_HOUSING_ARCHEOLOGY = new Identifier("numis_arch", "archeology/bastion_housing");
        BASTION_GARDEN_ARCHEOLOGY = new Identifier("numis_arch", "archeology/bastion_garden");

    }

}
