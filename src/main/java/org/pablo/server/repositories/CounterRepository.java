package org.pablo.server.repositories;

import java.util.List;

public interface CounterRepository<T, K> {
    K create(K counter);
    void increment(T name);
    K getValue(T name);
    void delete(T name);
    int getSumAllCounters();
    List<T> getAll();
    boolean exists(T name);

}
