package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityByteArray;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityByteArrayRepository extends JpaRepository<JsonEntityByteArray, Long> {
}
