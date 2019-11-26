CREATE TABLE if not exists authors(
id serial primary key not null,
first_name varchar(255),
last_name varchar(255)
);

CREATE TABLE if not exists books(
id serial primary key not null,
book_name varchar(255),
author integer,
foreign key (author) references authors(id)
);