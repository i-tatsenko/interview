package com.interview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PipelineDescriptorFactory {

    private final ObjectMapper om;

    public PipelineDescriptorFactory(ObjectMapper om) {
        this.om = om;
    }

    public PipelineDescriptor fromJson(String json) {
        try {
            var descriptor = om.readValue(json, PipelineDescriptor.class);
            validate(descriptor);
            return descriptor;
        } catch (JsonProcessingException e) {
            throw new PipelineCreationException("Can't deserialize pipeline json", e);
        }
    }

    private void validate(PipelineDescriptor descriptor) {
        descriptor.getSteps().forEach(step -> {
            if (step.getProcessor() == null || step.getProcessor().isBlank()) {
                throw new PipelineCreationException("Empty step name in descriptor");
            }
            if (step.getConfiguration() == null) {
                throw new PipelineCreationException("Null configuration for processor: " + step.getProcessor());
            }
        });
    }
}
