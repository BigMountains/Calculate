package com.czl.calculate.model.rule;

import lombok.Getter;

import java.util.Collections;


public abstract class AbstractRule implements Comparable<AbstractRule> {

    /**
     * 权重(当前项目所占的权重)
     */
    @Getter
    private double weight;
    @Getter
    private double score;
    @Getter
    private String level;
    @Getter
    private String value;

    AbstractRule(double weight,double score,String level,String value){
        this.weight = weight;
        this.score = score;
        this.level = level;
        this.value = value;
    }

    /**
     * 对规则进行排序  方便计算  （BMI也参与排序  但无意义）
     * @param o
     * @return
     */
    @Override
    public int compareTo(AbstractRule o) {
        //根据value做升序排序  若不能转为Double则不做排序
        try {
            Double thisValue = Double.parseDouble(this.value);
            Double thatValue = Double.parseDouble(o.getValue());
            return thisValue.compareTo(thatValue);
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
