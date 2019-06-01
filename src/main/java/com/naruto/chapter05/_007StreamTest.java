package com.naruto.chapter05;

import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class _007StreamTest {

    /**
     * Stream 流(是数据渠道,用于操作数据源(集合,数组等)所生成的元素序列
     *
     *  注意: Sreanm 自己不会存储元素
     *        Stream不会改变源对象.相反,他们会返回一个持有结果的新Stream
     *        Stream操作是延迟执行的.这意味着他们会等到需要结果的时候才执行
     *
     *  Stream的操作三个步骤
     *        1.创建Stream
     *         一个数据源(如:集合,数组等),获取一个流
     *        2.中间操作
     *          一个中间操作链,对数据源的数据进行处理(filter map)
     *        3.终止操作(终端操作)
     *         一个终止操作,执行中间操作链,并产生结果
     */

    //创建Stream
    @Test
    public void testStreamCreate(){
        //1. 可以通过Collection 系列集合提供的stream()串行流 或者 parallelSream() 并行流
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        System.out.println("- - - - - - - - - - - - - - - -");

        //2. 通过Arrays中的静态方法stream()获取数组流
        EmployeeVO[] employeeVOS = new EmployeeVO[16];
        Stream<EmployeeVO> stream2 = Arrays.stream(employeeVOS);

        System.out.println("- - - - - - - - - - - - - - - -");

        //3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        System.out.println("- - - - - - - - - - - - - - - -");

        //4. 创建无限流(UnaryOperator<T> 函数式接口,继承至Function<T, R>
        //4.1 迭代(从 0(seed 种子) 开始(这是个final修饰的值),根据 UnaryOperator 的lambda函数来一直迭代变化

        //值的累计变化
        //tream.iterate(0, x -> x + 2)
        //0
        //2
        //4
        //6
        //8
        // 一直到上限
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        //加上中间操作limit.限制只要前3个
        stream4.limit(3).forEach(System.out::println);

        System.out.println("- - - - - - - - - - - - - - - -");

        // 4.2 生成
        //值的随机生成
        //Stream.generate(() -> Math.random());
        Stream.generate(Math::random).limit(3).forEach(System.out::println);


    }

}
