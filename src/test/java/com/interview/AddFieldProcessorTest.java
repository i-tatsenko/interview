package com.interview;

import com.interview.validation.ProcessorInitializationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddFieldProcessorTest {

    private AddFieldProcessor underTest = new AddFieldProcessor();

    private Map<String, Object> data = Map.of("dataKey", "dataValue");

    @Test
    public void shouldThrowExceptionWhenConfigurationIsInvalid() {
        assertThrows(ProcessorInitializationException.class, () -> underTest.initialize(Map.of()));

        assertThrows(ProcessorInitializationException.class, () -> underTest.initialize(Map.of("fieldName", "test")));
        assertThrows(ProcessorInitializationException.class, () -> underTest.initialize(Map.of("fieldValue", "test")));
    }

    @Test
    public void shouldAddField() {
        underTest.initialize(Map.of("fieldName", "test", "fieldValue", "testValue"));

        var processedData = new HashMap<>(data);
        underTest.process(processedData);

        var expected = new HashMap<>(data);
        expected.put("test", "testValue");
        Assertions.assertThat(processedData).containsAllEntriesOf(expected)
                  .hasSameSizeAs(expected);
    }

}
