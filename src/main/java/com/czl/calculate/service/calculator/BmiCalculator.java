package com.czl.calculate.service.calculator;

import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Bmi 计算器
 */
@Component
public class BmiCalculator implements Calculator {

    @Autowired
    private RuleService ruleService;

    @Override
    public double calculate(AbstractScore s) {
        //获取规则
        Set<AbstractRule> rules = ruleService.getRules(s);
        BmiScore score = (BmiScore) s;
        //计算得BMI
        double bmi = score.getBodyWeight() / Math.pow(score.getBodyHeight(),2);
        //计算分数
        double calculate = 0;
        for(AbstractRule r:rules){
            BmiRule rule = (BmiRule)r;
            //取得分数
            if(bmi > rule.getRangeMin() && bmi <= rule.getRangeMax()){
                calculate = rule.getScore();
                //计算权重
                return calculate * rule.getWeight();
            }
        }
        return 0;
    }
}
