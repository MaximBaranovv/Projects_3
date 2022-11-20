package com.project.ppp;

import com.project.ppp.generics.Converter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ConverterIntegerToString implements Converter<String, Integer[]> {

    @Override
    public String get(Integer [] value) {
        int[] array = Arrays.stream(value).mapToInt(Integer::intValue).toArray();
        return String.join(" ", IntStream.of(array).mapToObj(String::valueOf).toArray(String[]::new));
    }
}
