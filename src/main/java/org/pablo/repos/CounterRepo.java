package org.pablo.repos;

import org.pablo.model.Counter;

import java.util.Set;

public interface CounterRepo {
    void create(String name, Counter counter);
    void increment(String name);
    int getValue(String name);
    void delete(String name);
    int getSumAllCounters();
    Set<Counter> getCounters();

}
