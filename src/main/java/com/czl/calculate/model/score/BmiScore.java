package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;
import lombok.Getter;
import lombok.Setter;

public class BmiScore extends AbstractScore {

    @Getter
    @Setter
    private double bodyWeight;
    @Getter
    @Setter
    private double bodyHeight;


    public BmiScore(Gender gender, Grade grade, double bodyWeight, double bodyHeight) {
        super(gender, grade, 0);
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
    }


}
