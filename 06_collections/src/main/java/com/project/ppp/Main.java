package com.project.ppp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
//        list.add("hi");
//        list.add("hi");
//        list.add("priv");
//        list.clear();



        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(3);
//        System.out.println(list1.addAll(list));
        System.out.println(list1);
        DefaultCollectionImpl<Integer> defaultCollection = new DefaultCollectionImpl<>();
        defaultCollection.add(2);
        defaultCollection.add(4);
        System.out.println(defaultCollection.addAll(list1));
        System.out.println(defaultCollection.size());
        System.out.println(defaultCollection);
    }
}
