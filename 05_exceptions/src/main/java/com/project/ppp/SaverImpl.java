package com.project.ppp;

import com.project.ppp.exceptions.Saver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;

public class SaverImpl implements Saver {

    @Override
    public void save(String text, String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new FileSystemAlreadyExistsException();
        }
    }
}
