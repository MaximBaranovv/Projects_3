package com.project.ppp;

import com.project.ppp.basetypes.BaseTypesUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BaseTypesUtilImpl implements BaseTypesUtil {
    @Override
    public String toggleScientificNotation(String s) {
        String stroka = s;
        if (stroka.contains("e") || stroka.contains("E")){
            char[] arrStroka = stroka.toCharArray();

            for (int i = 0; i < arrStroka.length; i++) {
                if (arrStroka[i] == ',') {
                    arrStroka[i] = '.';
                }
            }
            String stroka2 = String.valueOf(arrStroka);
            BigDecimal d = new BigDecimal(stroka2);
            String strr = d.toPlainString();
            char[] primArr11 = strr.toCharArray();
            int countSixteen = 0;
            int temp = 1;
            List<Character> firstPart = new ArrayList<>();
            List<Character> secondPart = new ArrayList<>();
            for (int i = 0; i < primArr11.length; i++) {
                if (primArr11[i] == '.') {
                    for (int j = i; j < primArr11.length; j++) {
                        if (countSixteen == 16) {
                            break;
                        }
                        secondPart.add(primArr11[j]);
                        countSixteen++;
                        temp = 0;

                    }
                }
                if (temp == 0) {
                    break;
                }
                firstPart.add(primArr11[i]);
            }
            firstPart.addAll(secondPart);
            return firstPart.stream().map(Object::toString)
                    .collect(Collectors.joining(""));
        }

        else{
            String str = s;
            char[] strArray = str.toCharArray();
            int count = -1;

            for (int i = 0; i < strArray.length; i++) {
                if(strArray[i] == ','){
                    strArray[i] = '.';
                }
            }

            String str2 = String.valueOf(strArray);
            if(strArray[0] == '0'){
                for (int i = strArray.length-1; i > 0; i--) {
                    if (strArray[i] == '0'){
                        break;
                    }
                    count++;
                }
                count = -1;
                for (int i = strArray.length-1; i > 0 ; i--) {
                    if (strArray[i] == '.'){
                        break;
                    }
                    count++;
                }
            }
            else {
                for (char c : strArray) {
                    if (c == '.') {
                        continue;
                    }
                    count++;
                }
            }
            int cCount = 0;
            List<Character> two = new ArrayList<>();
            List<Character> sixteen = new ArrayList<>();
            List<Character> theeOrTwo = new ArrayList<>();
            String prim = format(new BigDecimal(str2), count);
            char[] primArr = prim.toCharArray();
            two.add(primArr[0]);
            two.add(primArr[1]);
            for (int i = 2; i < primArr.length ; i++) {
                if(primArr[i] == 'E'){
                    for (int j = i; j < primArr.length; j++) {
                        theeOrTwo.add(primArr[j]);
                    }
                }
            }
            for (int i = 2; i < primArr.length; i++) {
                if(primArr[i] == 'E'){break;}
                if(cCount == 16){
                    break;
                }
                sixteen.add( primArr[i]);
                cCount++;
            }
            List<Character> characterList = new ArrayList<>();
            characterList.addAll(two);
            characterList.addAll(sixteen);
            characterList.addAll(theeOrTwo);
            return characterList.stream().map(Object::toString)
                    .collect(Collectors.joining(""));
        }
    }

    @Override
    public int[] sort(int[] ints) {
        int t = 0;
        int [] array = ints.clone();
        for (int i = 0; i < ints.length; i++) {
            for (int j = i+1; j < ints.length; j++) {
                if (array[i] > array[j]){
                    t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
            }
        }
        return array;
    }

    @Override
    public float arithmeticMean(int[] ints) {
        float length = ints.length;
        int common = 0;
        for (int i = 0; i < ints.length; i++) {
            common +=ints[i];
        }
        float result = common/length;
        return result;
    }

    @Override
    public String format(double v, String s) {
        String res = null;
        String[] languages = Locale.getISOLanguages();
        int length = s.length();
        Map<String, Locale> localeMap = new HashMap<String, Locale>(languages.length);
        for (String language : languages) {
            Locale locale = new Locale(language);
            localeMap.put(locale.getISO3Language(), locale);
        }
        if (length == 3){
            for (String name: localeMap.keySet()) {
                if(name.equals(s)){
                    res = localeMap.get(name).toString();
                }
            }
            NumberFormat numberFormat1 = NumberFormat.getInstance(Locale.forLanguageTag(res));
            return numberFormat1.format(v);
        }
        else {
            res = s;
            NumberFormat numberFormat1 = NumberFormat.getInstance(Locale.forLanguageTag(res));
            return numberFormat1.format(v);
        }
    }

    @Override
    public String plus(String s, String s1) {
        String newS = s;
        String newS1 = s1;
        if(s.contains(",") | s1.contains(",")) {
            newS = s.replace(",", ".");
            newS1 = s1.replace(",", ".");
        }
        BigDecimal number = new BigDecimal(newS);
        BigDecimal result = number.add(new BigDecimal(newS1));
        String resString = "" + result;
        return resString;
    }

    @Override
    public String minus(String s, String s1) {
        String newS = s;
        String newS1 = s1;
        if(s.contains(",") | s1.contains(",")) {
            newS = s.replace(",", ".");
            newS1 = s1.replace(",", ".");
        }
        BigDecimal number = new BigDecimal(newS);
        BigDecimal result = number.subtract(new BigDecimal(newS1));
        String resString = "" + result;
        return resString;
    }

    @Override
    public String mul(String s, String s1) {
        String newS = s;
        String newS1 = s1;
        if(s.contains(",") | s1.contains(",")) {
            newS = s.replace(",", ".");
            newS1 = s1.replace(",", ".");
        }
        BigDecimal number = new BigDecimal(newS);
        BigDecimal result = number.multiply(new BigDecimal(newS1));
        String resString = "" + result;
        return resString;
    }

    @Override
    public String div(String s, String s1) {
        String newS = s;
        String newS1 = s1;
        if(s.contains(",") | s1.contains(",")) {
            newS = s.replace(",", ".");
            newS1 = s1.replace(",", ".");
        }
        BigDecimal number = new BigDecimal(newS);
        BigDecimal result = number.divide(new BigDecimal(newS1));
        String resString = "" + result;
        return resString;
    }
    private static String format(BigDecimal x, int scale) {
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(scale);
        return formatter.format(x);
    }
}

