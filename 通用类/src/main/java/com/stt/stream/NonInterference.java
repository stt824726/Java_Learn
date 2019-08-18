package com.stt.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: Java_Learn
 * @description: 线程安全
 * @author: shaotongtong
 * @create: 2019-08-15 22:21
 **/
public class NonInterference {

    public static void main(String[] args) {
        unSafe();
    }

    /**
     * 流可以从非线程安全的集合中创建，当流的管道执行的时候，非concurrent数据源不应该被改变。
     */
    public static void unSafe(){
        List<String> list = new ArrayList(Arrays.asList("one", "two"));
        try{
            Stream<String> sl = list.stream();
            sl.forEach(item -> list.add("three"));
        }catch (Exception e){
            System.out.println("更改原数据出错");
            e.printStackTrace();
        }

        list.stream().forEach(item->item.concat("add"));
        System.out.println(list);

        try{
            list.stream().forEach(item->{
                if("two".equals(item)){
                    item.replaceAll("o","aaaaaa");
                }
            });
        }catch (Exception e){
            System.out.println("更改原数据出错");
            e.printStackTrace();
        }
        System.out.println(list);

        Stream<String> s2 = list.stream();
        s2.forEach(item->{
            if("two".equals(item)){
                list.remove(item);
            }
        });
        System.out.println(list);
    }
}
