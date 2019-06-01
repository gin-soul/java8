package com.naruto.chapter03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class _005LambdaStandardFunctional {

    /*
    * Java8 内置的四大核心函数式接口
    *
    * Consumer<T> : 消费型接口(有去无回)
    * void accept(T t);
    *
    * Supplier<T> : 供给型接口(提供资源)
    * T get();
    *
    * Function<T, R> : 函数型接口
    * R apply(T t);
    *
    * Predicate<T> : 断言型接口
    * boolean test(T t);
    *
    *
    * */


    @Test
    public void testConsumer(){
        happy(10000.00d, m -> System.out.println("大保健消费了: " + m + " 元"));
    }

    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }


    @Test
    public void testSupplier(){
        System.out.println(get(() -> Math.random() * 7));
    }

    public List<Integer> get(Supplier<Double> consumer){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < consumer.get(); i++) {
            list.add(7);
        }
        return list;
    }


    @Test
    public void testFunction(){
        System.out.println(function(8, x -> 7 * x + 6 ));
    }

    public Integer function(int x, Function<Integer, Integer> func){
        return func.apply(x);
    }


    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList("Hello", "World", "Lambda");
        System.out.println(predicate(list, x -> x.length() > 5 ));
    }

    public List<String> predicate(List<String> list, Predicate<String> predicate){
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)){
                result.add(s);
            }
        }
        return result;
    }

}
