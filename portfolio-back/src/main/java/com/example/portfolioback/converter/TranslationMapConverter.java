package com.example.portfolioback.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;

/** Stores a {language -> text} map as a JSON string, e.g. {"en":"...","fr":"..."}. */
@Converter
public class TranslationMapConverter implements AttributeConverter<Map<String, String>, String> {

    private static final JsonMapper MAPPER = JsonMapper.builder().build();
    private static final TypeReference<Map<String, String>> TYPE = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return attribute == null ? null : MAPPER.writeValueAsString(attribute);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MAPPER.readValue(dbData, TYPE);
    }
}
