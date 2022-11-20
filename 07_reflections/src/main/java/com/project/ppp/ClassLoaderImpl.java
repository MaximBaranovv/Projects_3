package com.project.ppp;

import com.project.ppp.reflection.PathClassLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class ClassLoaderImpl extends ClassLoader implements PathClassLoader {
    private Path path;

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            if (name.contains(".class")) {
                name = name.replace(".class", "");
            }
            String classFile = name.replaceAll("\\.", "/") + ".class";

            Path filePath = Path.of(getPath() + "\\" + classFile);
            byte[] classBytes = Files.readAllBytes(filePath);


            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
