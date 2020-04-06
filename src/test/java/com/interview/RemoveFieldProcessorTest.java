package com.interview;

import com.interview.validation.ProcessorInitializationException;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveFieldProcessorTest {

    private RemoveFieldProcessor underTest = new RemoveFieldProcessor();

    private Map<String, Object> data = Map.of("key1", "value1", "key2", "value2");


    @Test
    public void shouldFailInitializationWhenNoFieldName() {
        assertThrows(ProcessorInitializationException.class, () -> underTest.initialize(Map.of()));
    }

    @Test
    public void shouldRemoveField() {
        underTest.initialize(Map.of("fieldName", "key1"));

        var processed = new HashMap<>(data);
        underTest.process(processed);

        assertThat(processed).containsExactly(MapEntry.entry("key2", "value2"));
    }

    @Test
    public void shouldNotFailWhenKeyIsNotPresent() {
        underTest.initialize(Map.of("fieldName", "missing key"));

        var processed = new HashMap<>(data);
        underTest.process(processed);

        assertThat(processed).containsAllEntriesOf(data).hasSameSizeAs(data);
    }

}
