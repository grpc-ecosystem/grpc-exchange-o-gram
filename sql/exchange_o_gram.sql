CREATE DATABSE `exchange-o-gram`;

CREATE TABLE media (
  id INT64 NOT NULL,
  data BYTES(MAX) NOT NULL,
  mimetype STRING(32) NOT NULL,
) PRIMARY KEY(id);

CREATE TABLE wall_post (
  id INT64 NOT NULL,
  username STRING(64) NOT NULL,
  caption STRING(MAX) NOT NULL,
  media_id INT64 NOT NULL,
) PRIMARY KEY(id);

CREATE INDEX PostsByUsername ON wall_post(username) STORING (caption, media_id)

