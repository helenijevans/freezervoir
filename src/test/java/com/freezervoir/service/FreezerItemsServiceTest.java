package com.freezervoir.service;

import com.freezervoir.entity.LegacyFreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.repository.LegacyFreezerItemsRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FreezerItemsServiceTest {

    @Mock
    LegacyFreezerItemsRepository repository;

    @InjectMocks
    LegacyFreezerItemsService service;

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

    @Test
    void testAddItem_valid() {
        LegacyFreezerItems item = new LegacyFreezerItems(
                "SalmonPiece02",
                LocalDate.of(2025, 1, 8),
                "THROW"
        );

        // Mock repository to return the object it receives
        when(repository.save(any(LegacyFreezerItems.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LegacyFreezerItems savedItem = service.saveItem(item);

        System.out.println("Saved item: " + savedItem); // should not be null

        assertNotNull(savedItem);
        assertEquals("SalmonPiece02", savedItem.getItemId());
        verify(repository, times(1)).save(item);
    }

    @Test
    void testAddItem_missingDate() {
        LegacyFreezerItems item = new LegacyFreezerItems(
                "SalmonPiece04",
                null, // missing date
                "THROW"
        );

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<LegacyFreezerItems>> violations = validator.validate(item);

            assertFalse(violations.isEmpty());
        }
    }


}


