package com.czl.calculate.controller;


import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping
public class BaseController {

    @Autowired
    private CalculateService calculateService;

    @GetMapping("getScore")
    public String getScore() {
        BmiScore bmiScore = new BmiScore(Gender.MAN, Grade.PRIMARY_SCHOOL_FIRST_GRADE,
                75, 1.71
        );
        return String.valueOf(calculateService.calculateScore(Arrays.asList(bmiScore)));
    }


}
