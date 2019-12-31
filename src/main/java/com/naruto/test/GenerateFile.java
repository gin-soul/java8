package com.naruto.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author gin
 * @date 2019/12/27 10:54
 */
public class GenerateFile {

    public static void main(String[] args) {
        //8个字段
        //S_CI_VB_CUSID0x03S_CI_VB_INCOME0x03S_CI_VB_AGE0x03S_CI_VB_UUID
        Random random = new Random();
        long cusId = 360702199210311611L;
        String uuid = "bigdata00100433699f0a2c4909e8648";
        StringBuilder sb = new StringBuilder(200000);
        for (int i = 1; i <= 4000000; i++) {
            //身份证
            //收入
            //年龄
            //填充数据
            sb.append(cusId + i).append("0x03")
                    .append(random.nextDouble() * 100000).append("0x03")
                    .append(random.nextInt(20) + 10).append("0x03")
                    .append(uuid).append("\r\n");
            if (i % 10000 == 0) {
                appendMethodB("d:/var/big.txt", sb.toString());
                sb = new StringBuilder();
            }
        }

    }

    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
