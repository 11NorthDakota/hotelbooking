--changeset artei:4

INSERT INTO users (name, password, email, phone_number, role, is_blocked) VALUES
                                                                              ('Alice Ivanova', 'password1', 'alice@example.com', '1234567890', 'USER', false),
                                                                              ('Bob Petrov', 'password2', 'bob@example.com', '1234567891', 'USER', false),
                                                                              ('Charlie Smirnov', 'password3', 'charlie@example.com', '1234567892', 'USER', false),
                                                                              ('Diana Orlova', 'password4', 'diana@example.com', '1234567893', 'USER', false),
                                                                              ('Edward Pavlov', 'password5', 'edward@example.com', '1234567894', 'ADMIN', false);