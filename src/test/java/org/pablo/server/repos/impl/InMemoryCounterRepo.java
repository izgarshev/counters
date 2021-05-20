package org.pablo.server.repos.impl;

import org.pablo.server.CounterTestData;
import org.pablo.server.exceptions.EntityAlreadyExistsException;
import org.pablo.server.exceptions.NotFoundException;
import org.pablo.server.model.Counter;
import org.pablo.server.repos.CounterRepo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryCounterRepo implements CounterRepo<String, Counter> {
    private final Map<String, Counter> counterMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void setUp() {
        CounterTestData.initMap();
        CounterTestData.inMemoryCounterRepository.forEach(counterMap::put);
        System.out.println(counterMap);
    }

    @Override
    public Counter create(Counter counter) {
        Objects.requireNonNull(counter, "counter must not be null");
        if (exists(counter.getName())) {
            throw new EntityAlreadyExistsException("невозможно добавить счетчик с таким именем");
        }
        return counterMap.put(counter.getName(), counter);
    }

    @Override
    public void increment(String name) {
        if (getByName(name) == null) {
            throw new NotFoundException("счетчик с именем ".concat(name).concat(" не найден"));
        } else getByName(name).incrementValue();
    }

    @Override
    public Counter getValue(String name) {
        System.out.println(getAll());
        System.out.println("get value");
        System.out.println(name);
        return getByName(name);
    }

    @Override
    public void delete(String name) {
        counterMap.remove(name);
    }

    @Override
    public int getSumAllCounters() {
        return counterMap.values().stream().reduce(0, (sum, counter) -> sum + counter.getValue(), Integer::sum);
    }

    @Override
    public List<String> getAll() {
        return new ArrayList<>(counterMap.keySet());
    }

    @Override
    public boolean exists(String name) {
        return counterMap.containsKey(name);
    }

    public Counter getByName(String name) {
        if (exists(name)) {
            return counterMap.get(name);
        } else {
            throw new NotFoundException("счетчик с именем ".concat(name).concat(" не существует"));
        }
    }
}