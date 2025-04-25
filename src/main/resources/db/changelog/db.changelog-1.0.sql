-- changeset artei:1
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       is_blocked BOOLEAN NOT NULL
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255)
);

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL,
                             role_id INTEGER NOT NULL,
                             CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE hotels (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        location VARCHAR(255),
                        description VARCHAR(255)
);

CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       room_type VARCHAR(255),
                       price NUMERIC(38,2),
                       room_photo_url VARCHAR(255),
                       room_description VARCHAR(255),
                       hotel_id BIGINT,
                       CONSTRAINT fk_rooms_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE
);

CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT,
                          check_in_date TIMESTAMP,
                          check_out_date TIMESTAMP,
                          status VARCHAR(255) CHECK (status IN ('NEW','ACTIVE','CANCELLED','CONFIRMED','PENDING')),
                          num_of_adults INTEGER,
                          num_of_children INTEGER,
                          CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE booking_rooms (
                               id BIGSERIAL PRIMARY KEY,
                               booking_id BIGINT,
                               room_id BIGINT,
                               CONSTRAINT fk_booking_rooms_booking FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE,
                               CONSTRAINT fk_booking_rooms_room FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT,
                         hotel_id BIGINT,
                         message VARCHAR(255),
                         CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                         CONSTRAINT fk_reviews_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE
);

CREATE INDEX idx_booking_user_id ON bookings(user_id);
CREATE INDEX idx_review_user_id ON reviews(user_id);
CREATE INDEX idx_review_hotel_id ON reviews(hotel_id);
CREATE INDEX idx_booking_room_booking_id ON booking_rooms(booking_id);
CREATE INDEX idx_booking_room_room_id ON booking_rooms(room_id);
CREATE INDEX idx_room_hotel_id ON rooms(hotel_id);
