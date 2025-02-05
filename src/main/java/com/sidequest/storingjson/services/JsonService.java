package com.sidequest.storingjson.services;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.entities.JsonEntity;
import com.sidequest.storingjson.repositories.JsonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JsonService {

    private final JsonRepository jsonRepository;

    public void saveAll10000() {

        List<JsonEntity> randomJsonStringList = new ArrayList<>();

        for (int i = 0 ; i < 10000; i++) {

            JsonEntity jsonEntity = new JsonEntity();
            jsonEntity.setUser(Instancio.create(User.class));

            randomJsonStringList.add(jsonEntity);
        }

        log.info("Starting to store 10000 random JSON strings");

        long startTime = System.currentTimeMillis();

        jsonRepository.saveAll(randomJsonStringList);

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("Finished storing 10000 random JSON strings in {} milliseconds", resultTime);
    }
}
