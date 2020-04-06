package com.interview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultProcessorFactoryTest {

    private DefaultProcessorFactory underTest = new DefaultProcessorFactory();

    @Test
    public void shouldCreateAllKnownProcessors() {
        assertThat(underTest.create("AddField")).isInstanceOf(AddFieldProcessor.class);
        assertThat(underTest.create("RemoveField")).isInstanceOf(RemoveFieldProcessor.class);
        assertThat(underTest.create("CountNumOfFields")).isInstanceOf(CountNumOfFieldsProcessor.class);
    }

}
