package com.freezervoir.repository;

import com.freezervoir.entity.LegacyFreezerItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyFreezerItemsRepository extends JpaRepository<LegacyFreezerItems, String> {
}
