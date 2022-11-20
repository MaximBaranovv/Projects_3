package com.project.ppp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BaseTypesUtilImpl baseTypesUtil = new BaseTypesUtilImpl();
        int [] array = {4,2,1,321,3,21,213};
        System.out.println(Arrays.toString(baseTypesUtil.sort(array)));
        System.out.println(baseTypesUtil.arithmeticMean(array));
        System.out.println(baseTypesUtil.format(1221.1312, "ar"));
        System.out.println(baseTypesUtil.toggleScientificNotation("12213.13"));
        System.out.println(baseTypesUtil.toggleScientificNotation("1,221313E4"));
        System.out.println(baseTypesUtil.div("4,0", "2"));

    }
}
