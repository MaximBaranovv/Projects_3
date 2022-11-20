package com.project.ppp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        String[] str = {"foo", "bar", "foo", "baz"};
        StringTransformUtilsImpl stringTransformUtils = new StringTransformUtilsImpl();
//        System.out.println(stringTransformUtils.findDistinctToUpperCase(str));
//        List <Integer> integers = new ArrayList<>(Arrays.asList(12,-2,2,5,-1,-1,-12));
//        System.out.println(stringTransformUtils.countNegativeIntegers(integers));
        List <String> strings = new ArrayList<>(Arrays.asList("twwe", "toww", "dww"));
//        System.out.println(stringTransformUtils.countWordsInList(strings, "foo"));
//        String[] el1 = {"foo", "bar", "baz"};
//        String[] el2 = {"foo", "bar", "fuz"};
//        List<String[]> strings1 = new ArrayList<>(Arrays.asList(el1,el2));
//        List<String> result = stringTransformUtils.toDistinctList(strings1);
//        System.out.println(result);
        System.out.println(stringTransformUtils.isAllStringsLongerThen(strings, 3));

          int[] ints = {};
//        System.out.println(stringTransformUtils.getMaxFiveNumbers(ints));
        System.out.println(stringTransformUtils.getStringOfNumbers(ints));
//        System.out.println(stringTransformUtils.getFirstCharactersAsString(strings));
//        List <String> strings2 = new ArrayList<>(Arrays.asList( "Irene", "Wendy", "Seulgi", "Joy", "Yeri", "Red", "Velvet"));
//        System.out.println(stringTransformUtils.groupByLength(strings2));



    }
}
