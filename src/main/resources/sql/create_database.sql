CREATE TABLE IF NOT EXISTS REGIONS
(
    id   bigint
        CONSTRAINT regions_pk  PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) UNIQUE NOT NULL,
    reduction varchar(3) UNIQUE  NOT NULL
);