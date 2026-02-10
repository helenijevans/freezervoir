package com.freezervoir.service;

import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.repository.FreezerItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FreezerItemsServiceTest {

    @Mock
    FreezerItemsRepository repository;

    @InjectMocks
    FreezerItemsService service;

    @Test
    void deleteById_existingItem_deletes() throws ItemNotFoundException {
        when(repository.existsById("SalmonPiece1")).thenReturn(true);

        service.deleteById("SalmonPiece1");

        verify(repository).deleteById("SalmonPiece1");
    }

    @Test
    void deleteById_missingItem_throwsException() {
        when(repository.existsById("SalmonPiece1")).thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> service.deleteById("SalmonPiece1"));
    }
}
