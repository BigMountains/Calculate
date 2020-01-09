package com.czl.calculate.model.rule;

/**
 * BMI规则
 * @author Leo
 */
public class BmiRule extends AbstractRule {
    /** 等级 (正常、低体重、超重、肥胖) **/
    private String level;
    /** 分数 **/
    private int score;
    /** 范围   大 (若该值为-1 则表示无限大) **/
    private double rangeMin;
    /** 范围   小 (若该值为-1 则表示无穷小)**/
    private double rangeMax;

    public BmiRule(String level,int score,double rangeMin,double rangeMax,double weight){
        super(weight);
        this.level = level;
        this.score = score;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(double rangeMax) {
        this.rangeMax = rangeMax;
    }
}
