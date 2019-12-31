package com.naruto.test;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 * @author gin
 * @date 2019/12/31 16:01
 */
public class PbocTwoXML {
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File("D:/java/code/naruto/java8/src/test/resources/html/zhangmoumou.txt");
        String fileTxt = FileUtils.readFileToString(file);

        Document document = DocumentHelper.parseText(fileTxt);
        Element rootElement = document.getRootElement();

        Element ReportMessage = rootElement.element("Msg").element("ReportMessage");

        byte[] compressed = Base64.getDecoder().decode(ReportMessage.getTextTrim().getBytes());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        try {
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            out.flush();
            System.out.println(out.toString());
            FileUtils.writeStringToFile(new File("D:/java/code/naruto/java8/src/test/resources/html/zhangmoumou_part.xml"), out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ginzip != null) {
                ginzip.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
