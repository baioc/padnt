-- NOTE: varchar maximum lengths are based on Linux path limits; refer to
-- https://serverfault.com/questions/9546/filename-length-limits-on-linux
CREATE TABLE IF NOT EXISTS notepads (
	directory  VARCHAR(3840),
	filename   VARCHAR(255),
	content    TEXT NOT NULL,
	PRIMARY KEY (directory, filename)
);
