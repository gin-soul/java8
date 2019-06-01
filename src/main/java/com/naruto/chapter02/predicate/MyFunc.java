package com.naruto.chapter02.predicate;

//@FunctionalInterface 检查接口是否只有一个,多个会报错
@FunctionalInterface
public interface MyFunc {
    int function(int num);
}
