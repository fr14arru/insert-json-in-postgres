package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedZstd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityByteArrayCompressedZstdRepository extends JpaRepository<JsonEntityByteArrayCompressedZstd, Long> {
}
