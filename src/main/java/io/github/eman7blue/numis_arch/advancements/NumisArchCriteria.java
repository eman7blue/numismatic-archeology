package io.github.eman7blue.numis_arch.advancements;

import net.minecraft.advancement.criterion.Criteria;

public class NumisArchCriteria {

    public static final ArcheologyBlockDestroyedCriterion ARCHEOLOGY_BLOCK_DESTROYED;


    public static void registerCriteria() {
    }

    static {
        ARCHEOLOGY_BLOCK_DESTROYED = Criteria.register(ArcheologyBlockDestroyedCriterion.ID.toString() ,new ArcheologyBlockDestroyedCriterion());
    }
}
