package com.project.ppp;

import com.project.ppp.exceptions.Saver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;

public class SaverImpl implements Saver {

    static final Logger log = LogManager.getRootLogger();

    @Override
    public void save(String text, String fileAbsolutePath) {
        log.traceEntry();
        File file = new File(fileAbsolutePath);
        log.debug("File is created");
        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(text);
                log.info("Successful write to the file");
            } catch (IOException e) {
                log.warn("file write error");
                throw new RuntimeException(e);
            }
        } else {
            log.warn("File already exists");
            throw log.throwing(new FileSystemAlreadyExistsException());
        }
    }
}