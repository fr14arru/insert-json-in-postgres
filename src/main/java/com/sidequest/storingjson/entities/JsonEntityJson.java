package com.sidequest.storingjson.entities;

import com.sidequest.storingjson.domain.User;
import com.sidequest.storingjson.utils.UserJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

@Getter
@Setter
@Entity
@Table(name = "json_entity_json")
public class JsonEntityJson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = UserJsonConverter.class)
    @Column(name = "\"user\"", columnDefinition = "json")
    @ColumnTransformer(write = "?::json")
    private User user;
}
