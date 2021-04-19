drop table if exists node;
drop table if exists way;
drop table if exists relation;

CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS earthdistance cascade;

create table node
(
    id        bigserial not null
        constraint node_pk
            primary key,
    latitude  double precision,
    longitude double precision,
    user_name text,
    tags      hstore
);

create table relation
(
    id         bigserial not null
        constraint relation_pk
            primary key,
    user_name  text,
    is_visible boolean,
    tags       hstore
);

create table way
(
    id         bigserial not null
        constraint way_pk
            primary key,
    user_name  text,
    is_visible boolean,
    tags       hstore
);

