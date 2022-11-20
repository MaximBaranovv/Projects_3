package com.project.ppp;

public final class Marker extends Writable {
    private float core;
    private static final float CONST_1 = 1f;
    private static final float CONST_2 = 1.09f;
    private static final float CONST_3 = 1.21f;
    private int count;

    public Marker() {
       this.core = 100;
    }

    @Override
    public void write(StringBuilder stringBuilder, char[] chars) {
        float length1 = chars.length * CONST_1;
        float length2 = chars.length * CONST_2;
        float length3 = chars.length * CONST_3;
        stringBuilder = new StringBuilder(String.valueOf(chars));
        count = count + chars.length;
        if(count <=20 ){
            core = core - length1;
        }
        else if (count <= 40){
            core = core - length2;
        }
        else {
            core = core - length3;
        }
        System.out.println("After writing a " +"'" + stringBuilder +"'" + " by a marker, left: " + core +"%");
    }

    @Override
    public void erase(StringBuilder stringBuilder) {
    }

    @Override
    public String toString() {
        return "Marker{" +
                "core=" + core +
                ", count=" + count +
                '}';
    }

    @Override
    public float getCore() {
        return this.core;
    }

    @Override
    public String getName() {
        return "Marker";
    }
}
