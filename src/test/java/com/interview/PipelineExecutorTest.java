package com.interview;

import com.interview.validation.ProcessorInitializationException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class PipelineExecutorTest {

    private final ProcessorFactory factoryWithFailingToInitialize = name -> {
        if (name.equalsIgnoreCase("failure")) {
            return new InitializationFailedProcessor();
        }
        return new DummyProcessor(data -> fail("should not call it because previous processor failed to initialize"));
    };

    private PipelineExecutor underTest = new PipelineExecutor(factoryWithFailingToInitialize);
    private Map<String, Object> data = new HashMap<>();

    @Test
    public void shouldNotChangeDocumentWhenSomeProcessorsFailedToInitialize() {
        assertThrows(ProcessorInitializationException.class, () ->
                underTest.transform(new PipelineDescriptor(
                        List.of(createStep("default"), createStep("failure"))
                ), data));
    }

    private PipelineStep createStep(String name) {
        var step = new PipelineStep();
        step.setProcessor(name);
        return step;
    }

}
