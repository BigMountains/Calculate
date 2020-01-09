package com.czl.calculate.service;

import com.czl.calculate.common.enums.ProjectPacket;
import com.czl.calculate.common.utils.SpringUtils;
import com.czl.calculate.model.score.AbstractScore;
import com.czl.calculate.service.calculator.Calculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {

    /**
     * 成绩计算
     * 输入学生一轮体测的所有成绩
     * 返回汇总成绩
     * @return
     */
    public double calculateScore(List<AbstractScore> scores){
        double scoreAfterCalculate = 0;
        //遍历成绩
        for(AbstractScore score:scores){
            //根据成绩类型获取对应的计算器
            Calculator calculator = (
                    SpringUtils.getBean(
                            ProjectPacket.getCalculatorClass(score.getClass())
                    )
            );
            //计算成绩
            scoreAfterCalculate += calculator.calculate(score);
        }
        return scoreAfterCalculate;
    }

}
