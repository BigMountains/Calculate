package com.czl.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czl.calculate.common.enums.Gender;
import com.czl.calculate.common.enums.ProjectPacket;
import com.czl.calculate.common.utils.FileUtils;
import com.czl.calculate.model.rule.AbstractRule;
import com.czl.calculate.model.rule.BmiRule;
import com.czl.calculate.model.score.AbstractScore;
import org.apache.commons.math3.analysis.function.Abs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RuleService implements InitializingBean {
    /**
     * 该集合存储所有的规则
     **/
    private Map<Object, Map<Object, Map<Object, List<Object>>>> ruleContainer;

    private static final String FILE_PATH = "static/rules.json";

    /**
     * 初始化后读取一次规则配置
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        readRules();
    }

    /**
     * 读取配置文件
     *
     * @throws IOException
     */
    private void readRules() throws IOException {
        String ruleText = FileUtils.readFileStringFromResource(FILE_PATH);
        JSONArray rules = JSON.parseArray(ruleText);
        assert rules != null;
        ruleContainer = rules.stream().collect(
                //首先对性别分组
                Collectors.groupingBy(rule -> ((JSONObject) rule).getString("gender"),
                        //然后对年级分组
                        Collectors.groupingBy(rule -> ((JSONObject) rule).getString("grade"),
                                //然后对项目分组
                                Collectors.groupingBy(rule -> ((JSONObject) rule).getString("project"))
                        )
                )
        );
    }

    /**
     * 获取对应的规则
     *
     * @param score
     * @return
     */
    public List<AbstractRule> getRules(AbstractScore score) {
        String gender = score.getGender().getName();
        String grade = score.getGrade().getName();
        String project = ProjectPacket.getProject(score.getClass()).name();
        //获取规则列表
        List<Object> rules = ruleContainer.get(gender).get(grade).get(project);
        //将规则列表进行转换
        List<AbstractRule> formatRules = new ArrayList<>();
        rules.forEach(rule -> {
            formatRules.add(JSON.parseObject(
                    ((JSONObject) rule).toJSONString(),
                    ProjectPacket.getRuleClass(score.getClass()))
            );
        });
        if (formatRules.isEmpty()) {
            throw new NullPointerException("规则集合中找不到对应的规则");
        }
        //将规则进行排序  方便计算
        Collections.sort(formatRules);
        return formatRules;
    }



}
