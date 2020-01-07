package com.naruto.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * @author gin
 * @date 2020/1/7 15:17
 */
public class CreditReportParse {

    private Map<String,String> map = null;
    private String  content = null;
    private List<Map<String, Object>> loanList = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> debitCard = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> semiDebitCard = new ArrayList<Map<String, Object>>();
    private List<Map<String, Object>> overDueList = new ArrayList<Map<String, Object>>();


    public CreditReportParse(){};

    public CreditReportParse(String content){
        this.content = content;
        this.parseHtml();
    };

    public void parseHtml(){
        String html = null;
        map = new HashMap<String,String>(32);

    }

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File("D:/java/code/naruto/java8/src/main/resources/credit.txt");
        String fileTxt = FileUtils.readFileToString(file);
        JSONObject parse = JSONObject.parseObject(fileTxt);
        String text = (String)parse.getJSONObject("Transaction").getJSONObject("Body").getJSONObject("response").getJSONObject("bizBody").get("data");
//        System.out.println(text);
        int i = text.indexOf("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        String xml = text.substring(i);
//        System.out.println(xml);
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();

        Element msgEle = rootElement.element("Msg");
        String resultCode = msgEle.elementText("ResultCode");
        System.out.println("报文:调用状态=" + resultCode);

        Element reportMessage = msgEle.element("ReportMessage");

        byte[] compressed = Base64.getDecoder().decode(reportMessage.getTextTrim().getBytes());

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
            String outXml = out.toString();
//            System.out.println(outXml);
//            FileUtils.writeStringToFile(new File("D:/var/credit.xml"), out.toString());
            Document documentXml = DocumentHelper.parseText(outXml);
            Element docEle = documentXml.getRootElement();
            Element pa01bEle = docEle.element("PRH").element("PA01").element("PA01B");
            System.out.println("报告头:姓名=" + pa01bEle.elementText("PA01BQ01"));
            Element pb01Ele = docEle.element("PIM").element("PB01");
            Element pb01a = pb01Ele.element("PB01A");
            System.out.println("身份信息:姓别=" + pb01a.elementText("PB01AD01"));
            System.out.println("身份信息:生日=" + pb01a.elementText("PB01AR01"));
            System.out.println("身份信息:学历=" + pb01a.elementText("PB01AD02"));
            System.out.println("身份信息:学位=" + pb01a.elementText("PB01AD03"));
            System.out.println("身份信息:邮箱=" + pb01a.elementText("PB01AQ01"));
            System.out.println("身份信息:通讯地址=" + pb01a.elementText("PB01AQ02"));
            System.out.println("身份信息:国籍=" + pb01a.elementText("PB01AD05"));
            System.out.println("身份信息:户籍=" + pb01a.elementText("PB01AQ03"));

            Iterator pb01bhIter = pb01Ele.element("PB01B").elementIterator("PB01BH");
            Element pb01bhIter01 = (Element)pb01bhIter.next();
            System.out.println("身份信息:手机号=" + pb01bhIter01.elementText("PB01BQ01"));

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
