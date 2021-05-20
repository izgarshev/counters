package org.pablo.server.exceptions;

public class CounterNotFoundException extends RuntimeException {
    public CounterNotFoundException(String name) {
        super("counter with name ".concat(name).concat(" not found"));
    }
}
