CREATE DATABASE optimizing_jsons;

CREATE TABLE json_as_text(
    id int NOT NULL PRIMARY KEY generated always as identity,
    json text NOT NULL
);

CREATE TABLE json_as_json(
    id int NOT NULL PRIMARY KEY generated always as identity,
    json json NOT NULL
);

CREATE TABLE json_as_jsonb(
    id int NOT NULL PRIMARY KEY generated always as identity,
    json jsonb NOT NULL
);