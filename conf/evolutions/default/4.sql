-- 投稿お気に入り

# --- !Ups
CREATE TABLE statuses_favorites (
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  character_id VARCHAR(20) NOT NULL,
  status_id INT NOT NULL,
  FOREIGN KEY (character_id) REFERENCES characters(id),
  FOREIGN KEY (status_id) REFERENCES statuses(id)
);

# --- !Downs
drop table statuses_favorites
