package org.pablo.server.services;

import org.junit.jupiter.api.Test;
import org.pablo.server.CounterTestData;
import org.pablo.server.exceptions.EntityAlreadyExistsException;
import org.pablo.server.exceptions.NotFoundException;
import org.pablo.server.model.Counter;
import org.pablo.server.repos.CounterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CounterServiceTest {

    @Autowired
    @Qualifier(value = "inMemoryCounterRepo")
    private CounterRepo<String, Counter> counterRepo;

    @Test
    void create() {
        Counter newCounter = CounterTestData.forCreate;
        counterRepo.create(newCounter);
        Counter fromRepo = counterRepo.getValue(newCounter.getName());
        assertEquals(newCounter, fromRepo);
    }

    @Test
    void createFailTest() {
        counterRepo.create(CounterTestData.forRepeat);
        assertThrows(EntityAlreadyExistsException.class, () -> counterRepo.create(CounterTestData.forRepeat));
    }

    @Test
    void increment() {
        Counter counter = counterRepo.getValue(CounterTestData.counter1.getName());
        int startedValue = counter.getValue();
        counter.incrementValue();
        assertEquals(counter.getValue(), startedValue + 1);
    }

    @Test
    void delete() {
        Counter counter = CounterTestData.forDelete;
        String name = counter.getName();
        counterRepo.create(counter);
        counterRepo.delete(counter.getName());
        assertThrows(NotFoundException.class, () -> counterRepo.getValue(name));
    }
}