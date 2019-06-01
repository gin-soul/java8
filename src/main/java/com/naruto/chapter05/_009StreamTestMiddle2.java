package com.naruto.chapter05;

import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class _009StreamTestMiddle2 {

    //中间操作
    //多个中间操作可以连接起来形成一个流水线,除非流水线上触发终止操作,否则中间操作不会执行任何的处理
    //而在终止操作触发时一次性全部处理,称为"惰性求值"
    /**
     * 映射
     * map  -- 接收 Lambda ,将元素转换成其他形式或提取信息.接收一个函数作为参数,该函数会被应用到每个元素上
     *         并将其映射成一个新的元素
     *
     * flatMap  -- 接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     *
     *
     * 排序
     * sorted()  --  自然排序(Comparable),按照默认的排序方式
     * sorted(Comparator comp)  --  定制排序(Comparator)
     *
     */

    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99),
            new EmployeeVO("李四", 38, 5555.99),
            new EmployeeVO("王五", 50, 6666.66),
            new EmployeeVO("赵六", 16, 3333.33),
            new EmployeeVO("田七", 8, 7777.77)
    );


    List<String> list = Arrays.asList("wan", "hui", "xiang", "zeng");

    //接收 Lambda ,从流中排除某些元素
    @Test
    public void testMap(){

        list.stream()
                //.map(str -> str.toUpperCase())
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("- - - - - - - - - - - - - - - -");

        employees.stream()
                .map(EmployeeVO::getName)
                .forEach(System.out::println);

    }

    @Test
    public void testMap2(){

        //每个 str 都被装成了一个流,那么就是四个新流,然后被一个新流包含(第五个流)
        // { { 'w', 'a', 'n' }, { 'h', 'u', 'i'} }
        Stream<Stream<Character>> streamStream = list.stream()
                .map(_009StreamTestMiddle2::filterCharater);

        //那么就需要两次循环才能将这两层数据都取出来,这时候就有了 flatMap(flat 扁平化, 扁平化映射流)
        streamStream.forEach(
                bigStream -> {bigStream.forEach(System.out::println);}
                );

    }

    public static Stream<Character> filterCharater(String str){
        List<Character> characters = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characters.add(character);
        }
        return characters.stream();
    }


    @Test
    public void testFlatMap(){
        //接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
        //最终形成的不是一个大流内部包含多个小流,而是仅仅产生一个扁平化流
        //  普通map  ->  flatMap
        // (可以类比 : List<Object> 集合的add(直接添加对象) 和 addAll(将对象里面的元素取出来,添加进去) 方法的区别)
        //{ { 'w', 'a', 'n' }, { 'h', 'u', 'i'} }  ->  { 'w', 'a', 'n', 'h', 'u', 'i'}
        Stream<Character> characterStream = list.stream()
                                                .flatMap(_009StreamTestMiddle2::filterCharater);
        characterStream.forEach(System.out::println);

    }


    //sorted()  --  自然排序(Comparable),按照默认的排序方式
    @Test
    public void testSorted(){
        //String 的类型就按照 String的compareTo 进行排序,ASCII码表中的字符顺序
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("- - - - - - - - - - - - - - - -");

        list.stream()
                //  .sorted((e1, e2) -> e1.length() - e2.length())
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);

    }


}
