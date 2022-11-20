package com.project.ppp;

import com.project.ppp.reflection.Ignore;
import com.project.ppp.reflection.Info;
import com.project.ppp.reflection.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtilImpl implements ReflectionUtil {
    List<String> stringList = new ArrayList<>();
    List<String> BASE_TYPES = Arrays
            .asList("java.lang.String", "long", "int", "double", "boolean");
    String ELEMENT_START = "{";
    String ELEMENT_END = "}";
    String KEY_VALUE_SEPARATOR = " : ";
    String ELEMENT_SEPARATOR = ",";

    @Override
    public String toString(Object object) {
        if(object == null){
            return null;
        }
        stringList.add(ELEMENT_START);
        Class<?> reflectClass = object.getClass();
        Field[] fields = reflectClass.getDeclaredFields();
        List<Field> fields2 = new ArrayList<>();
        for (Field value : fields) {
            value.setAccessible(true);
            Annotation annotation1 = value.getAnnotation(Info.class);
            if (annotation1 != null) {
                fields2.add(value);
            }
        }
        fields = fields2.toArray(new Field[0]);
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue2 = null;
            try {
                fieldValue2 = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue2 == null) {
                continue;
            }
            Class<?> fieldType = field.getType();
            if (isBaseElement(fieldType)) {
                stringList.add(field.getName() + KEY_VALUE_SEPARATOR + fieldValue2);
            } else {
                stringList.add(field.getName() + KEY_VALUE_SEPARATOR);
                toString(fieldValue2);
            }
            if (field != fields[fields.length - 1]) {
                stringList.add(ELEMENT_SEPARATOR);
            }
        }
        stringList.add(ELEMENT_END);

        return stringList.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    @Override
    public boolean isTheSame(Object object1, Object object2) {
        Class claSS1 = object1.getClass();
        Class claSS2 = object2.getClass();
        Field[] field1 = claSS1.getDeclaredFields();
        Field[] field2 = claSS2.getDeclaredFields();
        List<Field> fieldList = Stream.of(field1, field2).flatMap(Stream::of).collect(Collectors.toList());
        Iterator<Field> it = fieldList.iterator();
        while (it.hasNext()) {
            Field s = it.next();
            Annotation annotation = s.getAnnotation(Ignore.class);
            if (annotation != null) {
                it.remove();
            }
        }
        Field[] fieldConcat = fieldList.toArray(new Field[0]);
        List<Boolean> res = new ArrayList<>();
        List<Boolean> res2 = new ArrayList<>();
        for (int i = 0; i < fieldConcat.length; i++) {
            fieldConcat[i].setAccessible(true);
            for (int j = i; j < fieldConcat.length; j++) {
                fieldConcat[j].setAccessible(true);
                if (fieldConcat[i].getType() == fieldConcat[j].getType() ) {
                    res.add(true);
                }
                else res.add(false);
            }
        }
        res.add(isTheSameValue(object1, object2));

        return res.stream().allMatch(el -> el);
    }

    public boolean isTheSameValue(Object object1, Object object2) {
        try {

            List<Field> fieldsFrom1 = getFields(object1);
            List<Field> fieldsFrom2 = getFields(object2);
            boolean found;
            Field field2Temp = null;
            for (Field field1 : fieldsFrom1) {
                Annotation annotation = field1.getAnnotation(Ignore.class);
                found = false;
                for (Field field2 : fieldsFrom2) {
                    Annotation annotation2 = field2.getAnnotation(Ignore.class);
                    if (!field1.get(object1).equals(field2.get(object2))&& annotation == null&& annotation2 == null) {
                            return false;
                        }
                        field2Temp = field2;
                        found = true;
                    }

                if (!found) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static List<Field> getFields(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        return new ArrayList<>(Arrays.asList(fields));
    }

    @Override
    public boolean isBaseElement(Class field) {
        String temp = field.toString();
        if (temp.contains("class")) {
            temp = temp.replace("class ", "");
        }

        return BASE_TYPES.contains(temp);
    }
}
