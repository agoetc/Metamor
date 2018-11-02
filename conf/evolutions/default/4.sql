-- ワールド（イベント）

# --- !Ups
CREATE TABLE worlds(
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  creator_id BIGINT NOT NULL,
  detail VARCHAR(255) NOT NULL,
  started_at DATETIME,
  ended_at DATETIME,
  emblem_id BIGINT NOT NULL,
  FOREIGN KEY (creator_id) REFERENCES creators(id),
  FOREIGN KEY (emblem_id) REFERENCES emblems(id)
);

# --- !Downs
DROP TABLE worlds;
