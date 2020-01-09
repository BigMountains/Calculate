package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;

public class BmiScore extends AbstractScore {

    private double bodyWeight;

    private double bodyHeight;



    public BmiScore(Gender gender, Grade grade,double bodyWeight, double bodyHeight){
        super(gender,grade);
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
    }


    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public double getBodyHeight() {
        return bodyHeight;
    }

    public void setBodyHeight(double bodyHeight) {
        this.bodyHeight = bodyHeight;
    }
}
