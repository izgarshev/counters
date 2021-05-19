package org.pablo.controllers;

import org.pablo.model.Counter;
import org.pablo.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CounterController {
    CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping(value = "/counter/create")
    public void create(String name, @RequestBody Counter counter) {
        counterService.create(name, counter);
    }


}
