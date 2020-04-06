package com.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PipelineExecutor {

    private final ProcessorFactory processorFactory;

    PipelineExecutor(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    public void transform(PipelineDescriptor pipelineDescriptor,
                          Map<String, Object> jsonDocument) {
        List<Processor> pipeline = new ArrayList<>();

        for (PipelineStep step : pipelineDescriptor.getSteps()) {
            var processor = processorFactory.create(step.getProcessor());
            processor.initialize(step.getConfiguration());
            pipeline.add(processor);
        }

        pipeline.forEach(p -> p.process(jsonDocument));
    }
}