package com.naruto.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gin
 * @date 2019/12/3 10:23
 */
public class FileUtils {

    public static void main(String[] args) {
        splitDataToSaveFile(1000, new File("D:/var/big.txt"), "D:/var/temp");

        /*StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            sb.append("testSplitFile" + i + "\r\n");
        }
        writeFile(sb.toString(), new File("D:/var/big.txt") );*/
    }

    public static List<File> splitDataToSaveFile(int rows, File sourceFile, String targetDirectoryPath) {
        long startTime = System.currentTimeMillis();
        List<File> fileList = new ArrayList<>();
        System.out.println("开始分割文件");
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return null;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return null;
            }
        } else {
            targetFile.mkdirs();
        }

        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            //把每次读取到的行缓存起来,不要每次都写道文件里面(效率低),累计到指定行数再一次写入
            StringBuilder stringBuilder = new StringBuilder();
            String lineStr;
            int lineNo = 1, fileNum = 1;
            while ((lineStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineStr).append("\r\n");
                if (lineNo % rows == 0) {
                    File file = new File(targetDirectoryPath + File.separator + fileNum + sourceFile.getName());
                    writeFile(stringBuilder.toString(), file);
                    //清空文本
                    stringBuilder.delete(0, stringBuilder.length());
                    fileNum++;
                    fileList.add(file);
                }
                lineNo++;
            }
            if ((lineNo - 1) % rows != 0) {
                File file = new File(targetDirectoryPath + File.separator + fileNum + sourceFile.getName());
                writeFile(stringBuilder.toString(), file);
                fileList.add(file);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("分割文件结束，耗时：{}秒" + (endTime - startTime) / 1000);
        } catch (Exception e) {
            System.out.println("分割文件异常" + e);
        }
        return fileList;
    }

    private static void writeFile(String text, File file) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter, 1024)
        ) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取文本文件的总行数
     * @param filePath
     */
    public static int getCountLine(String filePath){
        //总行号
        int lineNumber = 1;
        try {
            //使用这个类,可以直接跳过字符,快速获取行号
            LineNumberReader reader = new LineNumberReader(new FileReader(filePath));
            //获取文件的总大小
            File file = new File(filePath);
            long count = file.length();
            //跳过字符,获取到当前行号,就是总行号
            reader.skip(count);
            lineNumber = reader.getLineNumber();
        } catch (Exception e) {
            System.out.println("getCountLine<|>filePath:"+filePath + e.getMessage());
        }
        return lineNumber;
    }

}
