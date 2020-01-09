package com.czl.calculate.common.enums;

import lombok.Getter;

public enum Project {
    BMI("BMI"),
    VITAL_CAPACITY("VITAL_CAPACITY"),
    FIFTY_METER_RUN("FIFTY_METER_RUN"),
    SEATED_FORWARD_FLEXION("SEATED_FORWARD_FLEXION"),
    ONE_MINUTE_SKIPPING("ONE_MINUTE_SKIPPING"),
    ONE_MINUTE_SIT_UPS("ONE_MINUTE_SIT_UPS"),
    FIFTY_BY_EIGHT_BACK_AND_FORTH("FIFTY_BY_EIGHT_BACK_AND_FORTH"),
    PULL_UP("PULL_UP"),
    STANDING_LONG_JUMP("STANDING_LONG_JUMP"),
    ENDURANCE_RUN("ENDURANCE_RUN");


    private String name;

    Project(String name){
        this.name = name;
    }

}
