package com.freezervoir.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "legacy_freezer_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegacyFreezerItems {
        @Id
        @Column(name = "item_ID")
        @NotBlank(message = "ID cannot be blank")
        private String itemId;

        @Column(nullable = false, name = "date_added")
        @NotNull(message = "Date cannot be null")
        private LocalDate dateAdded ;

        @Column
        private String notes;
    }

