package com.naruto.json;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> invokeFailedList = new LinkedList<>();
        invokeFailedList.add("1");
        invokeFailedList.add("2");
        invokeFailedList.add("3");
        String join = StringUtils.join(invokeFailedList, ",");
        System.out.println("-" + join + "-");
    }
}


