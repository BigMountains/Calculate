package com.czl.calculate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;
import com.czl.calculate.common.utils.FileUtils;
import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.model.score.VitalCapacityScore;
import com.czl.calculate.service.CalculateService;
import com.czl.calculate.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.basic.BasicMenuItemUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootTest
class CalculateApplicationTests {

    @Autowired
    private RuleService ruleService;
    @Autowired
    private CalculateService calculateService;
    @Test
    void test() {
        BmiScore bmiScore = new BmiScore(
                Gender.MAN, Grade.PRIMARY_SCHOOL_FIRST_GRADE,200,100
        );

        VitalCapacityScore vitalCapacityScore = new VitalCapacityScore(
                Gender.MAN,Grade.PRIMARY_SCHOOL_FIRST_GRADE,3000
        );
        List<AbstractScore> scores = Arrays.asList(bmiScore,vitalCapacityScore);

        double result = calculateService.calculateScore(scores);
        System.out.println(result);
    }

}
