create table farmed_user
(
    id         uuid    not null primary key,
    email      varchar not null,
    username   varchar not null,
    created_at date    not null
);

alter table farmed_user
    add unique (username);
alter table farmed_user
    add unique (email);

create type identity_type as enum ('email', 'google', 'apple');
create type social_type as enum ('facebook', 'instagram', 'twitter');
create type day_of_week as enum ('monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday');

create table identity
(
    id            uuid          not null primary key references farmed_user(id),
    identity_type identity_type not null,
    hash          varchar       not null,
    created_at    date          not null,
    updated_at    date          not null
);


create table farm
(
    id   uuid    not null primary key,
    name varchar not null
);

create table daily_schedule (
    id uuid not null primary key,
    day_of_week day_of_week not null,
    start_time time not null,
    end_time time not null,
    farm_id uuid not null references farm(id)
);

alter table daily_schedule add unique (day_of_week, farm_id);

create table contact
(
    id           uuid    not null primary key references farm(id),
    phone_number varchar not null,
    email        varchar not null
);

create table social
(
    id uuid not null primary key,
    social_type social_type not null,
    social_info varchar not null,
    contact_id uuid not null references contact(id)
);

alter table social add unique (social_type, contact_id);

create table review
(
    stars      int2    not null,
    check ( stars between 1 and 5),
    text       varchar not null,
    created_at date    not null,
    updated_at date    not null,
    farm_id uuid not null references farm(id),
    user_id uuid not null references farmed_user(id)
);

alter table review add primary key (farm_id, user_id);