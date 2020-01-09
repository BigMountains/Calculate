package com.czl.calculate.common.enums;

import lombok.Getter;

/**
 * 年级枚举
 */
public enum Grade {


    PRIMARY_SCHOOL_FIRST_GRADE("一年级"),
    PRIMARY_SCHOOL_SECOND_GRADE("二年级"),
    PRIMARY_SCHOOL_THIRD_GRADE("三年级"),
    PRIMARY_SCHOOL_FOURTH_GRADE("四年级"),
    PRIMARY_SCHOOL_FIFTH_GRADE("五年级"),
    PRIMARY_SCHOOL_SIXTH_GRADE("六年级"),

    JUNIOR_HIGH_SCHOOL_FIRST_GRADE("初一"),
    JUNIOR_HIGH_SCHOOL_SECOND_GRADE("初二"),
    JUNIOR_HIGH_SCHOOL_THIRD_GRADE("初三"),

    SENIOR_HIGH_SCHOOL_FIRST_GRADE("高一"),
    SENIOR_HIGH_SCHOOL_SECOND_GRADE("高二"),
    SENIOR_HIGH_SCHOOL_THIRD_GRADE("高三"),

    COLLAGE_FIRST_GRADE("大一"),
    COLLAGE_SECOND_GRADE("大二"),
    COLLAGE_THIRD_GRADE("大三"),
    COLLAGE_FOURTH_GRADE("大四");

    @Getter
    private String name;

    Grade(String name){
        this.name = name;
    }
}
