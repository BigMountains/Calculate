package com.czl.calculate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.Grade;
import com.czl.calculate.common.utils.FileUtils;
import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.BmiScore;
import com.czl.calculate.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.basic.BasicMenuItemUI;
import java.util.Set;

@SpringBootTest
class CalculateApplicationTests {

    @Autowired
    private RuleService ruleService;

    @Test
    void test() {
        BmiScore bmiScore = new BmiScore(
                Gender.MAN, Grade.PRIMARY_SCHOOL_FIRST_GRADE,200,100
        );

        Set<AbstractRule> rules = ruleService.getRules(bmiScore);
        System.out.println(1);
    }

}
