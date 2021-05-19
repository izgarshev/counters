package org.pablo.server.controllers;

import org.pablo.server.model.Counter;
import org.pablo.server.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CounterController {
    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping(value = "/counter/create")
    public void create(@RequestBody Counter counter) {
        counterService.create(counter);
    }

    @GetMapping(value = "/counter/all")
    public List<String> getAll() {
        return counterService.getAll();
    }

    @GetMapping(value = "/counter/sum")
    public int getSum() {
        return counterService.getSum();
    }

    @GetMapping(value = "/counter/increment")
    public void increment(@RequestParam String name) {
        counterService.increment(name);
    }

    @DeleteMapping(value = "/counter/delete")
    public void delete(@RequestParam String name) {
        counterService.delete(name);
    }

    @GetMapping(value = "/counter/get")
    public Counter get(@RequestParam String name) {
        return counterService.getValue(name);
    }
}
