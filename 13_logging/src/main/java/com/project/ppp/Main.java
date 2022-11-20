package com.project.ppp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SaverImpl saverImpl = new SaverImpl();
        String str = "MESSAGE: hello";
        OneMessageFileLoggerImpl oneMessageFileLogger = new OneMessageFileLoggerImpl("hello.txt", saverImpl);
        try {
            oneMessageFileLogger.log(str);
        } catch (MessageIsNotFormattedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
