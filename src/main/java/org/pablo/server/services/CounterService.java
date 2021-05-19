package org.pablo.server.services;

import org.pablo.server.exceptions.EntityAlreadyExistsException;
import org.pablo.server.exceptions.NotFoundException;
import org.pablo.server.model.Counter;
import org.pablo.server.repos.CounterRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterService {
    private final CounterRepo<String, Counter> counterRepo;

    public CounterService(CounterRepo<String, Counter> counterRepo) {
        this.counterRepo = counterRepo;
    }

    public Counter create(Counter counter) {
        if (counterRepo.exists(counter.getName())) {
            throw new EntityAlreadyExistsException("не удалось добавить, счетчик с таким именем уже существует");
        }
        return counterRepo.create(counter);
    }

    public List<String> getAll() {
        return counterRepo.getAll();
    }

    public int getSum() {
        return counterRepo.getSumAllCounters();
    }

    public void increment(String name) {
        if (counterRepo.exists(name)) {
            counterRepo.increment(name);
        } else throw new NotFoundException("не удалось увеличить значение. Счетчик с именем ".concat(name).concat(" не существует"));
    }

    public void delete(String name) {
        if (counterRepo.exists(name)) {
            counterRepo.delete(name);
        } else throw new NotFoundException("не удалось удалить. Счетчик с именем ".concat(name).concat(" не существует"));
    }

    public Counter getValue(String name) {
        return counterRepo.getValue(name);
    }
}
