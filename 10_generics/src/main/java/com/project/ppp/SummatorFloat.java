package com.project.ppp;

import com.project.ppp.generics.Summator;

import java.util.*;

public class SummatorFloat implements Summator <Float> {

    @Override
    public Map<String, Double> sum(Map<String, List<Float>> data) {
        Map <String, Double> result = new LinkedHashMap<>();
        double sum2;
        for (String key: data.keySet()){
            sum2 = data.get(key).stream().mapToDouble(a -> a).sum();
            result.put(key, sum2);
        }
        return result;
    }
}