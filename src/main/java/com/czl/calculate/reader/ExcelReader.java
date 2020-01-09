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
                //解析range
                double rangeMin = 0;
                double rangeMax = 0;
                if (range.contains("≤")) {
                    rangeMin = -1;
                    rangeMax = Double.valueOf(range.split("≤")[1]);
                } else if (range.contains("≥")) {
                    rangeMin = Double.valueOf(range.split("≥")[1]);
                    rangeMax = -1;
                } else if (range.contains("~")) {
                    rangeMin = Double.valueOf(range.split("~")[0]);
                    rangeMax = Double.valueOf(range.split("~")[1]);
                }
                obj.put("rangeMin", rangeMin);
                obj.put("rangeMax", rangeMax);
                //获取权重
                obj.put("weight",getWeight(wb,obj.getString("grade"),Project.BMI));
                ja.add(obj);
            }
        }
        return ja;
    }

    /**
     * 读取肺活量
     * @param wb
     * @return
     */
    private static JSONArray readVitalCapacity(Workbook wb){
        JSONArray manVitalCapacity = readVitalCapacity(wb,3);
        JSONArray womanVitalCapacity = readVitalCapacity(wb,4);
        manVitalCapacity.addAll(womanVitalCapacity);
        return manVitalCapacity;
    }
    private static JSONArray readVitalCapacity(Workbook wb,int sheetAt){
        JSONArray ja = new JSONArray();
        Sheet sheet = wb.getSheetAt(sheetAt);
        Row head = sheet.getRow(1);
        for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
            Row row = sheet.getRow(i);
            for(int j=2;j<row.getPhysicalNumberOfCells();j++){
                JSONObject obj = new JSONObject();
                obj.put("gender",sheetAt==3?MAN:WOMAN);
                obj.put("project",Project.VITAL_CAPACITY.name());
                obj.put("grade",head.getCell(j).getStringCellValue());
                obj.put("level",row.getCell(0).getStringCellValue());
                obj.put("score",row.getCell(1).getNumericCellValue());
                //获取权重
                obj.put("weight",getWeight(wb,obj.getString("grade"),Project.VITAL_CAPACITY));
                ja.add(obj);
            }
        }
        return ja;
    }






//    public JSONArray template(Workbook wb,int sheetAt){
//        JSONArray ja = new JSONArray();
//        Sheet sheet = wb.getSheetAt(sheetAt);
//        Row head = sheet.getRow(1);
//        for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
//            Row row = sheet.getRow(i);
//            for(int j=2;j<row.getPhysicalNumberOfCells();j++){
//                JSONObject obj = new JSONObject();
//                obj.put("gender",sheetAt==3?MAN:WOMAN);
//                obj.put("project",Project.VITAL_CAPACITY.getName());
//                obj.put("grade",head.getCell(j).getStringCellValue());
//                obj.put("level",row.getCell(0).getStringCellValue());
//                obj.put("score",row.getCell(1).getNumericCellValue());
//                //获取权重
//                obj.put("weight",getWeight(wb,obj.getString("grade"),Project.VITAL_CAPACITY));
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
