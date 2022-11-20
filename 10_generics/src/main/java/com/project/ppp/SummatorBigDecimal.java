package com.project.ppp;

import com.project.ppp.generics.Summator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummatorBigDecimal implements Summator<BigDecimal> {

    @Override
    public Map<String, Double> sum(Map<String, List<BigDecimal>> data) {
        Map <String, Double> result = new HashMap<>();
        BigDecimal sum2;
        for (String key: data.keySet()){
             sum2 = data.get(key).stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            result.put(key, sum2.doubleValue());
        }
        return result;
    }
}
