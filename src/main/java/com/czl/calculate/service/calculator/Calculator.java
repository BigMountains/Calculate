package com.czl.calculate.service.calculator;

import com.czl.calculate.model.score.AbstractScore;

/**
 * 计算器 用于计算各项成绩
 */
public interface Calculator {

    double calculate(AbstractScore score);
}
