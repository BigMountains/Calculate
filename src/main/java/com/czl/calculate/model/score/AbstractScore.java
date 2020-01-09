package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;
import lombok.Getter;

public abstract class AbstractScore {

    @Getter
    private Gender gender;
    @Getter
    private Grade grade;
    @Getter
    private double score;

    AbstractScore(Gender gender, Grade grade,double score){
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }


}
