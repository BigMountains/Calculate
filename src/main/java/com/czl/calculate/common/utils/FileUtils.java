package com.czl.calculate.common.utils;

import com.google.common.io.Files;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FileUtils {

    /**
     * 从Resource路径下读取文件
     * @return
     */
    public static String readFileStringFromResource(String filePath){
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            File file =classPathResource.getFile();
            List<String> strings = Files.readLines(file, Charset.defaultCharset());
            StringBuilder sb = new StringBuilder();
            strings.forEach(sb::append);
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
