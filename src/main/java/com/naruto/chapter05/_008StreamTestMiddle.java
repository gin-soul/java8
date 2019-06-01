package com.naruto.chapter05;

import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class _008StreamTestMiddle {

    //中间操作
    //多个中间操作可以连接起来形成一个流水线,除非流水线上触发终止操作,否则中间操作不会执行任何的处理
    //而在终止操作触发时一次性全部处理,称为"惰性求值"
    /**
     * 筛选 与 切片
     * filter   -  接收 Lambda ,从流中排除某些元素
     * limit    -  截断流,使其元素不超过给定的数量
     * skip     -  跳过元素,返回一个扔掉了前个元素的流.若流中元素不足n个,则返回一个空流.与limint(n)互补
     * distinct -  筛选,通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99),
            new EmployeeVO("李四", 38, 5555.99),
            new EmployeeVO("王五", 50, 6666.66),
            new EmployeeVO("赵六", 16, 3333.33),
            new EmployeeVO("田七", 8, 7777.77),
            new EmployeeVO("田七", 8, 7777.77)
    );

    //接收 Lambda ,从流中排除某些元素
    @Test
    public void testFilter1(){
        //这里只是产生了一个新流,没有终止操作是不会产生数据的
        //惰性求值
        Stream<EmployeeVO> employeetream = employees.stream()
                                                    .filter(employeeVO -> {
                                                        System.out.println("中间操作,没有终止操作,是否会执行?");
                                                        return employeeVO.getAge() > 35;
                                                    });
        //终止操作
        //employeetream.forEach(System.out::println);
    }

    //内部迭代:迭代操作由 Stream API 完成
    @Test
    public void testFilter2(){
        //这里只是产生了一个新流,没有终止操作是不会产生数据的
        Stream<EmployeeVO> employeetream = employees.stream()
                .filter(employeeVO -> {
                    //这里会执行五次,因为总共有5条数据,每次判断进来就会打印一句
                    System.out.println("中间操作,有终止操作,才会执行");
                    //符合条件,则会对符合条件的做对应的终止操作
                    return employeeVO.getAge() > 35;
                });
        //终止操作,一次性执行全部内容(中间和终止操作同时在终止时开始执行)
        employeetream.forEach(System.out::println);
        /*
中间操作有终止操作,才会执行                      --  张三判断
中间操作有终止操作,才会执行                      --  李四判断
EmployeeVO{name='李四', age=38, salary=5555.99}  --  李四判断成功,执行终止操作
中间操作有终止操作,才会执行                      --  王五判断
EmployeeVO{name='王五', age=50, salary=6666.66}  --  王五判断成功,执行终止操作
中间操作有终止操作,才会执行
中间操作有终止操作,才会执行
         */
    }

    //外部迭代
    @Test
    public void testIterator(){
        Iterator<EmployeeVO> it = employees.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    //截断流,使其元素不超过给定的数量
    @Test
    public void testLimit(){
        employees.stream()
                .filter( e -> {
                    //发现该语句只执行了两次
                    //说明达到limit的上限的时候,后面的数据就不进行判断了
                    System.out.println("短路");
                    return  e.getSalary() > 5000;
                })
                //短路操作,可提高效率 (类似 &&  || )
                .limit(2)
                .forEach(System.out::println);
    }

    //截断流,使其元素不超过给定的数量
    @Test
    public void testSkip(){
        employees.stream().
                filter(e -> e.getSalary() > 5000).
                skip(2).
                forEach(System.out::println);
    }

    //去除重复元素
    @Test
    public void testDistinct(){
        // hashCode 和 equals 方法没有重写时,发现并没有去重!
        //注意: distinct() 去重是需要重写对象的 hashCode 和 equals 方法的
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .distinct()
                .forEach(System.out::println);
    }



}
