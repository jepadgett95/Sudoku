CREATE TABLE puzzles (
    puzzle_id SERIAL PRIMARY KEY,
    puzzle_text TEXT[] NOT NULL,
    puzzle_solution TEXT[] NOT NULL,
    puzzle_name TEXT UNIQUE
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username TEXT UNIQUE
);

CREATE TABLE user_puzzles (
    user_id SERIAL,
    puzzle_id SERIAL,
    user_solution TEXT[],
    solved BOOLEAN,
    CONSTRAINT user_puzzle_pk PRIMARY KEY (user_id, puzzle_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_puzzle FOREIGN KEY (puzzle_id) REFERENCES puzzles (puzzle_id)
);
