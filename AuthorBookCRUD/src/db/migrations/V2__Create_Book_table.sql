CREATE TABLE Book (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    authorID VARCHAR(100) NOT NULL,
    FOREIGN KEY (authorID) REFERENCES Author (authorID)
);
