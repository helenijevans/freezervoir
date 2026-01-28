package com.freezervoir;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {
    @GetMapping("/api")
    public String greeting() {
        return "Hello";
    }


}
