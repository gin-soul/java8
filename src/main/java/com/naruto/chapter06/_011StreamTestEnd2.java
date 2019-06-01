package com.naruto.chapter06;

import com.naruto.chapter01.em.Status;
import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class _011StreamTestEnd2 {

    /**
     * 规约 (identity  身份  恒等式; BinaryOperator  二元运算符)
     * reduce(T identity, BinaryOperator)  或  reduce(BinaryOperator)
     * 可以将流中元素反复结合起来,得到一个值
     */
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void testReduceEnd() {
        Integer reduce = list.stream()
                // 0 作为起始值 每次从list中取一个值进行x + y运算,结果取下一个值做x + y运算
                // 0 + 1 = 1
                // 1 + 2 = 3
                // 3 + 3 = 6
                // 6 + 4 = 10
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }


    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99, Status.BUSY),
            new EmployeeVO("李四", 38, 5555.99, Status.FREE),
            new EmployeeVO("王五", 50, 6666.66, Status.FREE),
            new EmployeeVO("赵六", 16, 3333.33, Status.FREE),
            new EmployeeVO("田七", 8, 7777.77, Status.BUSY)
    );

    @Test
    public void testReduceEnd2() {
        //map 和 reduce的连接通常称为 map-reduce 模式,因Google用它来进行网络搜索而出名
        Optional<Double> reduce = employees.stream()
                .map(EmployeeVO::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());
    }


    /**
     * 收集
     * collect  --  将流转换成为其他形式.接收一个Collector接口的实现,用于给Stream中元素做汇总的方法
     */
    @Test
    public void testCollectEnd() {
        //收集所有职员名字到list中
        List<String> collect = employees.stream()
                .map(EmployeeVO::getName)
                //Collectors提供了很多 collector 的静态实现方法,以便直接调用
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("- - - - - - - - - - - - - - - -");

        // 希望收集到特定的集合中
        Set<String> linkedHashSet = employees.stream()
                .map(EmployeeVO::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        linkedHashSet.forEach(System.out::println);

    }

    @Test
    public void testCollectEnd2() {
        //员工总数(数据条数)
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("- - - - - - - - - - - - - - - -");

        //平均工资
        Double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(EmployeeVO::getSalary));
        System.out.println(avgSalary);

        System.out.println("- - - - - - - - - - - - - - - -");

        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(EmployeeVO::getSalary));
        System.out.println(sum);

        //工资最大值
        Optional<EmployeeVO> max = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(EmployeeVO::getSalary)));
        System.out.println(max.get());

        //工资最小
        Optional<Double> min = employees.stream()
                .map(EmployeeVO::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());

        //详细信息
        DoubleSummaryStatistics summarySalary = employees.stream()
                .collect(Collectors.summarizingDouble(EmployeeVO::getSalary));
        System.out.println(summarySalary);

    }


    /**
     * 分组(单级分组)
     */
    @Test
    public void testCollectGroupBy() {
        Map<Status, List<EmployeeVO>> mapGroupBy = employees.stream()
                .collect(Collectors.groupingBy(EmployeeVO::getStatus));
        for (List<EmployeeVO> value : mapGroupBy.values()) {
            System.out.println(value);
        }
    }

    /**
     * 分组(多级分组)
     * 可以一直 Collectors.groupingBy("分组条件1", Collectors.groupingBy("分组条件2", Collectors.groupingBy("分组条件3", ...)))
     * 无限嵌套下去
     */
    @Test
    public void testCollectGroupBy2() {
        Map<Status, Map<String, List<EmployeeVO>>> groupBy2 = employees.stream()
                .collect(Collectors.groupingBy(EmployeeVO::getStatus, Collectors.groupingBy(
                        (e) -> {
                            if (((EmployeeVO) e).getAge() <= 35) {
                                return "青年";
                            } else if (((EmployeeVO) e).getAge() <= 50) {
                                return "中年";
                            } else {
                                return "老年";
                            }
                        })));
        for (Map.Entry<Status, Map<String, List<EmployeeVO>>> statusMapEntry : groupBy2.entrySet()) {
            Status key = statusMapEntry.getKey();
            System.out.println(" - - - " + key + " - - - ");
            for (Map.Entry<String, List<EmployeeVO>> ageMapEntry : statusMapEntry.getValue().entrySet()) {
                System.out.println(ageMapEntry.getKey() + " : " + ageMapEntry.getValue());
            }
        }
    }


    /**
     * 分区(满足条件的一个区(false: xxx),不满足条件的一个区(true:xxx))
     * 可以一直 Collectors.partitioningBy("分组条件1", Collectors.partitioningBy("分组条件2", Collectors.partitioningBy("分组条件3", ...)))
     * 无限嵌套下去
     */
    @Test
    public void testCollectPartition() {
        Map<Boolean, List<EmployeeVO>> salaryPartition = employees.stream().
                collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        salaryPartition.entrySet().forEach(System.out::println);
    }


    /**
     * 字符串拼接
     */
    @Test
    public void testCollectJoin() {
        String join = employees.stream()
                .map(EmployeeVO::getName)
                .collect(Collectors.joining());
        System.out.println(join);

        String join2 = employees.stream()
                .map(EmployeeVO::getName)
                //delimiter 分隔符;  prefix 头;  suffix 尾
                .collect(Collectors.joining(",","首: ", " :尾"));
        System.out.println(join2);
    }



}
