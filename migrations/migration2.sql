CREATE TABLE events (
  event_id SERIAL PRIMARY KEY,
  author_id INT,
  summary VARCHAR(255) NOT NULL,
  description VARCHAR(3000),
  time_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  time_start TIMESTAMP NOT NULL,
  all_day BOOLEAN,
  tags VARCHAR(255),
  CONSTRAINT fk_author
    FOREIGN KEY(author_id)
      REFERENCES users(id)
      ON DELETE SET NULL
);
