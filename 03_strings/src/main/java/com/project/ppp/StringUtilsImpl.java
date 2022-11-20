package com.project.ppp;

import com.project.ppp.strings.StringUtils;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtilsImpl implements StringUtils {
    private static final String SPACE = " ";
    private static final String BLANK = "";
    @Override
    public byte[] ip2Bytes(String ip) {
        char [] chars = ip.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if( Character.isLetter(chars[i])){
                return null;
            }
        }
        String ipCopy = ip.replace(" ", "");
        String[] array1 = ipCopy.split("\\.");
        if(array1.length > 4){
            return null;
        }
        byte[] arrayBytes = new byte[4];
        for (int i = 0; i < array1.length; i++) {
            if(array1[i].contains("[a-zA-Z]+")){
                return null;
            }
            arrayBytes[i] = (byte) Integer.parseInt(array1[i]);
            if( Integer.parseInt(array1[i]) > 255 || Integer.parseInt(array1[i] )<0){
                return null;
            }
        }
        return arrayBytes;
    }

    @Override
    public String convertIp(String ip) {
        String finalRes = null;
        String ipCopy = ip.replace(" ", "");
        String ip2 = ipCopy.replace('.', '-');
        Pattern twopart = Pattern.compile("(\\d+)-(\\d+)-(\\d+)-(\\d+)");
        Matcher m = twopart.matcher(ip2);
        if(m.matches()) {
            String oneZero = "0";
            String twoZeros = "00";
            String p1 = m.group(1);
            int pInt1 = Integer.parseInt(p1);
            String p2 = m.group(2);
            int pInt2 = Integer.parseInt(p2);
            String p3 = m.group(3);
            int pInt3 = Integer.parseInt(p3);
            String p4 = m.group(4);
            int pInt4 = Integer.parseInt(p4);
            if(pInt1 > 255 || pInt2 > 255 || pInt3 > 255 ||pInt4 > 255){
                return null;
            }
            List<String> strings = new ArrayList<>();
            if (p1.length() == 1) {
                String pP1 = twoZeros + p1;
                strings.add(pP1);
            }
            if (p1.length() == 2) {
                String pP1 = oneZero + p1;
                strings.add(pP1);
            }
            if (p1.length() == 3) {
                strings.add(p1);
            }
            if (p2.length() == 1) {
                String pP2 = twoZeros + p2;
                strings.add(pP2);
            }
            if (p2.length() == 2) {
                String pP2 = oneZero + p2;
                strings.add(pP2);
            }
            if (p2.length() == 3) {
                strings.add(p2);
            }
            if (p3.length() == 1) {
                String pP3 = twoZeros + p3;
                strings.add(pP3);
            }
            if (p3.length() == 2) {
                String pP3 = oneZero + p3;
                strings.add(pP3);
            }
            if (p3.length() == 3) {
                strings.add(p3);
            }

            if (p4.length() == 1) {
                String pP4 = twoZeros + p4;
                strings.add(pP4);
            }
            if (p4.length() == 2) {
                String pP4 = oneZero + p4;
                strings.add(pP4);
            }
            if (p4.length() == 3) {
                strings.add(p4);
            }
            List<Character> result =
                    strings.stream()
                            .flatMapToInt(String::chars)
                            .mapToObj(i -> (char) i)
                            .collect(Collectors.toList());
            result.add(3, '.');
            result.add(7, '.');
            result.add(11, '.');
            StringBuilder sb = new StringBuilder();
            for (Character ch : result) {
                sb.append(ch);
            }
            finalRes = sb.toString();
        }
        return finalRes;
    }

    @Override
    public StringBuilder alphabet() {
        List<Character> characterList = new ArrayList<>(Arrays.asList(' ', 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
        for (int i = 1; i < 27; i++) {
            if( i%2 == 0){
                characterList.set(i, Character.toUpperCase(characterList.get(i)));
            }
        }
        characterList.remove(0);
        String res = characterList.stream().map(Object::toString)
                .collect(Collectors.joining(""));
        StringBuilder stringBuilder = new StringBuilder(res);

        return stringBuilder;
    }

    @Override
    public String[] uri2Array(String uri) {
        String [] result = new String[8];
        List<Integer> indexesOfSlash = IntStream.range(0, uri.length())
                .filter(i -> uri.charAt(i) == '/').boxed()
                .collect(Collectors.toList());
        List<Integer> indexesOfDv = IntStream.range(0, uri.length())
                .filter(i -> uri.charAt(i) == ':').boxed()
                .collect(Collectors.toList());
        int indexOfAnchor = uri.indexOf("#");
        int indexOfDog = uri.indexOf("@");
        int indexOfVopr = uri.indexOf("?");
        String schema = uri.substring(0, indexesOfDv.get(0));
        //проверка на [0] элемент
        if(schema.length() != 0){
            result[0] = schema;
        }
        else {
            result[0] = null;
        }
        //проверка на логин пароль
        if (indexOfDog > 0) {
            //значит есть логин пароль
            String login = uri.substring(indexesOfSlash.get(1)+1, indexesOfDv.get(1) );
            result[1] = login;
            String password = uri.substring( indexesOfDv.get(1)+1, indexOfDog);

            result[2] = password;

        }
        else {
            //нету логина пароля
            result[1] = null;
            result[2] = null;

        }
        //на host
        if(indexOfDog >0&& indexesOfDv.size() ==3){
            String host = uri.substring(indexOfDog+1, indexesOfDv.get(2));
            if(host.length() != 0){
            result[3] = host;
            }
            else {
                result[3] = null;
            }
        }
        else if(indexOfDog > 0&&indexesOfDv.size() ==2){
            String host = uri.substring(indexOfDog+1, indexesOfSlash.get(2));
            if(host.length() != 0){
                result[3] = host;
            }
            else {
                result[3] = null;
            }
        }
        else if(indexOfDog<0&& indexesOfDv.size() ==2){
            String host = uri.substring(indexesOfSlash.get(1)+1, indexesOfDv.get(1));
            if(host.length() != 0){
                result[3] = host;
            }
            else {
                result[3] = null;
            }
        }
        else if(indexOfDog<0&& indexesOfDv.size() ==1){
            String host = uri.substring(indexesOfSlash.get(1)+1, indexesOfSlash.get(2));
            if(host.length() != 0){
                result[3] = host;
            }
            else {
                result[3] = null;
            }
        }
        // на port
        if(indexesOfDv.size() ==3 ){
            String port = uri.substring(indexesOfDv.get(2)+1, indexesOfSlash.get(2));
            result[4] = port;
        }
        else if(indexesOfDv.size() ==2 && indexOfDog <0 ){
            String port = uri.substring(indexesOfDv.get(1)+1, indexesOfSlash.get(2));
            result[4] = port;
        }
        else {
            result[4] = null;
        }
        //проверка на path
        if(indexesOfSlash.size() >3){
            // значит есть path
            if(indexOfVopr > 0){
                // Если у нас есть вопрс знак значит идем до него
                String path = uri.substring(indexesOfSlash.get(2)+1, indexOfVopr);
                result[5] = path;
            }
            else if (indexOfVopr < 0 && indexOfAnchor > 0){
                // если нету вопр знака но есть якорь
                String path = uri.substring(indexesOfSlash.get(2)+1, indexOfAnchor);
                result[5] = path;
            }
            else{
                // если нету ни якоря ни вопр знака
                String path = uri.substring(indexesOfSlash.get(2)+1);
                result[5] = path;
            }
        }
        else {
            //если нету path
            result[5] = null;
        }
        //проверка на параметры
        if(indexOfVopr >0){
            //значит у нас есть параметры
            if(indexOfAnchor > 0){
                //значит есть якорь
                String params = uri.substring(indexOfVopr+1 , indexOfAnchor);
                result[6] = params;
            }
            else {
                //нету якоря
                String params = uri.substring(indexOfVopr+1 );
                result[6] = params;
            }
        }
        else {
            result[6] = null;
        }
        //на якорь
        if(indexOfAnchor > 0){
            //якорь есть
            String anchor = uri.substring(indexOfAnchor+1);
            result[7] = anchor;
        }
        else {
            result[7] = null;
        }
        return result;
    }

    @Override
    public String toCamelCase(String str) {
        if (str != null && !str.isBlank()) {
            String str2 = str.trim();
        String first = str2.substring(0, 1);
        String[] array1 = str2.split("[, ]+");
        for (int i = 0; i < array1.length; i++) {
            array1[i] = array1[i].toLowerCase();
            String temp = array1[i].substring(0, 1).toUpperCase() + array1[i].substring(1);
            array1[i] = temp;
        }
        String res = String.join(BLANK, array1);
        return first.concat(res.substring(1));
        }
        return BLANK;
    }

    @Override
    public String fromCamelCase(String camelStr) {
        if (camelStr != null && !camelStr.isBlank()) {
            var result = "";
            boolean isFirst = true;
            var characters = camelStr.trim().toCharArray();
            for (int i = 0; i < characters.length; i++) {
                if (Character.isUpperCase(characters[i])) {
                    if (isFirst && i == 0) {
                        result = result.concat(SPACE).concat(String.valueOf(characters[i]));
                        isFirst = false;
                        continue;
                    } else {
                        result = result.concat(SPACE).concat(String.valueOf(characters[i]).toLowerCase());
                        continue;
                    }
                }
                result = result.concat(String.valueOf(characters[i]));
            }

            return result.trim();
        }

        return BLANK;
    }
}

