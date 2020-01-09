package com.czl.calculate.common.enums;

import com.czl.calculate.model.rule.*;
import com.czl.calculate.model.score.*;
import com.czl.calculate.service.calculator.*;
import lombok.Getter;

/**
 * 项目枚举类（表示规则与分数与计算器的对应关系)
 */
public enum ProjectPacket {

    BMI_PACKET(Project.BMI,BmiScore.class, BmiCalculator.class, BmiRule.class),
    VITAL_CAPACITY_PACKET(Project.VITAL_CAPACITY, VitalCapacityScore.class, VitalCapacityCalculator.class, VitalCapacityRule.class),
    FIFTY_METER_RUN(Project.FIFTY_METER_RUN, FiftyMeterRunScore.class, FiftyMeterRunCalculator.class,FiftyMeterRunRule.class),
    SEATED_FORWARD_FLEXION(Project.SEATED_FORWARD_FLEXION, SeatedForwardFlexionScore.class, SeatedForwardFlexionCalculator.class, SeatedForwardFlexionRule.class),
    ONE_MINUTE_SKIPPING(Project.ONE_MINUTE_SKIPPING,OneMinuteSkippingScore.class,OneMinuteSkippingCalculator.class,OneMinuteSkippingRule.class),
    ONE_MINUTE_SIT_UPS(Project.ONE_MINUTE_SIT_UPS,OneMinuteSitUpScore.class,OneMinuteSitUpCalculator.class,OneMinuteSitUpRule.class),
    FIFTY_BY_EIGHT_BACK_AND_FORTH(Project.FIFTY_BY_EIGHT_BACK_AND_FORTH,FiftyByEightBackAndForthScore.class,FiftyByEightBackAndForthCalculator.class,FiftyByEightBackAndForthRule.class),
    PULL_UP(Project.PULL_UP,PullUpScore.class,PullUpCalculator.class,PullUpRule.class),
    STANDING_LONG_JUMP(Project.STANDING_LONG_JUMP,StandingLongJumpScore.class,StandingLongJumpCalculator.class,StandingLongJumpRule.class),
    ENDURANCE_RUN(Project.ENDURANCE_RUN,EnduranceRunScore.class,EnduranceRunCalculator.class,EnduranceRunRule.class);


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
