package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonRepository extends JpaRepository<JsonEntity, Long> {
}
