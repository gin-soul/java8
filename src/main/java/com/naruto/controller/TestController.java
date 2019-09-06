package com.naruto.controller;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class TestController {

    @RequestMapping("/test/ok")
    public String testOk(){
        return "ok";
    }

    @RequestMapping("/get/{id}")
    public String getId(@PathVariable("id")Integer id){
        return String.valueOf(id);
    }

    @RequestMapping("/{method}/{num1}/{num2}")
    public String numFunc(@PathVariable("method")String method,@PathVariable("num1")Integer num1,@PathVariable("num2")Integer num2){
        if ("add".equals(method)){
            return String.valueOf(num1 + num2);
        }else if ("sub".equals(method)){
            return String.valueOf(num1 - num2);
        }
        return "error";
    }

    private static Map<String,Object>  mapStandard = new HashMap<String,Object>(){{
        put("pbp_loss_loan_cnt","1");
        put("credit_report_source","1");
        put("education","高中");
        put("pbp_doutful_loan_cnt","1");
        put("guarantee_abnormal_loan_amt_cnt","4");
        put("query_time","2019-07-06 12:25");
        put("error_message","放心,没错");
        put("debit_current_overdue_cnt","4");
        put("semi_debit_current_overdue_cnt","4");
        put("code","1");
        put("pbp_specialmention_loan_cnt","1");
        put("ltst12mon_estate_car_loan_num","1");
        put("pbp_substandard_loan_cnt","1");
        put("report_code","1101");
        put("mobile","110");
        put("guarantee_abnormal_loan_amt","4000.0");
        put("queryreason","02");
        put("mobile_spouse","120");
        put("rh_default_loan_num","3");
        put("cert_no_of_spouse","1201");

        //模型预处理字段
        put("sum_overdue_month_cnt_null","0");
        put("sum_overdue_month_cnt_0","0");
        put("sum_overdue_month_cnt_1","0");
        put("sum_overdue_month_cnt_2","0");
        put("sum_overdue_month_cnt_3","1");
        put("sum_overdue_month_cnt_6","0");

        //个体信息
        put("pbp_empty_flag","0");
        put("pbp_execution_flag","1");
        put("marital_status","已婚");

        //两年内已结清贷款
        put("loan_2year_settled_longest_overdue_month_cnt","3");
        put("loan_2year_settled_sum_overdue_month_cnt","1");
        put("debit_2year_settled_sum_overdue_month_cnt","2");
        put("debit_2year_settled_longest_overdue_month_cnt","3");
        put("semi_2year_settled_sum_overdue_month_cnt","6");
        put("semi_2year_settled_longest_overdue_month_cnt","7");

        //两年内 及 当前
        put("loan_2year_current_max_overdue_money_amt","8000.0");
        put("debit_2year_current_max_overdue_money_amt","5000.0");
        put("semi_2year_current_max_overdue_money_amt","3000.0");
        put("credit_card_2year_current_max_overdue_money_amt","5000.0");
        put("loan_2year_max_overdue_money_amt","9000.0");
        put("debit_2year_max_overdue_money_amt","5000.0");
        put("semi_2year_max_overdue_money_amt","10000.0");
        put("credit_card_2year_max_overdue_money_amt","10000.0");

        put("loan_sum_present_overdue_month_cnt","1");
        put("debit_sum_present_overdue_month_cnt","2");
        put("semi_sum_present_overdue_month_cnt","3");
        put("credit_card_2year_current_sum_overdue_month_cnt","5");
        put("sum_present_overdue_month_cnt","6");
        put("loan_sum_overdue_month_cnt","4");
        put("debit_sum_overdue_month_cnt","8");
        put("semi_sum_overdue_month_cnt","15");
        put("credit_card_2year_sum_overdue_month_cnt","23");
        put("sum_overdue_month_cnt","27");

        put("loan_2year_current_longest_overdue_month_cnt","1");
        put("debit_2year_current_longest_overdue_month_cnt","1");
        put("semi_2year_current_longest_overdue_month_cnt","1");
        put("credit_card_2year_current_longest_overdue_month_cnt","1");
        put("loan_longest_overdue_month_cnt","3");
        put("semi_longest_overdue_month_cnt","7");
        put("debit_longest_overdue_month_cnt","6");
        put("credit_card_2year_longest_overdue_month_cnt","7");
        put("longest_overdue_month_cnt","7");

        put("total_2year_unsettled_normal_payment_month_cnt","78");

        //近五年异常记录
        put("rh_trans_loan_num","1");
        put("rh_bad_loan_num","2");
        put("loan_state_bad_balance","14300.0");
        put("guarantee_focus_loan_amt_cnt","1");
        put("guarantee_bad_loan_amt_cnt","3");
        put("guarantee_loan_5_state_bad_amt","3000.0");
        put("credit_card_state_freeze_cnt","1");
        put("rh_stoppayment_card_num","2");
        put("rh_bad_card_num","4");

        //近两年以前
        put("loan_2year_ago_sum_overdue_month_cnt","2");
        put("loan_2year_ago_longest_overdue_month_cnt","2");
        put("loan_2year_ago_max_overdue_money_amt","1.0");
        put("credit_card_2year_ago_sum_overdue_month_cnt","5");
        put("credit_card_2year_ago_longest_overdue_month_cnt","6");
        put("credit_card_2year_ago_max_overdue_money_amt","10000.0");
        put("total_2year_ago_sum_overdue_month_cnt","7");

        //近五年征信记录
        put("rh_sum_loan_curdefault_month_m60","6");
        put("rh_max_loan_curdefault_month_m60","3");
        put("rh_max_loan_curdefault_amt_m60","9000.0");
        put("rh_sum_card_default_ct_5y","28");
        put("rh_max_card_default_ct_5y","7");
        put("rh_max_card_default_amt_5y","10000.0");
        put("total_5year_sum_overdue_month_cnt","34");
        put("total_5year_loan_cnt","7");
        put("total_5year_credit_card_cnt","11");
    }};

    private static Map<String,Object>  maptest = new HashMap<String,Object>(){{
        put("rh_max_card_default_amt_5y","10000.0");
        put("pbp_loss_loan_cnt","1");
        put("credit_report_source","1");
        put("education","高中");
        put("sum_overdue_month_cnt_null","0");
        put("semi_sum_overdue_month_cnt","15");
        put("pbp_doutful_loan_cnt","1");
        put("loan_2year_current_max_overdue_money_amt","8000.0");
        put("sum_overdue_month_cnt_1","0");
        put("guarantee_focus_loan_amt_cnt","1");
        put("total_5year_credit_card_cnt","11");
        put("sum_overdue_month_cnt_0","0");
        put("sum_overdue_month_cnt_3","1");
        put("semi_2year_settled_longest_overdue_month_cnt","7");
        put("sum_overdue_month_cnt_2","0");
        put("query_time","2019-08-02 19:19:03");
        put("loan_2year_settled_longest_overdue_month_cnt","3");
        put("loan_longest_overdue_month_cnt","3");
        put("semi_2year_max_overdue_money_amt","10000.0");
        put("sum_overdue_month_cnt_6","0");
        put("rh_trans_loan_num","1");
        put("debit_current_overdue_cnt","4");
        put("sum_present_overdue_month_cnt","6");
        put("debit_2year_settled_longest_overdue_month_cnt","3");
        put("rh_max_card_default_ct_5y","7");
        put("semi_2year_current_max_overdue_money_amt","3000.0");
        put("loan_2year_ago_longest_overdue_month_cnt","2");
        put("total_5year_loan_cnt","7");
        put("credit_card_2year_current_longest_overdue_month_cnt","1");
        put("pbp_specialmention_loan_cnt","1");
        put("ltst12mon_estate_car_loan_num","1");
        put("debit_2year_settled_sum_overdue_month_cnt","2");
        put("pbp_substandard_loan_cnt","1");
        put("rh_sum_loan_curdefault_month_m60","6");
        put("rh_sum_card_default_ct_5y","28");
        put("report_code","1101");
        put("guarantee_bad_loan_amt_cnt","3");
        put("semi_2year_current_longest_overdue_month_cnt","1");
        put("loan_2year_ago_sum_overdue_month_cnt","2");
        put("loan_sum_overdue_month_cnt","4");
        put("total_2year_unsettled_normal_payment_month_cnt","78");
        put("semi_longest_overdue_month_cnt","7");
        put("rh_max_loan_curdefault_month_m60","3");
        put("debit_2year_settled_max_overdue_money_amt","100.0");
        put("rh_default_loan_num","3");
        put("loan_2year_current_longest_overdue_month_cnt","1");
        put("debit_2year_max_overdue_money_amt","5000.0");
        put("cert_no_of_spouse","1201");
        put("loan_2year_settled_sum_overdue_month_cnt","1");
        put("credit_card_2year_sum_overdue_month_cnt","23");
        put("rh_bad_card_num","4");
        put("credit_card_2year_max_overdue_money_amt","10000.0");
        put("rh_stoppayment_card_num","2");
        put("debit_2year_current_longest_overdue_month_cnt","1");
        put("guarantee_abnormal_loan_amt_cnt","4");
        put("assurer_repay_sum_money","null");
        put("error_message","放心,没错");
        put("credit_card_2year_longest_overdue_month_cnt","7");
        put("pbp_empty_flag","0");
        put("semi_2year_settled_sum_overdue_month_cnt","6");
        put("credit_card_2year_current_sum_overdue_month_cnt","5");
        put("semi_sum_present_overdue_month_cnt","3");
        put("semi_2year_settled_max_overdue_money_amt","10000.0");
        put("debit_longest_overdue_month_cnt","6");
        put("sum_overdue_month_cnt","27");
        put("loan_2year_settled_max_overdue_money_amt","1.0");
        put("loan_2year_ago_max_overdue_money_amt","1.0");
        put("credit_card_2year_current_max_overdue_money_amt","5000.0");
        put("pbp_execution_flag","1");
        put("semi_debit_current_overdue_cnt","4");
        put("rh_max_loan_curdefault_amt_m60","9000.0");
        put("code","1");
        put("debit_sum_present_overdue_month_cnt","2");
        put("debit_2year_current_max_overdue_money_amt","5000.0");
        put("credit_card_2year_ago_longest_overdue_month_cnt","6");
        put("debit_sum_overdue_month_cnt","8");
        put("rh_bad_loan_num","2");
        put("credit_card_state_bad_balance","9900.0");
        put("loan_2year_max_overdue_money_amt","9000.0");
        put("total_2year_ago_sum_overdue_month_cnt","7");
        put("loan_state_bad_balance","14300.0");
        put("mobile","110");
        put("credit_card_2year_ago_max_overdue_money_amt","10000.0");
        put("guarantee_abnormal_loan_amt","4000.0");
        put("queryreason","02");
        put("mobile_spouse","120");
        put("marital_status","已婚");
        put("credit_card_state_freeze_cnt","1");
        put("credit_card_2year_ago_sum_overdue_month_cnt","5");
        put("guarantee_loan_5_state_bad_amt","3000.0");
        put("longest_overdue_month_cnt","7");
        put("loan_sum_present_overdue_month_cnt","1");
        put("total_5year_sum_overdue_month_cnt","34");
    }};

    public static void main(String[] args) {
        mapStandard.forEach((k,v)->{
            Object test = maptest.get(k);
            if (!v.equals(test)){
                System.out.println(k +" 标准值=" + v + " 而实际值为=" + test);
            }
        });
    }

    @Test
    public void test(){
        System.out.println(withinYears("2018年07月12", 1));
    }

    public static boolean withinYears(String historyTime, int range) {
        try {
            if (StringUtils.isNotBlank(historyTime)){
                Calendar nowDate = Calendar.getInstance();
                nowDate.add(Calendar.MONTH, (range * (-12)));
                //2019年05月01日
                String nowBeforeYears = new SimpleDateFormat("yyyy年MM月dd日").format(nowDate.getTime());
                if (nowBeforeYears.compareTo(historyTime) <= 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void test0(){
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:getQeSynchroResponseResponse xmlns:ns2=\"http://services.ec.synchro.webservices.cums.prefint.icfcc.com\"><return><errorMessage>拆分信用报告异常|</errorMessage><resultCode>1</resultCode><resultLineList xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"></resultLineList><queryReason>01</queryReason></return></ns2:getQeSynchroResponseResponse></S:Body></S:Envelope>";
            Document doc = DocumentHelper.parseText(xml);
            Element returnData = doc.getRootElement().element("Body").element("getQeSynchroResponseResponse").element("return");
            String code = returnData.elementText("resultCode");
            String creditreportSource = returnData.elementText("creditreportSource");
            String errorMessage = returnData.elementText("errorMessage");

            if ("1".equals(code)){
                if (StringUtils.isNotBlank(errorMessage)) {
                    try {
                        //重置 code 值,返回成功但是又有错误信息,置为3
                        //返回拆分信用报告失败正常有俩种情况，1是信用报告格式有变动真的拆分失败2.就是无征信报告白户情况，征信中心就给了一个表格写的是借款人信息不存在
                        //金电回复:第一种出现的概率比较低，我们这么长时间没遇到过
                        code = "2";
                        Element resultCodeEle = returnData.element("resultCode");
                        resultCodeEle.setText(code);
                        xml = doc.asXML();
                        System.out.println("修改后的:" + xml);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter pattern =
                DateTimeFormatter.ofPattern("G yyyy年MM月dd日 E a hh时mm分ss秒");
        String format = now.format(pattern);
        System.out.println(format);
    }


    @Test
    public void test2(){
        String content = "NNN123/234///5/*#";
        String content2 = "////////////";

        System.out.println(isMatchedByPattern(content, "(\\d)+|(N)+|(n)+"));
        System.out.println(isMatchedByPattern(content2, "(\\d)+|(N)+|(n)+"));

        System.out.println(isMatchedByPattern(content, "(/)+|(\\*)+|(#)+"));

    }

    private boolean isMatchedByPattern(String content, String regex) {
        Pattern hasLoanPattern = Pattern.compile(regex);
        Matcher hasLoanMatcher = hasLoanPattern.matcher(content);
        return hasLoanMatcher.find();
    }


}
