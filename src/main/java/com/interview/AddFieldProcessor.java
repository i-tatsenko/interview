package com.interview;

import com.interview.validation.ValidatingConfiguration;

import java.util.Map;

public class AddFieldProcessor implements Processor {

    private static final String FIELD_NAME_PROPERTY = "fieldName";
    private static final String FIELD_VALUE_PROPERTY = "fieldValue";

    private String fieldName;
    private String fieldValue;

    @Override
    public void initialize(Map<String, String> configuration) {
        var validatingConfiguration = new ValidatingConfiguration(configuration);
        fieldName = validatingConfiguration.get(FIELD_NAME_PROPERTY);
        fieldValue = validatingConfiguration.get(FIELD_VALUE_PROPERTY);
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        jsonDocument.put(fieldName, fieldValue);
    }
}
