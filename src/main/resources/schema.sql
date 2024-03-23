CREATE TABLE cinema (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255),
    capacities VARCHAR(255)
);

CREATE TABLE movie (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255),
     movie_year INT,
     director VARCHAR(255),
     duration VARCHAR(255),
     country VARCHAR(255),
     description VARCHAR(500)
);

CREATE TABLE movie_sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cinema_id INT,
    movie_id INT,
    show_time timestamp,
    FOREIGN KEY (cinema_id) REFERENCES cinema(id),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE seats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seat_row INT,
    place INT,
    seat_status VARCHAR(255) DEFAULT 'AVAILABLE',
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES movie_sessions(id)
);

CREATE TABLE orders (
     id INT AUTO_INCREMENT PRIMARY KEY,
     number_of_tickets INT,
     totalPrice INT,
     status VARCHAR(255),
     created_at timestamp,
     updated_at timestamp,
     session_id INT,
     FOREIGN KEY (session_id) REFERENCES movie_sessions(id)
);
