package com.czl.calculate.service.calculator;

import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VitalCapacityCalculator extends AbstractSortCalculator {

    @Autowired
    public VitalCapacityCalculator(RuleService ruleService) {
        super(ruleService);
    }

    /**
     * 使用父类默认的排序大小比较
     * @param score
     * @return
     */
    @Override
    public double calculate(AbstractScore score) {
        return super.calculateBySortRules(score);
    }
}
