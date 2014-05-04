DROP TABLE PUBLIC.PERSON IF EXISTS;
CREATE TABLE PUBLIC.PERSON (
  id INTEGER NOT NULL IDENTITY,
  first_name varchar(255),
  last_name varchar(255) NOT NULL,
  active_ind varchar(1) NOT NULL,
  activity_dt_tm DATE NOT NULL
); 