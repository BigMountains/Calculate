package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;

public class VitalCapacityScore extends AbstractScore {

    public VitalCapacityScore(Gender gender, Grade grade, double score) {
        super(gender, grade, score);
    }
}
