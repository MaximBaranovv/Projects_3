package com.project.ppp;

import com.project.ppp.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.zip.GZIPOutputStream;

public class IOUtilsImpl implements IOUtils {
    List<String> stringList = new ArrayList<>();

    public IOUtilsImpl() {
    }

    @Override
    public String gzip(String fileName, String folder) throws IllegalArgumentException {
        var filePath = Paths.get(fileName);
        var folderPath = Paths.get(folder);
        if (Files.notExists(filePath)) {
            throw new IllegalArgumentException("Can't find the resource file: ".concat(fileName));
        }
        if (Files.notExists(folderPath)) {
            throw new IllegalArgumentException("Can't find the specified folder: ".concat(folder));
        }
        if (!Files.isDirectory(folderPath)) {
            throw new IllegalArgumentException("It's must be folder: ".concat(folder));
        }
        var gzipFile = filePath.getFileName().toString();
        gzipFile = gzipFile.substring(0, gzipFile.lastIndexOf("."));
        var result = folderPath.toAbsolutePath().toString().concat("/").concat(gzipFile).concat(".gzip");
        try (var gos = new GZIPOutputStream(new FileOutputStream(result))) {
            Files.copy(filePath, gos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Integer searchText(String fileName, String mark) throws IllegalArgumentException {
        int linecount = 0;
        if (Files.notExists(Paths.get(fileName))) {
            throw new IllegalArgumentException("Can't find the resource file: ".concat(fileName));
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.contains(mark))
                    linecount++;
            }
            bf.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        return linecount;
    }

    @Override
    public String[] search(File folder, String ext) throws IllegalArgumentException {
        if (!folder.exists()) {
            throw new IllegalArgumentException();
        }
        if (ext != null) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isDirectory()) {
                    findFiles(file, ext);
                } else {
                    if (file.getName().endsWith(ext)) {
                        stringList.add(file.getAbsolutePath());
                    }

                }
            }
        } else {
            Stack<File> s = new Stack<>();
            s.push(folder);
            while (!s.empty()) {
                File tmpF = s.pop();
                if (tmpF.isFile()) {
                    stringList.add(tmpF.getAbsolutePath());
                } else if (tmpF.isDirectory()) {
                    File[] f = tmpF.listFiles();
                    for (File fpp : f) {
                        s.push(fpp);
                    }
                }
            }
        }

        return stringList.toArray(new String[0]);
    }

    public void findFiles(File folder, String ext) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                findFiles(file, ext);
            }
            if (file.getName().endsWith(ext)) {
                stringList.add(file.getAbsolutePath());
            }
        }
    }
}