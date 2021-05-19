package org.pablo.server.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    String name;
    AtomicInteger value;

    public int getValue() {
        return value.intValue();
    }

    public String getName() {
        return name;
    }

    public void incrementValue() {
        this.value.incrementAndGet();
    }

    public Counter(String name, AtomicInteger value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter counter = (Counter) o;
        return Objects.equals(value, counter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "value=" + value +
                '}';
    }
}
