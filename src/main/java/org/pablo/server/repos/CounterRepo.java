package org.pablo.server.repos;

import java.util.List;

public interface CounterRepo<T, K> {
    K create(K counter);
    void increment(T name);
    K getValue(T name);
    void delete(T name);
    int getSumAllCounters();
    List<T> getAll();
    boolean exists(T name);

}
