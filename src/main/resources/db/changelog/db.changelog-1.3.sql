--changeset artei:4

-- Создание ролей
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');
-- Создание пользователей
INSERT INTO users (name, password, email, phone_number, is_blocked)
VALUES ('Artei', '$2a$10$ASWIPRG.nbqtiW/oulgKkeOhkT8etJ2Y7A4NkC/Pff4WLODU2jItm', 'artei@example.com', '+491234567890', false),
       ('Ivan Petrov', '$2a$10$0ULkDpgcU3jGNESerKFu6exOWQ.qq2d9m.QX9UshbSCV7QXHoAE2C', 'ivan@example.com', '+491234567891', false);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), -- Artei ADMIN
       (2, 2); -- Ivan Petrov USER