--changeset artei:5
INSERT INTO bookings (user_id, check_in_date, check_out_date, status, num_of_adults, num_of_children) VALUES
                                                                                                          (1, '2025-05-01 14:00:00', '2025-05-05 12:00:00', 'CONFIRMED', 2, 1),
                                                                                                          (2, '2025-06-10 14:00:00', '2025-06-15 12:00:00', 'CONFIRMED', 1, 0),
                                                                                                          (3, '2025-07-01 14:00:00', '2025-07-03 12:00:00', 'CANCELLED', 2, 2),
                                                                                                          (4, '2025-08-15 14:00:00', '2025-08-20 12:00:00', 'PENDING', 1, 1),
                                                                                                          (5, '2025-09-10 14:00:00', '2025-09-12 12:00:00', 'CONFIRMED', 2, 0);
