package com.czl.calculate.service.calculator;

import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<AbstractRule> rules = ruleService.getRules(s);
        BmiScore score = (BmiScore) s;
        //计算得BMI
        double bmi = score.getBodyWeight() / Math.pow(score.getBodyHeight(),2);
        //计算分数
        double calculate = 0;
        for(AbstractRule rule:rules){
            double[] range = getRangeMinAndMax(rule.getValue());
            //取得分数
            if(bmi > range[0] && bmi <= range[1]){
                calculate = rule.getScore();
                //计算权重
                return calculate * rule.getWeight();
            }
        }
        return calculate;
    }

    /** 获取大小范围 **/
    private double[] getRangeMinAndMax(String rangeStr){
        //解析range
                double rangeMin = 0;
                double rangeMax = 0;
                if (rangeStr.contains("≤")) {
                    rangeMin = -1;
                    rangeMax = Double.parseDouble(rangeStr.split("≤")[1]);
                } else if (rangeStr.contains("≥")) {
                    rangeMin = Double.parseDouble(rangeStr.split("≥")[1]);
                    rangeMax = -1;
                } else if (rangeStr.contains("~")) {
                    rangeMin = Double.parseDouble(rangeStr.split("~")[0]);
                    rangeMax = Double.parseDouble(rangeStr.split("~")[1]);
                }
                return new double[]{rangeMin,rangeMax};
    }




}
