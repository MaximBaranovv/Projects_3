package com.project.ppp.converter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringDateConverter extends JsonDeserializer<java.sql.Date> {

    private static final Logger LOGGER = Logger.getLogger(StringDateConverter.class.getName());

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String date = jsonParser.getText();
        Date startDate = null;
        try {
            startDate = DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        return sqlStartDate;
    }
}
