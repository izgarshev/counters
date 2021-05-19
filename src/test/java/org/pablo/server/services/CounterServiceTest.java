package org.pablo.server.services;

import org.junit.jupiter.api.Test;
import org.pablo.server.model.Counter;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class CounterServiceTest {
    CounterService service;

    public CounterServiceTest(CounterService counterService) {
        this.service = counterService;
    }

    @Test
    void create() {
//        Meal created = service.create(getNew(), USER_ID);
//        int newId = created.id();
//        Meal newMeal = getNew();
//        newMeal.setId(newId);
//        MEAL_MATCHER.assertMatch(created, newMeal);
//        MEAL_MATCHER.assertMatch(service.get(newId, USER_ID), newMeal);
        Counter created = service.create(new Counter("new", new AtomicInteger(4)));
        Counter newCounter = new Counter(created.getName(), new AtomicInteger(created.getValue()));
        assertEquals(created, newCounter);

    }

    @Test
    void getAll() {
    }

    @Test
    void getSum() {
    }

    @Test
    void increment() {
    }

    @Test
    void delete() {
    }

    @Test
    void getValue() {
    }
}