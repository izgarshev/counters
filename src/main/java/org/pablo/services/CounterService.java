package org.pablo.services;

import org.pablo.model.Counter;
import org.pablo.repos.CounterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    CounterRepo counterRepo;

    @Autowired
    public CounterService(CounterRepo counterRepo) {
        this.counterRepo = counterRepo;
    }

    public void create(String name, Counter counter) {
        counterRepo.create(name, counter);
    }
}
