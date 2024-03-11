--liquibase formatted sql

--changeset sageniuz:1 labels:example-label context:example-context
--comment: example comment
CREATE SEQUENCE Message_SEQ INCREMENT BY 50;

CREATE TABLE Message
(
    id   INTEGER PRIMARY KEY DEFAULT NEXTVAL('Message_SEQ'),
    text TEXT
);
