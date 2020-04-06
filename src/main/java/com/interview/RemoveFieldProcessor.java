package com.interview;

import com.interview.validation.ValidatingConfiguration;

import java.util.Map;

public class RemoveFieldProcessor implements Processor {

    private static final String FIELD_NAME_PROPERTY = "fieldName";

    private String fieldName;

    @Override
    public void initialize(Map<String, String> configuration) {
        fieldName = new ValidatingConfiguration(configuration).get(FIELD_NAME_PROPERTY);
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        jsonDocument.remove(fieldName);
    }
}
