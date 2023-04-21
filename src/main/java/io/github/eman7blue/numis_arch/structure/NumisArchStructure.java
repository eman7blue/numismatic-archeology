package io.github.eman7blue.numis_arch.structure;

import com.mojang.datafixers.util.Pair;
import io.github.eman7blue.numis_arch.mixin.StructurePoolAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Optional;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class NumisArchStructure {

    private static final Identifier EMPTY = new Identifier("empty");

    public static void addToStructurePools(MinecraftServer server) {
        addStructurePoolElement(server, new Identifier("minecraft:bastion/units/stages/stage_1"), id("bastion/units/stages/stage_1_na"), new Identifier("minecraft:bastion_generic_degradation"));

        addStructurePoolElement(server, new Identifier("minecraft:village/desert/houses"), id("village/desert/houses/desert_archeologist_1"), id("village_dig_site_desert"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/plains/houses"), id("village/plains/houses/plains_archeologist_1"), id("village_dig_site_plains"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/savanna/houses"), id("village/savanna/houses/savanna_archeologist_1"), id("village_dig_site_savanna"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/snowy/houses"), id("village/snowy/houses/snowy_archeologist_1"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/taiga/houses"), id("village/taiga/houses/taiga_archeologist_1"), new Identifier("minecraft:mossify_10_percent"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/taiga/houses"), id("village/taiga/houses/taiga_archeologist_2"), id("village_dig_site_taiga"));

        addStructurePoolElement(server, new Identifier("minecraft:village/desert/zombie/houses"), id("village/desert/houses/desert_archeologist_1"), id("zombie_dig_site_desert"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/plains/zombie/houses"), id("village/plains/houses/plains_archeologist_1"), id("zombie_dig_site_plains"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/savanna/zombie/houses"), id("village/savanna/houses/savanna_archeologist_1"), id("zombie_dig_site_savanna"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/snowy/zombie/houses"), id("village/snowy/houses/snowy_archeologist_1"), new Identifier("minecraft:zombie_snowy"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/taiga/zombie/houses"), id("village/taiga/houses/taiga_archeologist_1"), new Identifier("minecraft:zombie_taiga"), 2);
        addStructurePoolElement(server, new Identifier("minecraft:village/taiga/zombie/houses"), id("village/taiga/houses/taiga_archeologist_2"), id("zombie_dig_site_taiga"));
    }

    protected static void addStructurePoolElement(MinecraftServer server, Identifier structurePoolId, Identifier structureElementId, Identifier structureProcessorId, int weight) {
        Optional<StructurePool> structurePoolOptional = server.getRegistryManager().get(RegistryKeys.TEMPLATE_POOL).getOrEmpty(structurePoolId);
        if (structurePoolOptional.isEmpty()){
            return;
        }

        StructurePool structurePool = structurePoolOptional.get();
        StructurePoolElement element;
        ObjectArrayList<StructurePoolElement> elementsList = ((StructurePoolAccessor) structurePool).getElements();
        if (structureProcessorId.equals(EMPTY)) {
            element = StructurePoolElement.ofSingle(structureElementId.toString()).apply(StructurePool.Projection.RIGID);
        } else {
            RegistryEntry<StructureProcessorList> structureProcessorList = server.getRegistryManager().get(RegistryKeys.PROCESSOR_LIST).getEntry(RegistryKey.of(RegistryKeys.PROCESSOR_LIST, structureProcessorId)).get();
            element = StructurePoolElement.ofProcessedSingle(structureElementId.toString(), structureProcessorList).apply(StructurePool.Projection.RIGID);
        }

        ArrayList<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor) structurePool).getElementCounts());
        elementCounts.add(Pair.of(element, weight));
        ((StructurePoolAccessor) structurePool).setElementCounts(elementCounts);
        for (int i = 0; i < weight; ++i)
            elementsList.add(element);
    }

    protected static void addStructurePoolElement(MinecraftServer server, Identifier structureElementId, Identifier structurePoolId, Identifier structureProcessorId) {
        addStructurePoolElement(server, structureElementId, structurePoolId, structureProcessorId, 1);
    }

    protected static void addStructurePoolElement(MinecraftServer server, Identifier structureElementId, Identifier structurePoolId, int weight) {
        addStructurePoolElement(server, structureElementId, structurePoolId, EMPTY, weight);
    }

    protected static void addStructurePoolElement(MinecraftServer server, Identifier structureElementId, Identifier structurePoolId) {
        addStructurePoolElement(server, structureElementId, structurePoolId, EMPTY, 1);
    }
}
