package com.freezervoir.repository;

import com.freezervoir.entity.FreezerItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreezerItemsRepository extends JpaRepository<FreezerItems, String> {
}
