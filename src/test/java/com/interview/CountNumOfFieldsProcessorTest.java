package com.interview;

import com.interview.validation.ProcessorInitializationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.data.MapEntry.entry;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountNumOfFieldsProcessorTest {

    private CountNumOfFieldsProcessor underTest = new CountNumOfFieldsProcessor();

    private Map<String, Object> data = Map.of("key", "value");

    @Test
    public void shouldThrowExceptionOnInvalidConfig() {
        assertThrows(ProcessorInitializationException.class, () -> underTest.initialize(Map.of()));
    }

    @Test
    public void shouldCountFields() {
        underTest.initialize(Map.of("targetFieldName", "count"));

        var processed = new HashMap<>(data);
        underTest.process(processed);

        Assertions.assertThat(processed).containsExactly(entry("count", 1), entry("key", "value"));
    }

    @Test
    public void shouldNotOverwriteFields() {
        underTest.initialize(Map.of("targetFieldName", "key"));
        assertThrows(ProcessingException.class, () -> underTest.process(data));
    }

}
