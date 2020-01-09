package com.czl.calculate.common.enums;

import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.calculator.BmiCalculator;
import com.czl.calculate.service.calculator.Calculator;
import lombok.Getter;
import org.apache.commons.math3.analysis.function.Abs;

/**
 * 项目枚举类（表示规则与分数与计算器的对应关系)
 */
public enum ProjectPacket {

    BMI_PACKET(Project.BMI,BmiScore.class, BmiCalculator.class, BmiRule.class);


    @Getter
    private Project project;
    @Getter
    private Class<? extends AbstractScore> scoreClass;
    @Getter
    private Class<? extends Calculator> calculatorClass;
    @Getter
    private Class<? extends AbstractRule> ruleClass;

    ProjectPacket(Project project,
                  Class<? extends AbstractScore> score,
                  Class<? extends Calculator> calculator,
                  Class<? extends AbstractRule> rule) {
        this.project = project;
        this.scoreClass = score;
        this.calculatorClass = calculator;
        this.ruleClass = rule;
    }


    public static Class<? extends Calculator> getCalculatorClass(Class<? extends AbstractScore> scoreClass) {
        for (ProjectPacket packet : values()) {
            if (packet.getScoreClass().equals(scoreClass)) {
                return packet.getCalculatorClass();
            }
        }
        throw new IllegalArgumentException("Packet错误，找不到对应的计算器");
    }


    public static Class<? extends AbstractRule> getRuleClass(Class<? extends AbstractScore> scoreClass) {
        for (ProjectPacket packet : values()) {
            if (packet.getScoreClass().equals(scoreClass)) {
                return packet.getRuleClass();
            }
        }
        throw new IllegalArgumentException("Packet错误，找不到对应的规则");
    }

    public static Project getProject(Class<? extends AbstractScore> scoreClass){
        for(ProjectPacket packet: values()){
            if(packet.getScoreClass().equals(scoreClass)){
                return packet.getProject();
            }
        }
        throw new IllegalArgumentException("Packet错误，找不到对应的项目");
    }
}
