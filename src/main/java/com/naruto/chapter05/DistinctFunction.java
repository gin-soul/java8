package com.naruto.chapter05;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.naruto.chapter05.vo.HouseInfo;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author gin
 * @date 2020/1/19 11:22
 */
public class DistinctFunction {

    @Test
    public void strListDistinct() {
        //对于 List<String> 列表去重
        List<String> strList = new ArrayList<String>() {{
            add("A");
            add("A");
            add("B");
            add("C");
            add("C");
        }};
        System.out.print("去重前：");
        for (String s : strList) {
            System.out.print(s);
        }
        System.out.println();
        strList = strList.stream().distinct().collect(Collectors.toList());
        System.out.print("去重后：");
        for (String s : strList) {
            System.out.print(s);
        }
        System.out.println();
    }


    @Test
    public void listDistinctByStreamDistinct() {
        //对于 整个对象 列表去重
        List<HouseInfo> houseList = initList();
        System.out.print("去重前：");
        System.out.println(JSONObject.toJSONString(houseList));
        houseList = houseList.stream().distinct().collect(Collectors.toList());
        System.out.print("去重后：");
        System.out.println(JSONObject.toJSONString(houseList));
    }

    private List<HouseInfo> initList() {
        List<HouseInfo> houseInfos = new LinkedList<>();
        HouseInfo houseInfo1 = new HouseInfo();
        houseInfo1.setLiveAddr("addr1");
        houseInfo1.setLiveCondition("1");
        houseInfo1.setUpdateDate("2018-10-01");
        houseInfos.add(houseInfo1);

        HouseInfo houseInfo2 = new HouseInfo();
        houseInfo2.setLiveAddr("addr2");
        houseInfo2.setLiveCondition("2");
        houseInfo2.setUpdateDate("2018-09-22");
        houseInfos.add(houseInfo2);

        HouseInfo houseInfo3 = new HouseInfo();
        houseInfo3.setLiveAddr("addr1");
        houseInfo3.setLiveCondition("2");
        houseInfo3.setUpdateDate("2018-11-22");
        houseInfos.add(houseInfo3);

        HouseInfo houseInfo4 = new HouseInfo();
        houseInfo4.setLiveAddr("addr1");
        houseInfo4.setLiveCondition("1");
        houseInfo4.setUpdateDate("2018-10-01");
        houseInfos.add(houseInfo4);
        return houseInfos;
    }


    @Test
    public void distinctByProperty1() {
        //第一种方法我们通过新创建一个只有不同元素列表来实现根据对象某个属性去重
        List<HouseInfo> houseList = initList();

        System.out.print("去重前: ");
        System.out.println(JSONObject.toJSONString(houseList));
        houseList = houseList.stream().distinct().collect(Collectors.toList());
        System.out.print("distinct去重后: ");
        System.out.println(JSONObject.toJSONString(houseList));
        // 这里我们引入了两个静态方法，以及通过 TreeSet<> 来达到获取不同元素的效果
        houseList = houseList.stream()
                .sorted(Comparator.comparing(e -> ((HouseInfo)e).getUpdateDate(), Comparator.nullsFirst(String::compareTo)).reversed())
                .collect(
                // java.util.stream.Collectors.collectingAndThen
                collectingAndThen(
                        // java.util.stream.Collectors.toCollection
                        //(s1, s2) -> s1.compareToIgnoreCase(s2)
                        toCollection(() -> new TreeSet<>(Comparator.comparing(e -> e.getLiveAddr()))), ArrayList::new)
                );
        System.out.print("treeSet去重后: ");
        System.out.println(JSONObject.toJSONString(houseList));
    }


    @Test
    public void distinctByProperty2() {
        //第二种方法通过过滤来实现根据对象某个属性去重
        List<HouseInfo> houseList = initList();
        System.out.print("去重前: ");
        System.out.println(JSONObject.toJSONString(houseList));
        houseList = houseList.stream().distinct().collect(Collectors.toList());
        System.out.print("distinct去重后: ");
        System.out.println(JSONObject.toJSONString(houseList));
        //将 distinctByKey() 方法作为 filter() 的参数(Predicate: true,false)，过滤掉那些不能加入到 set 的元素
        houseList = houseList.stream()
                .sorted(Comparator.comparing(e -> ((HouseInfo)e).getUpdateDate(), Comparator.nullsFirst(String::compareTo)).reversed())
                .filter(distinctByKey(HouseInfo::getLiveAddr))
                .collect(Collectors.toList());
        System.out.print("filter去重后: ");
        System.out.println(JSONObject.toJSONString(houseList));
    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        //先加入为true,后加入为false
        return t -> seen.add(keyExtractor.apply(t));
    }


    private Comparator<HouseInfo> getHouseInfoComparator(Function<HouseInfo, String> function) {
        return (s1, s2) -> {
            if(s1 == null && s2 != null){
                return 1;
            }else if(s1 !=null && s2 == null){
                return -1;
            }else if(s1 == null && s2 == null){
                return -1;
            }else {
                String time1 = function.apply(s1);
                String time2 = function.apply(s2);
                if(time1 == null && time2 != null){
                    return 1;
                }else if(time1 !=null && time2 == null){
                    return -1;
                }else if(time1 == null && time2 == null){
                    return -1;
                }else {
                    //降序
                    return time2.compareToIgnoreCase(time1);
                }
            }
        };
    }

}
