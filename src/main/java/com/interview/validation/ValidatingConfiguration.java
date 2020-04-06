package com.interview.validation;

import java.util.HashMap;
import java.util.Map;

//TODO: consider the signature change of Processor#initialize to receive  ProcessorConfiguration instead of Map
//TODO: consider removing ProcessorConfiguration interface and using validation in a Map delegator
public class ValidatingConfiguration implements ProcessorConfiguration {

    private final Map<String, String> configuration;

    public ValidatingConfiguration(Map<String, String> configuration) {
        this.configuration = new HashMap<>(configuration);
    }

    @Override
    public String get(String configKey) {
        var value = configuration.get(configKey);
        //TODO: in case of optional configuration keys this should be changed
        if (value == null) {
            throw new ProcessorInitializationException("No required property " + configKey + " has been found in the configuration");

        }
        return value;
    }
}
