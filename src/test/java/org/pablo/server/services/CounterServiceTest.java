package org.pablo.server.services;

import org.junit.jupiter.api.Test;
import org.pablo.server.CounterTestData;
import org.pablo.server.exceptions.CounterNotFoundException;
import org.pablo.server.exceptions.EntityAlreadyExistsException;
import org.pablo.server.model.Counter;
import org.pablo.server.repositories.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CounterServiceTest {

    @Autowired
    @Qualifier(value = "inMemoryCounterRepository")
    private CounterRepository<String, Counter> counterRepository;

    @Test
    void create() {
        Counter newCounter = CounterTestData.forCreate;
        counterRepository.create(newCounter);
        Counter fromRepo = counterRepository.getValue(newCounter.getName());
        assertEquals(newCounter, fromRepo);
    }

    @Test
    void createFailTest() {
        counterRepository.create(CounterTestData.forRepeat);
        assertThrows(EntityAlreadyExistsException.class, () -> counterRepository.create(CounterTestData.forRepeat));
    }

    @Test
    void increment() {
        Counter counter = counterRepository.getValue(CounterTestData.counter1.getName());
        int startedValue = counter.getValue();
        counter.incrementValue();
        assertEquals(counter.getValue(), startedValue + 1);
    }

    @Test
    void delete() {
        Counter counter = CounterTestData.forDelete;
        String name = counter.getName();
        counterRepository.create(counter);
        counterRepository.delete(counter.getName());
        assertThrows(CounterNotFoundException.class, () -> counterRepository.getValue(name));
    }
}