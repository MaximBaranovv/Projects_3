package com.project.ppp;

import com.project.ppp.io.NIOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIOUtilsImpl implements NIOUtils {

    @Override
    public String searchText(Path file, int offset) throws IllegalArgumentException {
        String res = "";
        try {
            byte[] array = Files.readAllBytes(file);
            for (int i = offset; i < array.length; ) {
                StringBuilder stringBuilder = new StringBuilder();
                char c = (char) array[i];
                if (c == '-' || Character.isDigit(c)) {
                    for (int j = i; j < array.length && array[j] != ' '; j++) {
                        c = (char) array[j];
                        stringBuilder.append(c);
                    }
                    i += Integer.parseInt(stringBuilder.toString());
                } else {
                    res = String.valueOf(c);
                    return res;
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return res;
    }

    @Override
    public String[] search(Path folder, String ext) throws IllegalArgumentException {
        List<String> stringList = null;
        if (ext != null) {
            try (Stream<Path> walk = Files.walk(folder)) {
                stringList = walk
                        .filter(p -> !Files.isDirectory(p))
                        .map(Path::toString)
                        .filter(f -> f.endsWith(ext))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        } else {
            try (Stream<Path> paths = Files.walk(folder)) {
                stringList = paths
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }

        return stringList.toArray(new String[0]);
    }
}
