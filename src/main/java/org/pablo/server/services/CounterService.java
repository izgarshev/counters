package org.pablo.server.services;

import org.pablo.server.exceptions.CounterNotFoundException;
import org.pablo.server.exceptions.EntityAlreadyExistsException;
import org.pablo.server.model.Counter;
import org.pablo.server.repositories.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterService implements CounterServiceInterface {
    private final CounterRepository<String, Counter> counterRepository;

    @Autowired
    public CounterService(@Qualifier("counterInMemory") CounterRepository<String, Counter> counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public Counter create(Counter counter) {
        if (counterRepository.exists(counter.getName())) {
            throw new EntityAlreadyExistsException("a counter with the same name already exists");
        }
        return counterRepository.create(counter);
    }

    @Override
    public List<String> getAll() {
        return counterRepository.getAll();
    }

    @Override
    public int getSum() {
        return counterRepository.getSumAllCounters();
    }

    @Override
    public void increment(String name) {
        if (counterRepository.exists(name)) {
            counterRepository.increment(name);
        } else {
            throw new CounterNotFoundException(name);
        }
    }

    @Override
    public void delete(String name) {
        if (counterRepository.exists(name)) {
            counterRepository.delete(name);
        } else {
            throw new CounterNotFoundException(name);
        }
    }

    @Override
    public Counter getValue(String name) {
        if (counterRepository.exists(name)) {
            return counterRepository.getValue(name);
        } else {
            throw new CounterNotFoundException(name);
        }
    }
}
