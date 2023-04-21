package io.github.eman7blue.numis_arch.loottable;

import net.minecraft.util.Identifier;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

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
    public static final Identifier VILLAGE_ARCHEOLOGIST;
    public static final Identifier VILLAGE_DIG_SITE_ARCHEOLOGY;
    public static final Identifier VILLAGE_ZOMBIE_DIG_SITE_ARCHEOLOGY;

    static {
        BAMBOO_SHRINE_ARCHEOLOGY = id("archeology/bamboo_shrine");
        BAMBOO_SHRINE_CHERRY_CHEST = id("chests/bamboo_shrine_cherry");
        BAMBOO_SHRINE_CHEST = id("chests/bamboo_shrine");
        BASTION_GARDEN_ARCHEOLOGY = id("archeology/bastion_garden");
        BASTION_HOUSING_ARCHEOLOGY = id("archeology/bastion_housing");
        BASTION_STABLE_ARCHEOLOGY = id("archeology/bastion_stable");
        BURIED_HOARD_BADLANDS_ARCHEOLOGY = id("archeology/buried_hoard_badlands");
        BURIED_HOARD_DESERT_ARCHEOLOGY = id("archeology/buried_hoard_desert");
        BURIED_HOARD_LOOT = id("chests/buried_hoard");
        END_TEMPLE_ARCHEOLOGY = id("archeology/end_temple");
        VILLAGE_ARCHEOLOGIST = id("chests/village/village_archeologist");
        VILLAGE_DIG_SITE_ARCHEOLOGY = id("archeology/village/village_dig_site");
        VILLAGE_ZOMBIE_DIG_SITE_ARCHEOLOGY = id("archeology/village/zombie_dig_site");
    }

}
