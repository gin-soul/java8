package com.naruto.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gin
 * @date 2019/12/17 16:10
 */
public class CompareTest {


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static List<String> readFileByLines(String fileName) {
        List<String> linkedList = new LinkedList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                linkedList.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linkedList;
    }

    public static void main(String[] args) {
        List<String> newFile = readFileByLines("D:\\java\\code\\naruto\\java8\\src\\main\\resources\\" + "new.txt");
        System.out.println(newFile.size());
        List<String> oldFile = readFileByLines("D:\\java\\code\\naruto\\java8\\src\\main\\resources\\" + "old.txt");
        System.out.println(oldFile.size());
        LinkedList<String> diff = new LinkedList<>();
        for (String s : oldFile) {
            if (!newFile.contains(s)){
                diff.add(s);
            }
        }
        System.out.println(diff.size());
        diff.forEach(System.out::println);

    }
}
