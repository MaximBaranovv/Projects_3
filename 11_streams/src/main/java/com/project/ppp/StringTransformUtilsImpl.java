package com.project.ppp;

import com.project.ppp.streamoptional.StringTransformUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class StringTransformUtilsImpl implements StringTransformUtils {

    @Override
    public List<String> findDistinctToUpperCase(String[] strings) {
        return stream(strings).distinct().map(el -> el.toUpperCase(Locale.ROOT)).collect(Collectors.toList());
    }

    @Override
    public long countNegativeIntegers(List<Integer> integers) {
        return integers.stream().filter(el-> el<0).count();
    }

    @Override
    public long countWordsInList(List<String> words, String wordToCount) {
        return words.stream().filter(el -> el.equals(wordToCount)).count();
    }

    @Override
    public List<String> toDistinctList(List<String[]> stringsArrays) {
        return stringsArrays.stream().flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    @Override
    public boolean isAllStringsLongerThen(List<String> strings, long numberOfCharacters) {
        if(numberOfCharacters<0){
            throw new IllegalArgumentException();
        }
        else if(strings.stream().anyMatch(el -> el.length()<=numberOfCharacters) ){
            return false;
        }
        return true;
    }

    @Override
    public List<Integer> getMaxFiveNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.comparingInt(a -> a))
                .skip(numbers.length - 5)
                .collect(Collectors.toList());
    }

    @Override
    public String getStringOfNumbers(int[] numbers) {
        return stream(numbers)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a.concat(",").concat(b))
                .orElse("");
    }

    @Override
    public String getFirstCharactersAsString(List<String> strings) {
        return strings.stream().map(el -> el.charAt(0)).map(Object::toString).collect(Collectors.joining(""));
    }

    @Override
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        return strings.stream().collect(Collectors.groupingBy(String::length));
    }
}
