package com.stcode.bootstrap.controller.demo;

import java.util.*;

public class RandomTest {
    private static Random random = new Random();

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        Collections.shuffle(list);

        List<String> strings = list.subList(0, 3);

        System.out.println(strings.size());

        System.out.println("-------------------------");
        System.out.println(strings.get(0));

//        int i = 0;
//        List<String> list2 = new ArrayList(list.size());
//        while (i < 2) {
//            i++;
//            //随机获取一个数
//            int num = random.nextInt(list.size());
//            //实现不重复，如果这个list集合中没有这个数就将这个数存储到list2中
//            if(!list2.contains(num)) {
//                list2.add(list.get(num));
//            }
//        }
//
//        for (String lis:list2) {
//            System.out.println(lis);
//        }


    }


}
