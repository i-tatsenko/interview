package com.interview;

import java.util.List;

public class PipelineDescriptor {

    private List<PipelineStep> steps;

    public PipelineDescriptor() {}

    public PipelineDescriptor(List<PipelineStep> steps) {
        this.steps = steps;
    }

    public List<PipelineStep> getSteps() {
        return steps;
    }

    public void setSteps(List<PipelineStep> steps) {
        this.steps = steps;
    }
}
