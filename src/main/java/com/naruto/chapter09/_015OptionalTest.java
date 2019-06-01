package com.naruto.chapter09;

import com.naruto.chapter01.em.Status;
import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.Optional;

public class _015OptionalTest {

    /**
     * Optional 容器类的常用方法:
     * Optional.of(T t) : 创建一个 Optional 实例
     * Optional.empty() : 创建一个空的 Optional 实例
     * Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
     * isPresent() : 判断是否包含值
     * orElse(T t) : 如果调用对象包含值,返回该值,否则返回 t
     * orElseGet(Supplier s) : 如果调用对象包含值,返回该值,否则返回 s 获得的值
     * map(Function f) : 如果有值对其处理,并返回处理后的 Optional ,否则返回 Optional.empty()
     * flatMap(Function mapper) : 与map类似,要求返回值必须是 Optional
     *
     */
    @Test
    public void testOptionalOfGet(){
        //Optional.of() 不能接收空值 null,直接抛出空指针异常,这种设计是为了方便定位空值具体是谁
        //以便快速定位到哪一行,哪个对象
        Optional<EmployeeVO> optionalEmployeeVO = Optional.of(new EmployeeVO());
        System.out.println(optionalEmployeeVO.get());
    }

    @Test
    public void testOptionalEmpty(){
        //Optional.of() 不能接收空值 null,直接抛出空指针异常,这种设计是为了方便定位空值具体是谁
        //以便快速定位到哪一行,哪个对象
        Optional<EmployeeVO> optionalEmployeeVO = Optional.empty();
        // 这里会直接抛出空值异常(java.util.NoSuchElementException: No value present)
        System.out.println(optionalEmployeeVO.get());
    }

    @Test
    public void testOptionalOfNullable(){
        /**
         * public static <T> Optional<T> ofNullable(T value) {
         *         return value == null ? empty() : of(value);
         *  }
         *
         *  实际上相当于 of 和 empty 的综合
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO = Optional.ofNullable(null);
        // 这里会直接抛出空值异常(java.util.NoSuchElementException: No value present)
        System.out.println(optionalEmployeeVO.get());
    }

    @Test
    public void testOptionalIsPresent(){
        /**
         * isPresent() : 判断是否包含值
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO = Optional.ofNullable(new EmployeeVO());
        // 这里会直接抛出空值异常(java.util.NoSuchElementException: No value present)
        if (optionalEmployeeVO.isPresent()){
            System.out.println(optionalEmployeeVO.get());
        }else {
            System.out.println("空值");
        }

    }

    @Test
    public void testOptionalOrElse(){
        /**
         * 如果调用对象包含值,返回该值,否则返回 t
         * 可以用于设置默认值
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO1 = Optional.ofNullable(new EmployeeVO());
        Optional<EmployeeVO> optionalEmployeeVO2 = Optional.ofNullable(null);

        //检查,null则设置默认值
        EmployeeVO luffy1 = optionalEmployeeVO1.orElse(new EmployeeVO("luffy", 26, 88888, Status.BUSY));
        EmployeeVO luffy2 = optionalEmployeeVO2.orElse(new EmployeeVO("luffy", 26, 88888, Status.BUSY));

        System.out.println(luffy1);
        System.out.println(luffy2);

    }

    @Test
    public void testOptionalOrElseGet(){
        /**
         * 如果调用对象包含值,返回该值,否则返回 Optional.empty()
         * 可以用于设置自定义结果,通过函数式编程(还可以包含自定义的日志 重试 补偿 等功能)
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO = Optional.ofNullable(null);

        //检查,null则设置默认值
        EmployeeVO luffy1 = optionalEmployeeVO.orElseGet(EmployeeVO::new);
        EmployeeVO luffy2 = optionalEmployeeVO.orElseGet(() -> new EmployeeVO("luffy", 26, 88888, Status.BUSY));

        System.out.println(luffy1);
        System.out.println(luffy2);

    }

    @Test
    public void testOptionalMap(){
        /**
         * 如果有值对其处理,并返回处理后的 Optional ,否则返回 Optional.empty()
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO1 = Optional.ofNullable(new EmployeeVO("luffy", 26, 88888, Status.BUSY));
        Optional<EmployeeVO> optionalEmployeeVO2 = Optional.ofNullable(null);

        //检查,null则设置默认值
        Optional<String> name1 = optionalEmployeeVO1.map(e -> e.getName());
        Optional<String> name2 = optionalEmployeeVO2.map(e -> e.getName());


        System.out.println(name1);
        System.out.println(name2);

    }

    @Test
    public void testOptionalFlatMap(){
        /**
         * 与map类似,要求返回值必须是 Optional
         * 进一步防止空指针异常
         * 不能直接返回 e.getName(),而是需要Optional再次包装一次
         */
        //Optional.ofNullable(T t) : 若t不为null,创建 Optional 实例,否则创建空实例
        Optional<EmployeeVO> optionalEmployeeVO1 = Optional.ofNullable(new EmployeeVO("luffy", 26, 88888, Status.BUSY));
        Optional<EmployeeVO> optionalEmployeeVO2 = Optional.ofNullable(null);

        //检查,null则设置默认值
        Optional<String> name1 = optionalEmployeeVO1.flatMap(e -> Optional.of(e.getName()));
        Optional<String> name2 = optionalEmployeeVO2.flatMap(e -> Optional.of(e.getName()));


        System.out.println(name1);
        System.out.println(name2);

    }


}
