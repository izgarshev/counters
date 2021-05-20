package org.pablo.server.services;

import org.pablo.server.model.Counter;

import java.util.List;

public interface CounterServiceInterface {
    Counter create(Counter counter);

    List<String> getAll();

    int getSum();

    void increment(String name);

    void delete(String name);

    Counter getValue(String name);
}
