package com.project.ppp;

public final class Pen extends Writable {

    private float core;
    private static final float CONST = 1.15f;

    public Pen() {
        this.core = 100;
    }

    @Override
    public void write(StringBuilder stringBuilder, char [] chars) {
        float length = chars.length * CONST;
        stringBuilder = new StringBuilder(String.valueOf(chars));
        core = core - length;
        System.out.println("After writing a " +"'" + stringBuilder +"'" + " by a pen, left: " + core +"%");

    }

    @Override
    public void erase(StringBuilder stringBuilder) {

    }

    @Override
    public String toString() {
        return "Pen{" +
                "core=" + core +
                '}';
    }
    @Override
    public float getCore() {
        return this.core;
    }
    @Override
    public String getName() {
        return "Pen";
    }

}
