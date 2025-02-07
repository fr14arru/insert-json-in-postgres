package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedSnappy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityByteArrayCompressedSnappyRepository extends JpaRepository<JsonEntityByteArrayCompressedSnappy, Long> {
}
