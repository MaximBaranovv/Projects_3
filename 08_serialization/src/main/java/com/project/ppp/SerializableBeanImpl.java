package com.project.ppp;

import com.project.ppp.serializable.SerializableBean;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
import java.util.logging.Logger;

public class SerializableBeanImpl implements SerializableBean {

    private static final Logger LOGGER = Logger.getLogger(SerializableBeanImpl.class.getName());

    private static final String DELIMITER = ";";

    private static final long serialVersionUID = 5709291231628534274L;
    private String name;
    private String email;
    private int zip;

    public SerializableBeanImpl() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getZip() {
        return zip;
    }

    @Override
    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            out.writeObject(getName() + DELIMITER + getEmail() + DELIMITER + getZip());
        } catch (EOFException e) {
            LOGGER.info("End of stream");
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        try {
            String[] array = in.readObject().toString().split(DELIMITER);
            setName(array[0]);
            setEmail(array[1]);
            setZip(Integer.parseInt(array[2]));
        } catch (EOFException e) {
            LOGGER.info("End of stream");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableBeanImpl that = (SerializableBeanImpl) o;
        return zip == that.zip && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, zip);
    }

    @Override
    public String toString() {
        return "SerializableBeanImpl{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", zip=" + zip +
                '}';
    }
}
