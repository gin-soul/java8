package com.naruto.chapter08;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class _014Stream {


    @Test
    public void single(){
        Instant start = Instant.now();
        long sum = 0L;
        for(long i =0; i <= 100L; i++){
            sum += i;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant end = Instant.now();
        //5050single耗时: 10224
        System.out.println(sum + "single耗时: " + Duration.between(start, end).toMillis());
    }


    @Test
    public void forkJoin() {
        Instant start = Instant.now();
        //需要 forkJoinPool 线程池支持
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        _013ForkJoin forkJoin = new _013ForkJoin(0, 100L);
        Long sum = forkJoinPool.invoke(forkJoin);
        Instant end = Instant.now();
        //5050fork耗时: 3505
        System.out.println(sum + "fork耗时: " + Duration.between(start, end).toMillis());

    }

    //java8 通过 parallel() 集成fork join
    //可以通过 parallel() 并行流方法  和  sequential() 顺序流方法
    //在并行流和顺序流之间进行切换
    @Test
    public void longStream(){
        Instant start = Instant.now();
        //rangeClosed 包含后面的100L,相当于闭区间
        long reduce = LongStream.rangeClosed(0, 100L)
                .parallel()
                .reduce(0, (x, y) -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x + y;
                });
        Instant end = Instant.now();
        //5050parallel耗时: 3502
        System.out.println(reduce + "parallel耗时: " + Duration.between(start, end).toMillis());
    }


}
