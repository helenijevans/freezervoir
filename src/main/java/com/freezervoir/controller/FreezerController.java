package com.freezervoir.controller;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.service.FreezerItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor // can use this instead of needing @Autowired for service injection
public class FreezerController {
    @GetMapping("/api")
    public String greeting() {
        return "Hello";
    }

    private final FreezerItemsService service;

    @GetMapping("/api/get_items")
    public List<FreezerItems> getAll() {
        return service.getAll();
    }

    @GetMapping("/api/get_items/{itemId}")
    public FreezerItems getById(@PathVariable String itemId) {
        return service.getById(itemId);
    }

}
