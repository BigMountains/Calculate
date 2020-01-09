package com.czl.calculate.service.calculator;

import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneMinuteSitUpCalculator extends AbstractSortCalculator {

    @Autowired
    public OneMinuteSitUpCalculator(RuleService ruleService) {
        super(ruleService);
    }

    @Override
    public double calculate(AbstractScore score) {
        return super.calculateBySortRules(score);
    }
}
