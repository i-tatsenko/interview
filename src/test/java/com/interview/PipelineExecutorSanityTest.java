package com.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PipelineExecutorSanityTest {

    private PipelineExecutor underTest = new PipelineExecutor(new DefaultProcessorFactory());
    private PipelineDescriptorFactory pipelineDescriptorFactory = new PipelineDescriptorFactory(new ObjectMapper());

    private Map<String, Object> data = Map.of(
            "key1", "value1",
            "key2", LocalDateTime.now(),
            "key3", "value3"
                                             );

    @Test
    public void shouldProcessTestScenario() throws IOException {
        var processed = new HashMap<>(data);
        underTest.transform(pipelineDescriptorFactory.fromJson(readTestFile("test-pipeline-descriptor.json")), processed);

        var expected = new HashMap<>(data);
        expected.put("firstName", "George");
        expected.put("numOfFields", 4);

        assertThat(processed).containsAllEntriesOf(expected).hasSameSizeAs(expected);
    }

    private String readTestFile(String fileName) throws IOException {
        return Files.readString(new File(getClass().getClassLoader().getResource(fileName).getFile()).toPath());
    }
}
