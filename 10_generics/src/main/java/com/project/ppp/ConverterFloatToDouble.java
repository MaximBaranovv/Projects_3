package com.project.ppp;

import com.project.ppp.generics.Converter;

public class ConverterFloatToDouble implements Converter<Double, Float> {

    @Override
    public Double get(Float value) {
        return Double.valueOf(String.valueOf(value));
    }
}
