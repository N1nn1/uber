package com.ninni.uber.registry.secondary;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class UberGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> RULE_MANA_SOURCE_CONVERSION = GameRuleRegistry.register("manaSourceConversion", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));
}
