package com.naruto.test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author gin
 * @date 2020/1/7 11:24
 */
public class ReadFileTest {

    public static void main(String[] args) {
        List<String> lineToList = readLineToList(new File("D:/var/menu.txt"));
        List<Map<String, Object>> maps = splitData("menu;function;tree", lineToList);
        maps.forEach((e) -> {
            System.out.println(e);
        });
    }

    public static List<String> readLineToList(File file) {
        List<String> dataList = new LinkedList<>();
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                dataList.add(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            System.out.println("file parse String error:" + e);
        } finally {
            try {
                if (null != read) {
                    read.close();
                }
            } catch (IOException e) {
                System.out.println("io close error:" + e);
            }
        }
        return dataList;
    }

    private static final String DATA_SPLIT = ";";

    public static List<Map<String, Object>> splitData(String fieldName, List<String> fileResult) {
        //获取字段数组
        String[] filedArray = fieldName.split(DATA_SPLIT);

        LinkedList<Map<String, Object>> dataMapList = new LinkedList<>();
        for (String data : fileResult) {
            if (StringUtils.isNotBlank(data) && data.contains(DATA_SPLIT)) {
                //将每行客户信息进行分割
                String[] splitList = data.split(DATA_SPLIT);
                HashMap<String, Object> dataMap = new HashMap<>();
                for (int i = 0; i < splitList.length; i++) {
                    if (StringUtils.isNotBlank(splitList[i])) {
                        //与字段数组匹配成map,要求文件中每行数据顺序和字段数组(配置文件中)顺序一致
                        dataMap.put(filedArray[i], splitList[i]);
                    }
                }
                dataMapList.add(dataMap);
            }
        }
        return dataMapList;
    }

}
