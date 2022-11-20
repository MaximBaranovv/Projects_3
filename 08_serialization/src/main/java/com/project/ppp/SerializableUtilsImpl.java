package com.project.ppp;

import com.project.ppp.serializable.SerializableUtils;

import java.io.*;

public class SerializableUtilsImpl implements SerializableUtils {

    @Override
    public void serialize(OutputStream out, Object obj) {
        if (obj == null || out == null) {
            throw new NullPointerException();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(obj);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(InputStream in, Class<T> clazz) {
        Object object;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return clazz.cast(object);
    }
}