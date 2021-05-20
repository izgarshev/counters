package org.pablo.server.repos.impl;

import org.pablo.server.exceptions.NotFoundException;
import org.pablo.server.model.Counter;
import org.pablo.server.repos.CounterRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Transactional(readOnly = true)
public class CounterRepoImpl implements CounterRepo<String, Counter> {
    ConcurrentHashMap<String, Counter> inMemoryCounterMap = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public Counter create(Counter counter) {
        return inMemoryCounterMap.put(counter.getName(), counter);
    }

    @Override
    @Transactional
    public void increment(String name) {
        if (getByName(name) == null) {
            throw new NotFoundException("счетчик с именем ".concat(name).concat(" не найден"));
        } else getByName(name).incrementValue();
    }

    @Override
    public Counter getValue(String name) {
        return getByName(name);
    }

    @Override
    @Transactional
    public void delete(String name) {
        inMemoryCounterMap.remove(name);
    }

    @Override
    public int getSumAllCounters() {
        return inMemoryCounterMap.values().stream().reduce(0, (sum, counter) -> sum + counter.getValue(), Integer::sum);
    }

    @Override
    public List<String> getAll() {
        return new ArrayList<>(inMemoryCounterMap.keySet());
    }

    @Override
    public boolean exists(String name) {
        return inMemoryCounterMap.containsKey(name);
    }

    public Counter getByName(String name) {
        return inMemoryCounterMap.get(name);
    }
}
