package com.interview;

import java.util.Map;
import java.util.function.Consumer;

public class DummyProcessor implements Processor {

    private final Consumer<Map<String, Object>> processor;

    public DummyProcessor(Consumer<Map<String, Object>> processor) {
        this.processor = processor;
    }

    @Override
    public void initialize(Map<String, String> configuration) {

    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        processor.accept(jsonDocument);
    }
}
