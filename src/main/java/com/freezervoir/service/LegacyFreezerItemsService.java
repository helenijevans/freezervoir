package com.freezervoir.service;

import com.freezervoir.entity.LegacyFreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.repository.LegacyFreezerItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegacyFreezerItemsService {

    private final LegacyFreezerItemsRepository repository;

    public List<LegacyFreezerItems> getAll() {
        return repository.findAll();
    }

    public LegacyFreezerItems getById(String itemId) throws ItemNotFoundException {
        return repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(
                         "Item not found: " + itemId));
    }

    public LegacyFreezerItems saveItem(LegacyFreezerItems newItem){
        return repository.save(newItem);
    }

    public void deleteById(String id) throws ItemNotFoundException {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        repository.deleteById(id);
    }


}