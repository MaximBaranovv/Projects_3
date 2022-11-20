package com.project.ppp;

import com.project.ppp.io.NIOUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String source_filepath = "/Users/mah/FolderForIO/text.txt";
        String destination_zip_filepath = "/Users/mah/FolderForIO/";
        IOUtilsImpl ioUtils = new IOUtilsImpl();
        NIOUtils nioUtils = new NIOUtilsImpl();
        System.out.println(ioUtils.gzip(source_filepath, destination_zip_filepath));

        String file2 = "/Users/mah/FolderForIO2/text.txt";
        System.out.println(ioUtils.searchText(file2, "the"));

        File file = new File("/Users/mah/FolderForIO3");
        System.out.println(Arrays.toString(ioUtils.search(file, "")));

        Path path = Paths.get("/Users/mah/FolderForNIO/text.txt");
        System.out.println(nioUtils.searchText(path, 4));

        Path path2 = Paths.get("/Users/mah/FolderForIO3");
        System.out.println(Arrays.toString(nioUtils.search(path2, "")));
    }
}
