package io.github.eman7blue.numis_arch.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import io.github.eman7blue.numis_arch.mixin.RuleStructureProcessorAccessor;
import io.github.eman7blue.numis_arch.mixin.StructurePoolAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTruePosRuleTest;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.blockentity.AppendLootRuleBlockEntityModifier;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class NumisArchStructure {
    public static void init() {
        DynamicRegistrySetupCallback.EVENT.register(registryManager -> registryManager.registerEntryAdded(RegistryKeys.TEMPLATE_POOL, (rawId, id, object) -> {
            RegistryEntry<StructureProcessorList> bastion_processor = RegistryEntry.of(new StructureProcessorList(ImmutableList.of(BlackstoneReplacementStructureProcessor.INSTANCE)));
            if (id.equals(new Identifier("minecraft", "bastion/units/stages/stage_1"))) {
                ObjectArrayList<StructurePoolElement> elements = ((StructurePoolAccessor) object).getElements();
                elements.add(StructurePoolElement.ofProcessedSingle("numis_arch:bastion/units/stages/stage_1_na", bastion_processor).apply(StructurePool.Projection.RIGID));
            }
            if (id.equals(new Identifier("minecraft", "village/desert/houses"))) {
                ObjectArrayList<StructurePoolElement> elements = ((StructurePoolAccessor) object).getElements();
                elements.add(StructurePoolElement.ofSingle("numis_arch:village/desert/houses/desert_archeologist_1").apply(StructurePool.Projection.RIGID));
            }
        }));
        DynamicRegistrySetupCallback.EVENT.register(registryManager -> registryManager.registerEntryAdded(RegistryKeys.PROCESSOR_LIST, (rawId, id, object) -> {
            if (id.equals(new Identifier("minecraft", "housing"))) {
                StructureProcessorRule rule = new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.15f),
                        AlwaysTrueRuleTest.INSTANCE, AlwaysTruePosRuleTest.INSTANCE, NumisArchBlocks.SUSPICIOUS_SOUL_SAND.getDefaultState(), new AppendLootRuleBlockEntityModifier(NumisArchLootTables.BASTION_HOUSING_ARCHEOLOGY));
                addToProcessorList(object, rule);
            }
            if (id.equals(new Identifier("minecraft", "stable_degradation"))) {
                StructureProcessorRule rule = new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.05f),
                        AlwaysTrueRuleTest.INSTANCE, AlwaysTruePosRuleTest.INSTANCE, NumisArchBlocks.SUSPICIOUS_SOUL_SAND.getDefaultState(), new AppendLootRuleBlockEntityModifier(NumisArchLootTables.BASTION_STABLE_ARCHEOLOGY));
                addToProcessorList(object, rule);
            }
        }));
    }

    private static void addToProcessorList(StructureProcessorList processorList, StructureProcessorRule rule) {
        ImmutableList<StructureProcessor> processors = (ImmutableList<StructureProcessor>) processorList.getList();
        RuleStructureProcessor ruleStructureProcessor = (RuleStructureProcessor) processors.get(0);
        ImmutableList<StructureProcessorRule> rules = ((RuleStructureProcessorAccessor) ruleStructureProcessor).getRules();
        ImmutableList<StructureProcessorRule> newRules = ImmutableList.<StructureProcessorRule>builder().addAll(rules).add(rule).build();
        ((RuleStructureProcessorAccessor) ruleStructureProcessor).setRules(newRules);
    }
}
