package com.naruto.chapter02;

import com.naruto.chapter01.vo.EmployeeVO;
import com.naruto.chapter02.predicate.MyLongFunc;
import com.naruto.chapter02.predicate.MyStringFunc;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _004LambdaTest {

    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99),
            new EmployeeVO("李四", 38, 5555.99),
            new EmployeeVO("王五", 50, 6666.66),
            new EmployeeVO("赵六", 16, 3333.33),
            new EmployeeVO("田七", 8, 7777.77)
    );

    //通过Collections.sort()排序,先按年龄比,年龄相同按姓名比
    @Test
    public void testSort(){
        Collections.sort(employees,(employee1, employee2) -> {
            if (employee1.getAge() == employee2.getAge()){
               return employee1.getName().compareTo(employee2.getName());
            }else {
                return Integer.compare(employee1.getAge(), employee2.getAge());
            }
        });

        employees.forEach(System.out::println);
    }


    // 声明函数式接口,接口中声明抽象方法
    // 使用接口作为参数,将一个字符串转成大写,并作为方法返回值
    // 再将一个字符串的第2个和第4个索引位置进行截取字符串
    @Test
    public void testString(){
        System.out.println(changeString("wanhuixiang", str -> str.toUpperCase()));
        System.out.println(changeString("wanhuixiang", str -> str.substring(1,4)));
    }

    public String changeString(String str, MyStringFunc<String> msf){
        return msf.change(str);
    }


    //声明一个带两个泛型的函数式接口,泛型类型为<T,R> T 为参数,R 为返回值
    //接口中声明对应抽象方法
    //使用接口作为参数,计算两个long型参数的和
    //计算两个long型参数的乘积
    @Test
    public void testLong(){
        System.out.println(changeLong(1L, 7L, (x, y) -> x + y));
        System.out.println(changeLong(1L, 7L, (x, y) -> x * y));
    }

    public Long changeLong(Long num1, Long num2, MyLongFunc<Long, Long> mlf){
        return mlf.change(num1, num2);
    }

}
