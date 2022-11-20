package com.project.ppp;

public class MessageIsNotFormattedException extends Exception {

    public MessageIsNotFormattedException(String message) {
        super("You have written the incorrect message: " + message);
    }
}
