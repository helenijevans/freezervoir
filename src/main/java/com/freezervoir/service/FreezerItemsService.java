package com.freezervoir.service;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.repository.FreezerItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreezerItemsService {

    private final FreezerItemsRepository repository;

    public List<FreezerItems> getAll() {
        return repository.findAll();
    }

    public FreezerItems getById(String itemId) throws ItemNotFoundException {
        return repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(
                         "Item not found: " + itemId));
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