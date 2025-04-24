--changeset artei:1
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       role VARCHAR(50),
                       is_blocked BOOLEAN DEFAULT FALSE
);

CREATE TABLE hotels (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        location VARCHAR(255),
                        description TEXT
);

CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       room_type VARCHAR(255),
                       price NUMERIC,
                       room_photo_url TEXT,
                       room_description TEXT,
                       hotel_id BIGINT REFERENCES hotels(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                          check_in_date TIMESTAMP,
                          check_out_date TIMESTAMP,
                          status VARCHAR(50),
                          num_of_adults INT NOT NULL DEFAULT 1,
                          num_of_children INT NOT NULL DEFAULT 0
);

CREATE TABLE booking_rooms (
                               id BIGSERIAL PRIMARY KEY,
                               booking_id BIGINT REFERENCES bookings(id) ON DELETE CASCADE,
                               room_id BIGINT REFERENCES rooms(id) ON DELETE CASCADE
);

CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                         hotel_id BIGINT REFERENCES hotels(id) ON DELETE CASCADE,
                         message TEXT
);

CREATE INDEX idx_booking_user_id ON bookings(user_id);
CREATE INDEX idx_review_user_id ON reviews(user_id);
CREATE INDEX idx_booking_room_booking_id ON booking_rooms(booking_id);
CREATE INDEX idx_booking_room_room_id ON booking_rooms(room_id);
CREATE INDEX idx_room_hotel_id ON rooms(hotel_id);
CREATE INDEX idx_review_hotel_id ON reviews(hotel_id);