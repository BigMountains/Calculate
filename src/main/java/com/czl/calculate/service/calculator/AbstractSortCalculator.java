package com.czl.calculate.service.calculator;

import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.RuleService;

import java.util.List;

public abstract class AbstractSortCalculator implements Calculator {

    private RuleService ruleService;

    AbstractSortCalculator(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    final double calculateBySortRules(AbstractScore score) {
        List<AbstractRule> rules = ruleService.getRules(score);
        AbstractRule finalScore = null;
        for (AbstractRule rule : rules) {
            if (score.getScore() < rule.getScore()) {
                finalScore = rule;
                break;
            }
        }
        //若都不匹配 则说明是超过最大值  直接使用最大值
        if(finalScore == null){
            finalScore = rules.get(rules.size()-1);
        }
        //计算单项最终成绩（含权重）
        return finalScore.getScore() * finalScore.getWeight();
    }
}
