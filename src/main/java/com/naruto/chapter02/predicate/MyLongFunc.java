package com.naruto.chapter02.predicate;

@FunctionalInterface
public interface MyLongFunc<T,R> {
    R change(T t1, T t2);
}
