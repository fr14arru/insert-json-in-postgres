package com.sidequest.storingjson.repositories;

import com.sidequest.storingjson.entities.JsonEntityRawText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonEntityRawTextRepository extends JpaRepository<JsonEntityRawText, Long> {
}
