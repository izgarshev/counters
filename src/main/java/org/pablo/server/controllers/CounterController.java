package org.pablo.server.controllers;

import org.pablo.server.model.Counter;
import org.pablo.server.services.CounterServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CounterController {
    private final CounterServiceInterface counterService;

    public CounterController(@Qualifier(value = "counterService") CounterServiceInterface counterService) {
        this.counterService = counterService;
    }

    /**
     * добавляет новый счетчик
     * @param counter - счетчик
     */
    @PostMapping(value = "/counter/create")
    public void create(@RequestBody Counter counter) {
        counterService.create(counter);
    }

    /**
     * @return - список названий всех счетчиков
     */
    @GetMapping(value = "/counter/all")
    public List<String> getAll() {
        return counterService.getAll();
    }

    /**
     * @return - сумма всех счетчиков
     */
    @GetMapping(value = "/counter/sum")
    public int getSum() {
        return counterService.getSum();
    }

    /**
     * увеличивает значение счетчика с указанным именем на 1
     * @param name - имя счетчика
     */
    @GetMapping(value = "/counter/increment")
    public void increment(@RequestParam String name) {
        counterService.increment(name);
    }

    /**
     * удаляет счетчик с указанным именем
     * @param name - имя счетчика
     */
    @DeleteMapping(value = "/counter/delete")
    public void delete(@RequestParam String name) {
        counterService.delete(name);
    }

    /**
     * @param name - имя счетчика
     * @return - счетчик с указанным именем
     */
    @GetMapping(value = "/counter/get")
    public Counter get(@RequestParam String name) {
        return counterService.getValue(name);
    }
}
