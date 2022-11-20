package com.project.ppp;

public final class Pencil extends Writable {
    private float core;
    private int sharpness;
    private int count;
    private static final float CONST = 0.95f;

    public Pencil() {
        this.core = 100;
        this.sharpness = 100;
    }

    @Override
    public void write(StringBuilder stringBuilder, char[] chars) {
        stringBuilder = new StringBuilder(String.valueOf(chars));
        int length = chars.length;
        core = core - length;
        if(count >= 20){
            sharpness = sharpness - 3;
            count = 0;
        }
        System.out.println("After writing a " +"'" + stringBuilder +"'" + " by a pencil, left: " + core +"%" + " and it`s sharpness is: " + sharpness);
        erase(stringBuilder);
        System.out.println("And after erase it became: " + "'" + stringBuilder + "'");
        count += stringBuilder.length();
    }

    @Override
    public void erase(StringBuilder stringBuilder) {
        if( stringBuilder.length() > 0 )
            stringBuilder.setLength( stringBuilder.length() - 1 );
    }

    @Override
    public String getName() {
        return "Pencil";
    }

    @Override
    public float getCore() {
        return this.core;
    }

    public String toString() {
        return "Pencil{" +
                "core=" + core +
                ", sharpness=" + sharpness +
                '}';
    }
}
