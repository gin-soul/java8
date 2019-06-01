package com.naruto.chapter07;

import com.naruto.chapter01.em.Status;
import com.naruto.chapter01.vo.EmployeeVO;
import com.naruto.chapter07.vo.Trader;
import com.naruto.chapter07.vo.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _012StreamPractice {
    /**
     * 给定一个数字列表,如何返回一个由每个数的平方构成的列表
     * 给定[1, 2, 3, 4, 5]  返回 [1, 4, 9, 16, 25]
     */
    @Test
    public void testNum() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        numList.stream()
                .map(x -> x * x)
                .forEach(System.out::println);
    }


    List<EmployeeVO> employees = Arrays.asList(
            new EmployeeVO("张三", 18, 9999.99, Status.BUSY),
            new EmployeeVO("李四", 38, 5555.99, Status.FREE),
            new EmployeeVO("王五", 50, 6666.66, Status.FREE),
            new EmployeeVO("赵六", 16, 3333.33, Status.FREE),
            new EmployeeVO("田七", 8, 7777.77, Status.BUSY)
    );

    /**
     * 怎样用 map 和 reduce 方法数一数流中有多少个 Employee
     */
    @Test
    public void testCount() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> total = numList.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(total);
    }


    List<Transaction> transactions;

    @Before
    public void beforeTest() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易,并按交易额排序(从低到高)
    @Test
    public void testTransaction() {
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }


    //2. 交易员都在哪些不同的城市工作过
    @Test
    public void testTransaction2() {
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

    }

    //3.查找所有来自剑桥的交易员,并按姓名排序
    @Test
    public void testTransaction3() {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getTrader)
                //(t1, t2) -> t1.getName().compareTo(t2.getName())
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
    }

    //4.返回所有交易员的姓名字符串(拼接成串),按字母顺序排序
    @Test
    public void testTransaction4() {
        String sortedName = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                //(t1, t2) -> t1.getName().compareTo(t2.getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining());
        System.out.println(sortedName);

        System.out.println(" - - - - - - ");

        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .flatMap(_012StreamPractice::filterCharater)
                //(s1, s2) -> s1.compareToIgnoreCase(s2)
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::print);

    }

    public static Stream<String> filterCharater(String str) {
        List<String> characters = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characters.add(character.toString());
        }
        return characters.stream();
    }


    //5.有没有交易员是在米兰工作的?
    @Test
    public void testTransaction5() {
        boolean anyMatch = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));

        System.out.println(anyMatch);
    }


    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void testTransaction6() {
        Optional<Integer> sum = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum);
    }

    //7.所有交易中,最高的交易额是多少
    @Test
    public void testTransaction7() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                //(t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())
                .max(Integer::compare);

        System.out.println(max.get());
    }

    //8.所有交易中,交易额最小的交易
    @Test
    public void testTransaction8() {
        Optional<Transaction> max = transactions.stream()
                //(t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())
                .min(Comparator.comparingInt(Transaction::getValue));

        System.out.println(max.get());
    }


}
