package com.interview.validation;

import com.interview.PipelineException;

//TODO: consider to make it checked in case we can change the Processor#initialize signature
public class ProcessorInitializationException extends PipelineException {

    public ProcessorInitializationException(String message) {
        super(message);
    }
}
