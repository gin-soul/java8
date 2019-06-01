package com.naruto.chapter09;

import com.naruto.chapter09.vo.Godness;
import com.naruto.chapter09.vo.Man;
import com.naruto.chapter09.vo.NewMan;
import org.junit.Test;

import java.util.Optional;

public class _016OptionalPractice {

    //旧的写法,if判断随着对象层级越多嵌套就越深
    @Test
    public void getGodnessByMan(){
        Man man = new Man();
        System.out.println(getGodnessName(man));
    }

    //需求,获取一个男人心中女神的名字
    public String getGodnessName(Man man){
        if (man != null){
            Godness gn = man.getGodness();
            if (gn != null){
                //层级越多,越往中间陷入
                return gn.getName();
            }
        }
        return "wan hui xiang";
    }


    // 使用链式编程的方式,避免空指针,从嵌套层级上看差不多,但是格式上看起来更直观
    // 不会再产生 龟太气功(最中间的判断要在凹槽中才能看到) 一样的情况
    @Test
    public void getGodnessByMan2(){
        Optional<NewMan> man = Optional.ofNullable(null);
        System.out.println(getGodnessName2(man));
    }

    //需求,获取一个男人心中女神的名字
    //为了防止NewMan为null,也可以要求传入Optional对象
    public String getGodnessName2(Optional<NewMan> newMan){
        //用 new NewMan() 代替 可能出现的 null
        return newMan.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("wan hui xiang"))
                .getName();
    }

}
