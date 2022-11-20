package com.project.ppp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SaverImpl saverImpl = new SaverImpl();
        String str = "MESSAGE: hello";
        OneMessageFileLoggerImpl oneMessageFileLogger = new OneMessageFileLoggerImpl("hello.txt", saverImpl);
        try {
            oneMessageFileLogger.log(str);
        } catch (MessageIsNotFormattedException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}
