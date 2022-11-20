package com.project.ppp;

import com.project.ppp.serializable.SingletonBean;

public class SingletonBeanImpl implements SingletonBean {

    private static final SingletonBean INSTANCE = new SingletonBeanImpl();

    private SingletonBeanImpl() {
    }

    public static SingletonBean getInstance() {
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}
