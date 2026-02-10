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

    private final FreezerItemsRepository repo;

    public List<FreezerItems> getAll() {
        return repo.findAll();
    }

    public FreezerItems getById(String itemId) {
        return repo.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found: " + itemId));
    }

    public void deleteById(String id) throws ItemNotFoundException {
        if (!repo.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        repo.deleteById(id);
    }
}
