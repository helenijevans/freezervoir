package com.freezervoir.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "freezer_items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FreezerItems {
        @Id
        @Column(name = "item_ID")
        private String itemId;

        @Column(nullable = false, name = "date_added")
        private LocalDate dateAdded ;

        @Column
        private String notes;
    }

