package org.pablo.server;

import org.pablo.server.model.Counter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterTestData {
    public final static int START_SEQ = 100;
    public static Map<String, Counter> inMemoryCounterRepository = new ConcurrentHashMap<>();

    public final static Counter counter1 = new Counter("counter1", new AtomicInteger(START_SEQ));
    public final static Counter counter2 = new Counter("counter2", new AtomicInteger(START_SEQ + 1));
    public final static Counter counter3 = new Counter("counter3", new AtomicInteger(START_SEQ + 2));
    public final static Counter counter4 = new Counter("counter4", new AtomicInteger(START_SEQ + 3));

    public CounterTestData() {
        inMemoryCounterRepository.put(counter1.getName(), counter1);
        inMemoryCounterRepository.put(counter2.getName(), counter2);
        inMemoryCounterRepository.put(counter3.getName(), counter3);
        inMemoryCounterRepository.put(counter4.getName(), counter4);

        System.out.println(inMemoryCounterRepository);
    }
}
