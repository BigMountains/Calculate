package com.czl.calculate.reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.czl.calculate.common.enums.Project;
import com.google.common.io.Files;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelReader {
    private static HashMap<String, HashMap<String, Double>> gradeProjectWeight = new HashMap<>();
    /**
     * 性别
     **/
    private final static String MAN = "MAN";
    private final static String WOMAN = "WOMAN";


    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Desktop\\体质标准.xls");
        Workbook wb = new XSSFWorkbook(new FileInputStream(file));
        JSONArray result = new JSONArray();
        //读取Bmi
        result.addAll(readBmi(wb));
        //读取肺活量
        result.addAll(readVitalCapacity(wb));
        //读取50米跑
        result.addAll(readFiftyMeterRun(wb));
        //读取坐位体前屈
        result.addAll(readSeatedForwardFlexion(wb));
        //读取跳绳
        result.addAll(readSkipping(wb));
        //读取跳远
        result.addAll(readJump(wb));

        String pretty = JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        File output = new File("D:\\Desktop\\rules.json");
        Files.write(pretty.getBytes(), output);
    }


    /**
     * 读取BMI
     **/
    private static JSONArray readBmi(Workbook wb) {
        JSONArray manBMI = readBmi(wb, 1);
        JSONArray womanBMI = readBmi(wb, 2);
        manBMI.addAll(womanBMI);
        return manBMI;
    }

    private static JSONArray readBmi(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        int rowNum = sheet.getPhysicalNumberOfRows();
        Row headRow = sheet.getRow(1);
        for (int i = 2; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 1 ? MAN : WOMAN);
                obj.put("project", Project.BMI.name());
                obj.put("grade", headRow.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                String range = row.getCell(j).getStringCellValue();
                obj.put("value",range);
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.BMI));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 读取肺活量
     *
     * @param wb
     * @return
     */
    private static JSONArray readVitalCapacity(Workbook wb) {
        JSONArray manVitalCapacity = readVitalCapacity(wb, 3);
        JSONArray womanVitalCapacity = readVitalCapacity(wb, 4);
        manVitalCapacity.addAll(womanVitalCapacity);
        return manVitalCapacity;
    }

    private static JSONArray readVitalCapacity(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 3 ? MAN : WOMAN);
                obj.put("project", Project.VITAL_CAPACITY.name());
                obj.put("grade", head.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                obj.put("value", row.getCell(j).getNumericCellValue());
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.VITAL_CAPACITY));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 读取50米跑规则
     *
     * @param wb
     * @return
     */
    private static JSONArray readFiftyMeterRun(Workbook wb) {
        JSONArray manFiftyRun = readFiftyMeterRun(wb, 5);
        JSONArray womanFiftyRun = readFiftyMeterRun(wb, 6);
        manFiftyRun.addAll(womanFiftyRun);
        return manFiftyRun;
    }

    private static JSONArray readFiftyMeterRun(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 5 ? MAN : WOMAN);
                obj.put("project", Project.FIFTY_METER_RUN.name());
                obj.put("grade", head.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                obj.put("value", row.getCell(j).getNumericCellValue());
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.FIFTY_METER_RUN));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 读取坐位体前屈规则
     *
     * @param wb
     * @return
     */
    private static JSONArray readSeatedForwardFlexion(Workbook wb) {
        JSONArray manSeatForwardFlexion = readSeatedForwardFlexion(wb, 7);
        JSONArray womanSeatForwardFlexion = readSeatedForwardFlexion(wb, 8);
        manSeatForwardFlexion.addAll(womanSeatForwardFlexion);
        return manSeatForwardFlexion;
    }

    private static JSONArray readSeatedForwardFlexion(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 7 ? MAN : WOMAN);
                obj.put("project", Project.SEATED_FORWARD_FLEXION.name());
                obj.put("grade", head.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                obj.put("value", row.getCell(j).getNumericCellValue());
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.SEATED_FORWARD_FLEXION));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 读取跳绳规则
     *
     * @param wb
     * @return
     */
    private static JSONArray readSkipping(Workbook wb) {
        JSONArray manSkipping = readSkipping(wb, 9);
        JSONArray womanSkipping = readSkipping(wb, 10);
        manSkipping.addAll(womanSkipping);
        return manSkipping;
    }

    private static JSONArray readSkipping(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 9 ? MAN : WOMAN);
                obj.put("project", Project.ONE_MINUTE_SKIPPING.name());
                obj.put("grade", head.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                obj.put("value", row.getCell(j).getNumericCellValue());
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.ONE_MINUTE_SKIPPING));
                ja.add(obj);
            }
        }
        return ja;
    }


    /**
     * 读取立定跳远规则
     *
     * @param wb
     * @return
     */
    private static JSONArray readJump(Workbook wb) {
        JSONArray manJump = readJump(wb, 11);
        JSONArray womanJump = readJump(wb, 12);
        manJump.addAll(womanJump);
        return manJump;
    }

    private static JSONArray readJump(Workbook wb, int sheetAt) {
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
                JSONObject obj = new JSONObject();
                obj.put("gender", sheetAt == 11 ? MAN : WOMAN);
                obj.put("project", Project.STANDING_LONG_JUMP.name());
                obj.put("grade", head.getCell(j).getStringCellValue());
                obj.put("level", row.getCell(0).getStringCellValue());
                obj.put("score", row.getCell(1).getNumericCellValue());
                obj.put("value", row.getCell(j).getNumericCellValue());
                //获取权重
                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.STANDING_LONG_JUMP));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 引体向上
     *
     * @return
     * @Author LeoChen
     * @Date 2020/1/9
     * @Param
     **/
//    private static JSONArray readPullUp(Workbook wb) {
//        JSONArray ja = new JSONArray();
//        Sheet sheet = wb.getSheetAt(13);
//        Row head = sheet.getRow(1);
//        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
//            Row row = sheet.getRow(i);
//            for (int j = 2; j < row.getPhysicalNumberOfCells(); j++) {
//                JSONObject obj = new JSONObject();
//                obj.put("gender", MAN);
//                obj.put("project", Project.FIFTY_METER_RUN.name());
//                obj.put("grade", head.getCell(j).getStringCellValue());
//                obj.put("level", row.getCell(0).getStringCellValue());
//                obj.put("score", row.getCell(1).getNumericCellValue());
//                //获取权重
//                obj.put("weight", getWeight(wb, obj.getString("grade"), Project.STANDING_LONG_JUMP));
//                //TODO 若规则不存在 则不用录入
//
//
//                ja.add(obj);
//            }
//        }
//        return ja;
//    }


    /**
     * 获取权重
     **/
    private static double getWeight(Workbook wb, String g, Project p) {
        Sheet sheet = wb.getSheetAt(0);

        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            String grade = row.getCell(0).getStringCellValue();
            String project = row.getCell(1).getStringCellValue();
            double weight = row.getCell(3).getNumericCellValue() / 100.0;

            if (gradeProjectWeight.get(grade) == null) {
                gradeProjectWeight.put(grade, new HashMap<>());
            } else {
                gradeProjectWeight.get(grade).put(project, weight);
            }
        }

        HashMap<String, Double> map = gradeProjectWeight.get(g);
        if (map == null) {
            throw new NullPointerException("不存在该年极段的规则");
        }
        switch (p) {
            case BMI:
                return map.get("体重指数(BMI)");
            case VITAL_CAPACITY:
                return map.get("肺活量");
            case FIFTY_METER_RUN:
                return map.get("50米跑");
            case SEATED_FORWARD_FLEXION:
                return map.get("坐位体前屈");
            case ONE_MINUTE_SKIPPING:
                return map.get("1分钟跳绳");
            case ONE_MINUTE_SIT_UPS:
                return map.get("引体向上/1分钟仰卧起坐");
            case PULL_UP:
                return map.get("1分钟仰卧起坐");
            case FIFTY_BY_EIGHT_BACK_AND_FORTH:
                return map.get("50米×8往返跑");
            case STANDING_LONG_JUMP:
                return map.get("立定跳远");
            case ONE_THOUSAND_METER_RUN:
            case EIGHT_HUNDRED_METER_RUN:
                return map.get("1000米跑/800米跑");
            default:
                throw new NullPointerException("不存在的权重");
        }
    }

}
