package com.project.ppp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringUtilsImpl stringUtils = new StringUtilsImpl();
//      System.out.println(stringUtils.fromCamelCase("HelloJavaWorld"));
//        System.out.println(stringUtils.toCamelCase("HeLlO jAvA, WoRlD"));
//        System.out.println(stringUtils.alphabet());
//        System.out.println(stringUtils.convertIp(" 1.1.1.  1"));

//       ftp://Maksim:Baranov@1.2.3.4:25/pass0/pass1/pass2?a=&b=2#anchor
//        System.out.println(Arrays.toString(stringUtils.uri2Array("ftp://Maksim:@1.2.3.4:25/pass0/pass1/pass2?a=&b=2#")));
//        System.out.println(Arrays.toString(stringUtils.ip2Bytes("127.0.0.1")));
//        System.out.println(stringUtils.toCamelCase("HeLlO, jAvA, WoRlD"));
//        System.out.println(Arrays.toString(stringUtils.uri2Array("ftp://Maksim:@1.2.3.4:25/pass0/pass1/pass2#")));
//          System.out.println(Arrays.toString(stringUtils.uri2Array("ftp://Username:Password@1.2.3.4:25/pass0/pass1/pass2?a=&b=2#anchor")));
          System.out.println(Arrays.toString(stringUtils.uri2Array("ftp://1231/pass0/pass1/pass2?a=&b=2#anchor")));;
//        System.out.println(stringUtils.toCamelCase("hKd"));
//        System.out.println(Arrays.toString(stringUtils.ip2Bytes("127.0.0.255.1")));
//        System.out.println(stringUtils.fromCamelCase("hJW"));
    }
}
