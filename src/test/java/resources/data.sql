INSERT INTO hotels (name, location, description) VALUES
                                                     ('Grand Central Hotel', 'Нью-Йорк, США', 'Роскошный отель в центре Манхэттена с великолепным видом на город.'),
                                                     ('Oceanview Resort', 'Майами, США', 'Курортный отель с видом на океан и прямым выходом на пляж.'),
                                                     ('Mountain Escape Lodge', 'Аспен, США', 'Уютный отель в горах, идеальный для любителей лыжного спорта.'),
                                                     ('City Lights Hotel', 'Токио, Япония', 'Современный отель в центре города с панорамным видом на мегаполис.'),
                                                     ('Desert Oasis Inn', 'Дубай, ОАЭ', 'Элегантный отель в пустыне с роскошными удобствами.'),
                                                     ('Lakeview Retreat', 'Женева, Швейцария', 'Отель на берегу озера с потрясающими видами и спокойной атмосферой.'),
                                                     ('Historic Charm Hotel', 'Прага, Чехия', 'Отель в историческом здании с уникальной архитектурой.'),
                                                     ('Seaside Paradise', 'Сидней, Австралия', 'Отель на побережье с прямым доступом к пляжу и видом на океан.'),
                                                     ('Urban Chic Hotel', 'Лондон, Великобритания', 'Стильный отель в центре города, окруженный магазинами и ресторанами.'),
                                                     ('Northern Lights Inn', 'Рейкьявик, Исландия', 'Отель, идеально подходящий для наблюдения северного сияния.');
-- Grand Central Hotel
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (250.00, 'Стандартный номер', 'https://www.pexels.com/photo/bright-and-airy-modern-bedroom-in-istanbul-with-minimalist-design-and-luxury-amenities-12312345/', 'Уютный номер с одной двуспальной кроватью и видом на город.', 1),
                                                                                     (400.00, 'Люкс', 'https://www.pexels.com/photo/elegant-bedroom-with-purple-decor-modern-lighting-and-mirror-wardrobe-67891011/', 'Просторный люкс с гостиной зоной и панорамным видом на Манхэттен.', 1);

-- Oceanview Resort
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (300.00, 'Стандартный номер', 'https://www.pexels.com/photo/cozy-and-modern-hotel-bedroom-interior-with-elegant-decor-and-soft-lighting-11223344/', 'Номер с балконом и видом на океан.', 2),
                                                                                     (500.00, 'Люкс', 'https://www.pexels.com/photo/spacious-modern-hotel-room-featuring-cozy-decor-and-neutral-tones-with-natural-light-55667788/', 'Люкс с отдельной гостиной и прямым выходом на пляж.', 2);

-- Mountain Escape Lodge
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (200.00, 'Стандартный номер', 'https://www.pexels.com/photo/stylish-bedroom-featuring-wooden-furniture-and-soft-lighting-for-a-warm-ambiance-33445566/', 'Номер с видом на горы и камином.', 3),
                                                                                     (350.00, 'Люкс', 'https://www.pexels.com/photo/contemporary-hotel-room-with-minimalist-interior-design-and-cozy-lighting-77889900/', 'Просторный люкс с отдельной гостиной и джакузи.', 3);

-- City Lights Hotel
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (220.00, 'Стандартный номер', 'https://www.pexels.com/photo/modern-hotel-room-featuring-a-bed-laptop-and-stylish-decor-with-ample-lighting-44556677/', 'Современный номер с видом на городские огни.', 4),
                                                                                     (380.00, 'Люкс', 'https://www.pexels.com/photo/elegant-bedroom-setting-with-champagne-wine-glass-and-desserts-creating-a-cozy-ambiance-99887766/', 'Люкс с панорамными окнами и современной мебелью.', 4);

-- Desert Oasis Inn
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (270.00, 'Стандартный номер', 'https://www.pexels.com/photo/peaceful-bedroom-in-tropical-villa-with-balcony-view-perfect-for-relaxation-22334455/', 'Номер с видом на пустыню и современным дизайном.', 5),
                                                                                     (450.00, 'Люкс', 'https://www.pexels.com/photo/luxurious-bedroom-suite-in-resort-high-rise-hotel-with-cushion-66778899/', 'Люкс с частным бассейном и террасой.', 5);

-- Lakeview Retreat
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (240.00, 'Стандартный номер', 'https://www.pexels.com/photo/minimalist-bedroom-with-large-windows-offering-a-breathtaking-mountain-view-during-sunset-11224488/', 'Номер с балконом и видом на озеро.', 6),
                                                                                     (400.00, 'Люкс', 'https://www.pexels.com/photo/spacious-and-modern-bedroom-with-sleek-design-featuring-elegant-decor-and-natural-lighting-33445599/', 'Люкс с отдельной гостиной и камином.', 6);

-- Historic Charm Hotel
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
                                                                                     (230.00, 'Стандартный номер', 'https://www.pexels.com/photo/a-cozy-and-stylish-bedroom-featuring-modern-decor-and-a-sunlit-window-with-shutter-panels-55667744/', 'Номер с антикварной мебелью и видом на старый город.', 7),
                                                                                     (370.00, 'Люкс', 'https://www.pexels.com/photo/stylish-bedroom-interior-featuring-a-rustic-chandelier-cozy-bedding-and-elegant-wooden-furniture-77889911/', 'Просторный люкс с историческими элементами декора.', 7);

-- Seaside Paradise
INSERT INTO rooms (price, room_type, room_photo_url, room_description, hotel_id) VALUES
    (280
        ::contentReference[oaicite:0]{index=0}

