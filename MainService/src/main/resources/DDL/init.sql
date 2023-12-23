CREATE TABLE sequence (
    SEQ_NAME varchar,
    SEQ_COUNT bigint
);

INSERT INTO sequence values ('SEQ_GEN', 50);

CREATE TABLE coordinates (
    id bigint primary key,
    x float4 null,
    y float4 not null
);

CREATE TABLE locations (
    id bigint primary key,
    x float8 null,
    y bigint not null,
    z float8 not null
);

CREATE TYPE COUNTRY AS ENUM (
    'UNITED_KINGDOM',
    'INDIA',
    'ITALY',
    'SOUTH_KOREA'
);

CREATE TYPE SEMESTER AS ENUM (
    'FOURTH',
    'FIFTH',
    'SIXTH'
);

CREATE TYPE FormOfEducation AS ENUM (
    'DISTANCE_EDUCATION',
    'FULL_TIME_EDUCATION',
    'EVENING_CLASSES'
);


CREATE TABLE persons (
    id bigint primary key,
    name varchar(64) not null check ( name != '' ),
    passportID varchar(64) not null check ( length(name) >= 7),
    nationality COUNTRY null,
    location_id LOCATIONS not null
);

CREATE TABLE study_groups (
    id bigint primary key,
    name varchar not null check ( name != '' ),
    coordinates_id Coordinates not null,
    creationDate date not null,
    studentsCount bigint check ( studentsCount > 0 ),
    shouldBeExpelled int not null check ( shouldBeExpelled > 0 ),
    formOfEducation FormOfEducation not null,
    semesterEnum SEMESTER null,
    groupAdmin_id persons not null
)