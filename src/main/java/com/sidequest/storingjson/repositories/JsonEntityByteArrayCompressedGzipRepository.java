package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityByteArrayCompressedGzip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityByteArrayCompressedGzipRepository extends JpaRepository<JsonEntityByteArrayCompressedGzip, Long> {
}
