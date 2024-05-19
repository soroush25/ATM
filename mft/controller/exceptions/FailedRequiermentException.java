package mft.controller.exceptions;

public class FailedRequiermentException extends Exception{
    public FailedRequiermentException() {
        super("Person Dont Have Minimum Requirement !!!");
    }
}

