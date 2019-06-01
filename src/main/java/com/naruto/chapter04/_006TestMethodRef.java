package com.naruto.chapter04;

import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class _006TestMethodRef {

    /*
    *
    * 一.方法引用:若 Lambda 体中的内容有方法已经实现了,我们可以使用"方法引用"
    * (可以理解为方法引用是 Lambda 表达式的另外一种表现形式)
    *
    * 主要有三种语法格式:
    *
    * 注意: Lambda 体中调用方法(通过方法引用::)的参数列表与返回值类型,
    * 1.要与函数式接口中抽象方法的参数列表和返回值类型保持一致
    * 2.当且仅当第一个参数为方法调用者,第二个参数(或无第二参数)为方法的入参时,
    * 才能使用 ClassName::method来调用实例方法
    *
    * 对象::实例方法
    *
    * 类::静态方法名
    *
    * 类::实例方法名
    *
    *
    * 二.构造器引用:
    *
    * 格式
    *
    * ClassName::new
    *
    * 注意: 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
    *
    *
    * 三: 数组引用
    * Type::new
    *
    *
    * */

    //对象::实例方法
    @Test
    public void testReferenceByObject1(){
        //这条lambda表达式x -> System.out.println(x) 是PrintStream的一个实例方法
        // 实际上可以完全被 System类下的 public void println(String x) 方法替代
        //所以我们可以使用更简洁的写法:因为这是个实例方法.所以使用(对象::实例方法)方式
        Consumer<String> consumer1 = x -> System.out.println(x);
        consumer1.accept("wanhuixiang");
        System.out.println("- - - - - - - - - - - - - - - -");
        PrintStream ps = System.out;
        //使用 public final static PrintStream out = null; 这个对象
        Consumer consumer2 = ps::println;
        consumer2.accept("zengxiang");
    }



    @Test
    public void testReferenceByObject2(){
        EmployeeVO employeeVO = new EmployeeVO("孙七", 99, 99999);
        //实际上 employeeVO.getName() 也就是 employeeVO 对象的一个实例方法
        Supplier<String> supplier = () -> employeeVO.getName();
        System.out.println(supplier.get());

        System.out.println("- - - - - - - - - - - - - - - -");

        //使用 对象::实例方法 的方式
        Supplier<Integer> supplier2 = employeeVO::getAge;
        System.out.println(supplier2.get());

    }


    // 类::静态方法
    @Test
    public void testReferenceByClass1(){

        /**
         * compare 是 Integer 的一个静态方法
         * 两个三元运算符,省略了大量if return
         * public static int compare(int x, int y) {
         *         return (x < y) ? -1 : ((x == y) ? 0 : 1);
         * }
         */
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(0, 1));

        System.out.println("- - - - - - - - - - - - - - - -");

        //使用 类::静态方法 的方式
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1, 0));

    }


    //类::实例方法名
    @Test
    public void testReferenceByClass2(){
        BiPredicate<String,String> biPredicate1 = (x, y) -> x.equals(y);
        System.out.println(biPredicate1.test("wanhuixiang", "zengxiang"));


        System.out.println("- - - - - - - - - - - - - - - -");

        //规则 : 当且仅当第一个参数为方法调用者,第二个参数(或无第二参数)为方法的入参时,才能使用
        BiPredicate<String,String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("xiang", "xiang"));

    }

    //类::new 无参构造器(使用Supplier)
    @Test
    public void testReferenceByClass3(){
        Supplier<EmployeeVO> supplier = () -> new EmployeeVO();
        System.out.println(supplier.get());

        System.out.println("- - - - - - - - - - - - - - - -");

        Supplier<EmployeeVO> supplier2 = EmployeeVO::new;
        System.out.println(supplier2.get());
    }

    //类::new 含参构造器(使用Function)
    // 通过方法类型推断 来引用对应的构造器
    // (对应函数式接口抽象方法含有几个参数,调用的构造方法就有几个参数)
    // 另: 参数类型也要保持一致
    @Test
    public void testReferenceByClass4(){
        Function<String, EmployeeVO> func = name -> new EmployeeVO(name);
        System.out.println(func.apply("naruto"));

        System.out.println("- - - - - - - - - - - - - - - -");

        Function<String, EmployeeVO> func2 = EmployeeVO::new;
        System.out.println(func2.apply("naruto"));

        System.out.println("- - - - - - - - - - - - - - - -");

        BiFunction<String,Integer,EmployeeVO> biFunc = EmployeeVO::new;
        System.out.println(biFunc.apply("naruto", 88));
    }


    //Type::new 数组引用
    @Test
    public void testReferenceByType(){
        Function<Integer, String[]> func = x -> new String[x];
        System.out.println(func.apply(6).length);

        Function<Integer, String[]> func2 = String[]::new;
        System.out.println(func2.apply(20).length);
    }

}
