package com.interview;

import com.interview.validation.ProcessorInitializationException;

import java.util.Map;

public class InitializationFailedProcessor implements Processor {

    @Override
    public void initialize(Map<String, String> configuration) {
        throw new ProcessorInitializationException("test failure");
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        throw new UnsupportedOperationException("Should not invoke process on a failed to initialize processor");
    }
}
