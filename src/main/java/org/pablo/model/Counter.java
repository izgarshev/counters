package org.pablo.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    AtomicInteger value;

    public int getValue() {
        return value.intValue();
    }

    public void incrementValue() {
        this.value.incrementAndGet();
    }

    public Counter(AtomicInteger value) {
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
