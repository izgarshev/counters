package org.pablo.server.repositories.inmemory;

import org.pablo.server.exceptions.CounterNotFoundException;
import org.pablo.server.model.Counter;
import org.pablo.server.repositories.CounterRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CounterInMemory implements CounterRepository<String, Counter> {
    ConcurrentHashMap<String, Counter> inMemoryCounterMap = new ConcurrentHashMap<>();

    @Override
    public Counter create(Counter counter) {
        return inMemoryCounterMap.put(counter.getName(), counter);
    }

    @Override
    public void increment(String name) {
        if (getByName(name) == null) {
            throw new CounterNotFoundException(name);
        } else getByName(name).incrementValue();
    }

    @Override
    public Counter getValue(String name) {
        return getByName(name);
    }

    @Override
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
