package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityJsonRepository extends JpaRepository<JsonEntityJson, Long> {
}
