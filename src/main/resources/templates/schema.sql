DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE user
(
  id BIGINT NOT NULL PRIMARY KEY,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  languages VARCHAR(255) NOT NULL,
  learninglanguages VARCHAR(255),
  hobbies VARCHAR(255)
);

INSERT INTO user(
id, firstname,
lastname, languages,
learninglanguages, hobbies)
VALUES (
  114005839740106632529, 'Karl',
  'Matti', 'Estonian',
  'Russian', 'Fishing'
);
//