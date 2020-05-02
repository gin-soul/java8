package com.naruto.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author gin
 * @date 2020/1/29 17:42
 */
public class DecodeTest {

    public static void main(String[] args)
    {

        String str = "测试字符转换 a beautiful girl"; //默认环境，已是UTF-8编码
        try {
            String strGBK = URLEncoder.encode(str, "GBK");
            System.out.println(strGBK);
            String strUTF8 = URLDecoder.decode(str, "UTF-8");
            System.out.println(strUTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecode(){
        try {
            String str = "\\xc3\\xa5\\xc2\\x85\\xc2\\xac\\xc3\\xa5\\xc2\\x8a\\xc2\\xa1\\xc3\\xa5\\xc2\\x91\\xc2\\x98";
            String sourceArr[] = str.split("\\\\");
            byte[] byteArr = new byte[sourceArr.length - 1];
            for (int i = 1; i < sourceArr.length; i++) {
                Integer hexInt = Integer.decode("0" + sourceArr[i]);
                System.out.println(hexInt);
                System.out.println(hexInt.byteValue());
                byteArr[i - 1] = hexInt.byteValue();
            }
            System.out.println(new String(byteArr, "GBK"));
            System.out.println(new String(byteArr, "Unicode"));
            System.out.println(new String(byteArr, "UTF-8"));
            System.out.println(new String(byteArr, "UTF-16"));
            System.out.println(new String(byteArr, "UTF-32"));
            System.out.println(new String(byteArr, "iso-8859-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解码
    public static String unicodeToStr(String unicode) {
        if (unicode == null || "".equals(unicode)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        return sb.toString();
    }
    //编码
    public static String strToUnicode(String string) {
        if (string == null || "".equals(string)) {
            return null;
        }
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }


    @Test
    public void testDecode2(){
        try {
            String string = "中国\u6211\u7231\u5317\u4EAC";
            byte[] utf8 = string.getBytes("UTF-8");
            for (int i = 0; i < utf8.length; i++) {
                System.out.println(utf8[i]);
            }
            string = new String(utf8, "UTF-8");
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
