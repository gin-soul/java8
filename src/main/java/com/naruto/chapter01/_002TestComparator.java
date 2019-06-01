package com.naruto.chapter01;

import com.naruto.chapter01.predicate.FilterEmployeeByAge;
import com.naruto.chapter01.predicate.FilterEmployeeBySalary;
import com.naruto.chapter01.predicate.MyPredicate;
import com.naruto.chapter01.vo.EmployeeVO;
import org.junit.Test;

import java.util.*;

public class _002TestComparator {


    //1.0 原来的匿名内部类,实现比较大小
    @Test
    public void testComparatorOriginal(){
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //实际仅仅该条策略起到决定作用(即有实际逻辑上的意义)
                return Integer.compare(o1, o2);
            }

        };

        System.out.println(comparator.compare(1, 2));

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);

        treeSet.add(9);
        treeSet.add(6);
        treeSet.add(8);
        treeSet.add(7);

        System.out.println(treeSet);
    }


    //2.0 lambda表达式
    @Test
    public void testComparatorLambda(){

        Comparator<Integer> comparatorLambda = (x, y) -> Integer.compare(x, y);
        //方法引用,类名::静态方法,后续会演示
        //Comparator<Integer> comparatorLambda = Integer::compare;

        TreeSet<Integer> treeSet = new TreeSet<>(comparatorLambda);

        treeSet.add(9);
        treeSet.add(6);
        treeSet.add(8);
        treeSet.add(7);

        System.out.println(treeSet);

    }


    //需求: 获取当前公司中员工年龄大于35的员工信息
    //      获取薪资大于5000的员工信息
    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99),
            new EmployeeVO("李四", 38, 5555.99),
            new EmployeeVO("王五", 50, 6666.66),
            new EmployeeVO("赵六", 16, 3333.33),
            new EmployeeVO("田七", 8, 7777.77)
    );


    @Test
    public void testFilterEmployeeAge(){
        List<EmployeeVO> emps = new ArrayList<>();

        for (EmployeeVO employee : employees) {
            if (employee.getAge() >= 35){
                emps.add(employee);
            }
        }

        System.out.println(emps);
    }

    @Test
    public void testFilterEmployeeSalary(){
        List<EmployeeVO> emps = new ArrayList<>();

        for (EmployeeVO employee : employees) {
            if (employee.getSalary() >= 5000){
                emps.add(employee);
            }
        }

        System.out.println(emps);
    }

    //优化方式一: 策略模式
    public List<EmployeeVO> testFilterEmployee(List<EmployeeVO> employees, MyPredicate<EmployeeVO> myPredicate){
        List<EmployeeVO> emps = new ArrayList<>();
        for (EmployeeVO employee : employees) {
            if (myPredicate.test(employee)){
                emps.add(employee);
            }
        }

        return emps;
    }


    @Test
    public void testFilterEmployeeAge2(){
        System.out.println(testFilterEmployee(employees, new FilterEmployeeByAge()));
        System.out.println(testFilterEmployee(employees, new FilterEmployeeBySalary()));
    }


    //优化方式二: 匿名内部类
    @Test
    public void testFilterEmployeeAge3(){
        System.out.println(testFilterEmployee(employees, new MyPredicate<EmployeeVO>() {
            @Override
            public boolean test(EmployeeVO employeeVO) {
                return employeeVO.getAge() > 30;
            }
        }));
        System.out.println(testFilterEmployee(employees, new MyPredicate<EmployeeVO>() {
            @Override
            public boolean test(EmployeeVO employeeVO) {
                return employeeVO.getSalary() > 5000;
            }
        }));
    }

    //优化方式三: lambda
    @Test
    public void testFilterEmployeeAge4(){
        for (EmployeeVO employeeVO : testFilterEmployee(employees, (employee) -> employee.getAge() > 30)) {
            System.out.println(employeeVO);
        }
        System.out.println("--- --- --- --- --- ---");
        testFilterEmployee(employees, (employee) -> employee.getSalary() >5000).forEach(System.out::println);
    }


    //优化方式四: stream API
    @Test
    public void testStream(){
        employees.stream()
                //过滤
                .filter((employee) -> employee.getSalary() > 5000)
                //限制前两位
                .limit(2)
                //遍历
                .forEach(System.out::println);
        System.out.println("--- --- --- --- --- ---");
        employees.stream()
                //获取名字
                .map(EmployeeVO::getName)
                .limit(2)
                .forEach(System.out::println);
    }


}
