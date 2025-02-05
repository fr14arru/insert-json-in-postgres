package com.sidequest.storingjson.utils;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class UserJsonConverter implements AttributeConverter<User, String> {

    @Override
    public String convertToDatabaseColumn(User user) {

        return JSON.toJSONString(user);
    }

    @Override
    public User convertToEntityAttribute(String s) {

        return JSON.parseObject(s, User.class);
    }
}
