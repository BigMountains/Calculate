package com.czl.calculate.model.score;

import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;

public abstract class AbstractScore {

    private Gender gender;

    private Grade grade;

    AbstractScore(Gender gender, Grade grade){
        this.gender = gender;
        this.grade = grade;
    }

    public Gender getGender(){
        return this.gender;
    }

    public Grade getGrade(){
        return this.grade;
    }

}
