package com.naruto.chapter02.predicate;

@FunctionalInterface
public interface MyStringFunc<T> {
    T change(T t);
}
