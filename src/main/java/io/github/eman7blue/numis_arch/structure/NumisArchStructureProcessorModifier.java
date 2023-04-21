package io.github.eman7blue.numis_arch.structure;

import com.google.common.collect.ImmutableList;
import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import io.github.eman7blue.numis_arch.mixin.RuleStructureProcessorAccessor;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTruePosRuleTest;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.blockentity.AppendLootRuleBlockEntityModifier;
import net.minecraft.util.Identifier;

public class NumisArchStructureProcessorModifier {

    public static void modifyProcessors() {
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
