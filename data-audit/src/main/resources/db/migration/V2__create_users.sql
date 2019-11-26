CREATE TABLE if not exists users(
  id SERIAL PRIMARY KEY ,
  created_time       timestamp  not null ,
  updated_time       timestamp  not null ,
  name           VARCHAR (255),
  password           VARCHAR (255),
  email              VARCHAR (255) unique,
  username           VARCHAR (255),
  roles              VARCHAR (355)
);
