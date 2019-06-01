package com.naruto.chapter08;

import java.util.concurrent.RecursiveTask;

//Recursive递归  RecursiveTask递归任务
public class _013ForkJoin extends RecursiveTask<Long> {

    /**
     * Fork/Join 框架(java中需要继承RecursiveTask(有返回值) 或 RecursiveAction(无返回值))
     * 就是在必要的情况下,将一个大任务,进行拆分(fork)成若干个小任务(拆到不能再拆时),
     * 再将一个个的小任务运算的结果进行join汇总
     *
     * 单纯依赖多线程来处理会产生的问题:(每个任务执行时间不同)
     * CPU 会分配执行时间,无法保证同一组多线程任务不被阻塞同时执行
     * 一旦同一组的任务,其他都完成了(且该线程没队列都空了),
     * 而只有最后一个任务被长时间阻塞了(队列很满,且任务排在队列靠后的位置),
     * 执行完毕的线程就需要空闲等待了,那么浪费cpu其他核的利用率
     *
     */

    private long start;

    private long end;

    public _013ForkJoin() {
    }

    public _013ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 不再拆分的临界值
     */
    private static final long THRESHOLD = 10;


    @Override
    protected Long compute() {

        //每次拆分后还剩下的值范围
        long length = end - start;

        long sum = 0L;

        //值范围 小于 需要拆分的临界值,那么直接计算即可
        if (length <= THRESHOLD){
            for(long i =start; i <= end; i++){
                sum += i;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum;
        }else {
            //开始拆分
            long middle = (start + end) / 2;

            //左半边的任务
            _013ForkJoin left = new _013ForkJoin(start, middle);
            //将左半边的任务压入线程队列
            left.fork();

            //右半边的任务
            _013ForkJoin right = new _013ForkJoin(middle + 1, end);
            //将右半边的任务压入线程队列
            right.fork();

            return left.join() + right.join();
        }

    }


}
