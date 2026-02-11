package com.freezervoir.service;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.repository.FreezerItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreezerItemsService {

    private final FreezerItemsRepository repository;

    public List<FreezerItems> getAll() {
        return repository.findAll();
    }

    public FreezerItems getById(String itemId) {
        return repository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found: " + itemId));
    }

    public FreezerItems saveItem(FreezerItems newItem){
        return repository.save(newItem);
    }

    public void deleteById(String id) throws ItemNotFoundException {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        repository.deleteById(id);
    }


}
