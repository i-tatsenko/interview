package com.interview;

import java.util.Map;
import java.util.function.Supplier;

public class DefaultProcessorFactory implements ProcessorFactory {

    private final Map<String, Supplier<Processor>> processorsByName;

    public DefaultProcessorFactory() {
        this.processorsByName = Map.of(
                "AddField", AddFieldProcessor::new,
                "RemoveField", RemoveFieldProcessor::new,
                "CountNumOfFields", CountNumOfFieldsProcessor::new
                                      );
    }

    @Override
    public Processor create(String processorName) {
        var processorSupplier = processorsByName.get(processorName);
        if (processorSupplier == null) {
            throw new ProcessorCreationException("There is no known processor for name " + processorName);
        }
        return processorSupplier.get();
    }
}
