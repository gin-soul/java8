package com.naruto.chapter06;

import com.naruto.chapter01.em.Status;
import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class _010StreamTestEnd {

    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99, Status.BUSY),
            new EmployeeVO("李四", 38, 5555.99, Status.FREE),
            new EmployeeVO("王五", 50, 6666.66, Status.FREE),
            new EmployeeVO("赵六", 16, 3333.33, Status.FREE),
            new EmployeeVO("田七", 8, 7777.77, Status.BUSY)
    );

    /**
     * 终止操作
     *
     * 查找与匹配
     * allMatch  --  检查是否匹配所有元素
     * anyMatch  --  检查是否至少匹配一个元素
     * noneMatch  --  检查是否没有匹配所有元素
     * findAny   --  返回第一个元素
     * count  --  返回流中元素的总个数
     * max  --  返回流中最大值
     * min  --  返回流中最小值
     */

    @Test
    public void testAllMatchEnd(){
        // 是否所有的人都是 FREE 状态的
        boolean allMatch = employees.stream().
                allMatch(employeeVO -> employeeVO.getStatus() == Status.FREE);
        System.out.println(allMatch);

    }

    @Test
    public void testAnyMatchEnd(){
        // 是否有人是 FREE 状态的
        boolean allMatch = employees.stream().
                anyMatch(employeeVO -> employeeVO.getStatus() == Status.FREE);
        System.out.println(allMatch);

    }

    @Test
    public void testNoneMatchEnd(){
        // 是否没有人是 FREE 状态的
        boolean allMatch = employees.stream().
                noneMatch(employeeVO -> employeeVO.getStatus() == Status.VOCATION);
        System.out.println(allMatch);

    }

    @Test
    public void testFindFirstEnd(){
        // 查询工资最低的一个人
        // 因为返回值可能为空(目前场景也就集合为空),
        // 所以使用了Optional(检查到为空时可以替换空值)
        // 类似于 sql 中的 IFNULL(expression_不为null返回该值本身,expression_前一个值为null返回本值);
        //    IFNULL(1,0)返回1，因为1不为NULL。
        //    IFNULL(''，1)返回''，因为''字符串不为NULL。
        //    IFNULL(NULL，'I`m not null')返回'I`m not null'，因为第一个参数为NULL。

        Optional<EmployeeVO> first = employees.stream()
                //下面的 sorted 和 findFirst 逻辑上可以被 min()替代
                //.min(Comparator.comparingDouble(EmployeeVO::getSalary));

                //.sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .sorted(Comparator.comparingDouble(EmployeeVO::getSalary))
                .findFirst();

        System.out.println(first.get());

    }


    @Test
    public void testFindAnyEnd(){
        //串行流,按顺序查找
        //从职员中找到任意一个空闲状态成员,以便加入工作
        Optional<EmployeeVO> any = employees.stream()
                .filter(employee -> employee.getStatus() == Status.FREE)
                .findAny();
        System.out.println(any.get());

        System.out.println("- - - - - - - - - - - - - - - -");

        //并行流,同时查找
        //从职员中找到任意一个空闲状态成员,以便加入工作
        Optional<EmployeeVO> any2 = employees.parallelStream()
                .filter(employee -> employee.getStatus() == Status.FREE)
                .findAny();
        System.out.println(any2.get());
    }

    @Test
    public void testCountEnd(){
        long count = employees.stream()
                .count();
        System.out.println(count);
    }
    
    @Test
    public void testMaxEnd(){
        Optional<EmployeeVO> max = employees.stream()
                .max(Comparator.comparingInt(EmployeeVO::getAge));
        System.out.println(max);
    }
    
    @Test
    public void MintestCountEnd(){
        Optional<Integer> min = employees.stream()
                .map(EmployeeVO::getAge)
                .min(Integer::compare);
        System.out.println(min);
    }

}
