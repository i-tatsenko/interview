package com.interview;

import java.util.Map;

interface Processor {

    void initialize(Map<String, String> configuration);

    void process(Map<String, Object> jsonDocument);
}
