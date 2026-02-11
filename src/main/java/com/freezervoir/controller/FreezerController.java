package com.freezervoir.controller;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.service.FreezerItemsService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor // can use this instead of needing @Autowired for service injection
@RequestMapping("/api")
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

     @PostMapping("/items")
     public ResponseEntity<String> addItem(@RequestBody FreezerItems newItem) {

         FreezerItems saved = service.saveItem(newItem);

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(String.format("%s successfully added", saved.getItemId()));
     }




}
