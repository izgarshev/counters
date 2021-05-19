package org.pablo.repos.impl;

import org.pablo.model.Counter;
import org.pablo.repos.CounterRepo;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CounterRepoImpl implements CounterRepo {
    ConcurrentHashMap<String, Counter> inMemoryCounterMap = new ConcurrentHashMap<>();

    @Override
    public void create(String key, Counter counter) {
        inMemoryCounterMap.put(key, counter);
    }

    @Override
    public void increment(String name) {

    }

    @Override
    public int getValue(String name) {
        return 0;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public int getSumAllCounters() {
        return 0;
    }

    @Override
    public Set<Counter> getCounters() {
        return null;
    }
}
