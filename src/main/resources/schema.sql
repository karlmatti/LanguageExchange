create table user
(
  id integer not null,
  firstname varchar(255) not null,
  lastname varchar(255) not null,
  languages varchar(255) not null,
  learninglanguages varchar(255),
  hobbies varchar(255)
  primary key(id)
);

insert into user values (
  114005839740106632529,
  'Karl',
  'Matti',
  'Estonian',
  'Russian',
  'Fishing'
);