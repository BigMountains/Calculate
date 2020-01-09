package com.czl.calculate.common.enums;

import lombok.Getter;

public enum Gender {

    MAN("MAN"),
    WOMAN("WOMAN");

    @Getter
    private String name;

    Gender(String name){
        this.name = name;
    }
}
