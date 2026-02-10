package com.freezervoir.controller;

import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.service.FreezerItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // can use this instead of needing @Autowired for service injection
public class FreezerController {
     private final FreezerItemsService service;

     @DeleteMapping("/items/{id}")
     public ResponseEntity<String> deleteDetails(@PathVariable String id) {
         try {
             service.deleteById(id);
             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body(String.format("%s successfully deleted", id));

         } catch (ItemNotFoundException e) {
             return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .body(String.format("%s not found", id));

         }
     }



}
