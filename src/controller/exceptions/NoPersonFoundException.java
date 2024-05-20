package src.controller.exceptions;

public class NoPersonFoundException extends Exception {
    public NoPersonFoundException() {
        super("Not Found!");
    }
}
