package io.github.eman7blue.numis_arch.loottable;

import net.minecraft.util.Identifier;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.MOD_ID;

public class NumisArchLootTables {
    public static final Identifier BAMBOO_SHRINE_ARCHEOLOGY;
    public static final Identifier BAMBOO_SHRINE_CHERRY_CHEST;
    public static final Identifier BAMBOO_SHRINE_CHEST;
    public static final Identifier BASTION_GARDEN_ARCHEOLOGY;
    public static final Identifier BASTION_HOUSING_ARCHEOLOGY;
    public static final Identifier BASTION_STABLE_ARCHEOLOGY;
    public static final Identifier BURIED_HOARD_BADLANDS_ARCHEOLOGY;
    public static final Identifier BURIED_HOARD_DESERT_ARCHEOLOGY;
    public static final Identifier BURIED_HOARD_LOOT;
    public static final Identifier END_TEMPLE_ARCHEOLOGY;

    static {
        BAMBOO_SHRINE_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/bamboo_shrine");
        BAMBOO_SHRINE_CHERRY_CHEST = new Identifier(MOD_ID, "chests/bamboo_shrine_cherry");
        BAMBOO_SHRINE_CHEST = new Identifier(MOD_ID, "chests/bamboo_shrine");
        BASTION_GARDEN_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/bastion_garden");
        BASTION_HOUSING_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/bastion_housing");
        BASTION_STABLE_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/bastion_stable");
        BURIED_HOARD_BADLANDS_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/buried_hoard_badlands");
        BURIED_HOARD_DESERT_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/buried_hoard_desert");
        BURIED_HOARD_LOOT = new Identifier(MOD_ID, "chests/buried_hoard");
        END_TEMPLE_ARCHEOLOGY = new Identifier(MOD_ID, "archeology/end_temple");
    }

}
