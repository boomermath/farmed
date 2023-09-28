
CREATE TABLE IF NOT EXISTS farmed_user (
    id UUID NOT NULL UNIQUE PRIMARY_KEY
    email NOT NULL VARCHAR UNIQUE
    username NOT NULL VARCHAR UNIQUE 
);

CREATE TABLE IF NOT EXISTS email_user (
    id UUID NOT NULL UNIQUE PRIMARY_KEY,
    email VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL UNIQUE,
);

alter table email_user add constraint fk_email_user 
foreign key farmed_user_id references farmed (id);