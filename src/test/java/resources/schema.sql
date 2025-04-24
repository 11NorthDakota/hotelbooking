--changeset artei:1
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255),
                       name VARCHAR(255),
                       password VARCHAR(255),
                       phone_number VARCHAR(255),
                       role VARCHAR(255) CHECK (role IN ('USER', 'ADMIN')),
                       is_blocked BOOLEAN NOT NULL
);

-- Таблица отелей
CREATE TABLE hotels (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        location VARCHAR(255),
                        description VARCHAR(255)
);

-- Таблица бронирований
CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT,
                          check_in_date TIMESTAMP(6),
                          check_out_date TIMESTAMP(6),
                          num_of_adults INTEGER,
                          num_of_children INTEGER,
                          status VARCHAR(255) CHECK (status IN ('NEW', 'ACTIVE', 'CLOSE')),
                          CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Таблица комнат
CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       price NUMERIC(38,2),
                       room_type VARCHAR(255),
                       room_photo_url VARCHAR(255),
                       room_description VARCHAR(255),
                       booking_id BIGINT,
                       hotel_id BIGINT,
                       CONSTRAINT fk_rooms_booking FOREIGN KEY (booking_id) REFERENCES bookings(id),
                       CONSTRAINT fk_rooms_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

-- Таблица отзывов
CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         hotel_id BIGINT,
                         user_id BIGINT,
                         message VARCHAR(255),
                         CONSTRAINT fk_reviews_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(id),
                         CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users(id)
);
