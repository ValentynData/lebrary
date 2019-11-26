package com.app.audit.converter;

import com.app.audit.holders.ObjectMapperHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashSet;

public class Set2StringConverter  implements AttributeConverter<HashSet, String> {
    @Autowired
    private ObjectMapper objectMapper;
    private ApplicationContext context;


    @Override
    public String convertToDatabaseColumn(HashSet income) {
        if (income == null) return "";
        try {
            return getObjectMapper().writeValueAsString(income);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashSet convertToEntityAttribute(String income) {
        if (income == null) return new HashSet();
        try {
            return getObjectMapper().readValue(income, HashSet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = ObjectMapperHolder.getObjectMapper();
        }
        return objectMapper;
    }
}
