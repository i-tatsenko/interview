package com.interview;

import com.interview.validation.ValidatingConfiguration;

import java.util.Map;

public class CountNumOfFieldsProcessor implements Processor {

    private static final String TARGET_FIELD_NAME_PROPERTY = "targetFieldName";

    private String targetField;

    @Override
    public void initialize(Map<String, String> configuration) {
        targetField = new ValidatingConfiguration(configuration).get(TARGET_FIELD_NAME_PROPERTY);
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        //TODO: should clarify expected behaviour in such case
        if (jsonDocument.containsKey(targetField)) {
            throw new ProcessingException("Document already contains " + targetField + " key");
        }
        jsonDocument.put(targetField, jsonDocument.size());
    }
}
