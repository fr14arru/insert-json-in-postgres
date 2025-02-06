package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityByteArrayCompressedRepository extends JpaRepository<JsonEntityByteArrayCompressed, Long> {
}
