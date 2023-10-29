create table farmed_user
(
    id          uuid    not null primary key,
    email       varchar not null unique,
    username    varchar not null unique,
    created_at  date    not null,
    identity_id uuid not null unique
);

create type identity_type as enum ('email', 'google', 'apple');

create table farmed_user_identity
(
    id            uuid          not null primary key,
    identity_type identity_type not null,
    hash          varchar       not null,
    created_at    date          not null,
    updated_at    date          not null,
    user_id       uuid          not null unique references farmed_user (id)
);

alter table farmed_user add constraint fk_farmed_user_identity
    foreign key (identity_id) references farmed_user_identity(id)
on delete cascade;