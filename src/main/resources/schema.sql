CREATE TABLE cinema (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    likes INT,
    created_at timestamp NOT NULL DEFAULT current_timestamp
);

CREATE TABLE movie (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255),
     year INT,
     director VARCHAR(255),
     duration VARCHAR(255),
     country VARCHAR(255),
     description TEXT,
     likes INT,
     cinema_id INT,
     FOREIGN KEY (cinema_id) REFERENCES cinema(id)
);
