package com.naruto.chapter02;

import com.naruto.chapter02.predicate.MyFunc;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class _003LambdaBegin {

    /*
    * 一. Lambda 表达式的基础语法:Java8中引入了一个新的操作符"->"
    *       该操作符称为箭头操作符 或 Lambda 操作符
    *
    *      箭头操作符将 Lambda 表达式拆分成两部分:
    *      左侧:Lambda 表达式的参数列表
    *      右侧:Lambda 表达式所需执行的功能,即 Lambda 体
    *
    *      语法格式一: 无参数,无返回值
    *      () -> 接口实现功能
    *      例: () -> System.out.println("hello lambda");
    *
    *      语法格式二:有一个参数,无返回值
    *
    *      语法格式三:多个参数,有返回值
    *
    *      语法格式四: (x, y) 可以省略类型,但是要写了其中一个,就都需要写
    *      因为java8会根据上下文进行类型推断
    *
    *
    *      总结: 源(T x, T y) -> { return x+y;}
    *      能省则省
    *      左右遇一括号省
    *      左侧推断类型省
    *
    *
    *  二. Lambda 表达式需要"函数式接口"的支持
    *       函数式接口: 接口中只有一个抽象方法的接口,称为函数式接口.可以使用 @FunctionalInterface 修饰
    *       @FunctionalInterface 可以检查是否是函数式接口
    * */

    //语法格式一: 无参数,无返回值
    @Test
    public void testRunnable() throws InterruptedException {
        //其实还是final int num =7;  就是不用我们手动添加final了
        //因为线程是新开辟空间执行,不能确定执行时源线程的num是否还存在
        int num = 7;
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world " + num);
            }
        };
        runnable1.run();

        System.out.println("--- --- --- --- --- ---");

        Runnable runnable2 = () -> System.out.println("hello lambda " + num);
        runnable2.run();
    }


    //语法格式二:有一个参数,无返回值
    @Test
    public void testConsumer() {
        Consumer<String> consumer1 = (name) -> System.out.println(name);
        consumer1.accept("羽");

        //只有一个参数的时候,可以省略左侧的小括号
        Consumer<Integer> consumer2 = num -> System.out.println(num * num);
        consumer2.accept(7);
    }

    //语法格式三:多个参数,有返回值
    @Test
    public void testComparator() {
        //lambda右侧功能有多条语句时,必须使用大括号
        Comparator<Integer> comparable1 = (x, y) ->{
            if (x == null && y ==null) return 0;
            x = x == null ? 0 : x;
            y = y == null ? 0 : y;
            if (x < y) return -1;
            else if (x.equals(y)) return 0;
            else return 1;
        };

        System.out.println(comparable1.compare(1, null));

        System.out.println("--- --- --- --- --- ---");

        //如果只有一条语句,那么 大括号 和return 都能省略
        Comparator<Integer> comparable2 = (x, y) -> Integer.compare(x, y);
        System.out.println(comparable2.compare(1, 2));

        System.out.println("--- --- --- --- --- ---");

        //Comparator.comparingInt 本身接收函数,同时,自己还有默认的Comparator的实现
        Comparator<Integer> comparable3 = Comparator.comparingInt(x -> x);
        System.out.println(comparable3.compare(1, 2));
    }


    //需求: 对一个数进行运算(类似函数)
    @Test
    public void testFunction(){
        System.out.println(operation(100, x -> x * 5 + 20));

        System.out.println(operation(100, x -> x * 10 + x * 3 + x / 10 + 4));
    }

    public int operation(int num, MyFunc mf){
        return mf.function(num);
    }

}
