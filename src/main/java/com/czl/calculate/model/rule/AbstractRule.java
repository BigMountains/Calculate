package com.czl.calculate.model.rule;

public abstract class AbstractRule {

    /**
     * 权重
     */
    private double weight;


    AbstractRule(double weight){
        this.weight = weight;
    }

    public double getWeight(){
        return this.weight;
    }
}
