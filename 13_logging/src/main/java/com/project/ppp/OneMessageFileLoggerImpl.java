package com.project.ppp;

import com.project.ppp.exceptions.OneMessageFileLogger;
import com.project.ppp.exceptions.Saver;

import java.io.IOException;

public class OneMessageFileLoggerImpl implements OneMessageFileLogger {

    private static final String MESSAGE_STARTS_WITH = "MESSAGE: ";
    private String path;
    private Saver saver;

    public OneMessageFileLoggerImpl() {
    }

    public OneMessageFileLoggerImpl(String path, Saver saver) {
        this.path = path;
        this.saver = saver;
    }

    @Override
    public void log(String message) throws MessageIsNotFormattedException, IOException {
        if (!message.startsWith(MESSAGE_STARTS_WITH)) {
            throw new MessageIsNotFormattedException(message);
        }
        saver.save(message, path);
        saver.save(message, path);
    }
}
