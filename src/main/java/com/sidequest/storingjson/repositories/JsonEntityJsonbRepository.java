package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityJsonb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityJsonbRepository extends JpaRepository<JsonEntityJsonb, Long> {
}
