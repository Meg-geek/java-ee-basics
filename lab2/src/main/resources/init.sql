drop table if exists tag;
drop table if exists node;

create table node
(
    id        bigserial not null
        constraint node_pk
            primary key,
    latitude  double precision,
    longitude double precision,
    user_name    text
);

create table tag
(
    key     text,
    value   text,
    node_id bigint not null
        constraint tag_node_id_fk
            references node
);