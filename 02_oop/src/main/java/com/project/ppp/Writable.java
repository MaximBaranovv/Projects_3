package com.project.ppp;

public abstract class Writable {
    public abstract String getName();
    public abstract float getCore();
    public abstract void write(StringBuilder stringBuilder, char[] chars);
    public abstract void erase(StringBuilder stringBuilder);
    
}
