package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;

public class OneMinuteSitUpScore extends AbstractScore
{
    public OneMinuteSitUpScore(Gender gender, Grade grade, double score) {
        super(gender, grade, score);
    }
}
