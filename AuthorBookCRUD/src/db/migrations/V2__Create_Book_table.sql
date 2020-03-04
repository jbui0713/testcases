CREATE TABLE Book (
    isbn INT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    authorID INT NOT NULL,
    FOREIGN KEY (authorID) REFERENCES Author (authorID)
);
