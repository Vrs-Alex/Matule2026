-- 1. Пользователи (Пароль везде: 'Test-1234' - хешированный для примера)
INSERT INTO app_user (email, password_hash, firstname, lastname, patronymic, date_birthday, gender, verified, created_at, updated_at)
VALUES
    ('test@example.com', '$2a$10$hq6MsizP7Z3MLsDZ9pAi1OLG4u/rZthaW.cWYAmR1D5kciPydWPFC', 'Алексей', 'Варламов', 'Сергеевич', '1995-05-15', 'Мужской', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('user@matule.ru', '$2a$10$hq6MsizP7Z3MLsDZ9pAi1OLG4u/rZthaW.cWYAmR1D5kciPydWPFC', 'Иван', 'Иванов', 'Иванович', '2000-01-01', 'Мужской', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. Категории товаров
INSERT INTO category (name, description)
VALUES
    ('Мужчинам', 'Мужская одежда'),
    ('Женщинам', 'Женская одежда');

-- 3. Баннеры для главной страницы
INSERT INTO banner (title, price, image)
VALUES
    ('Шорты Вторник', '4000 ₽', 'banner1.png'),
    ('Рубашка Воскресенье', '8000 ₽', 'banner2.png');

-- 4. Товары (Products)
INSERT INTO product (name, subtitle, price, description, category_id)
VALUES
    ('Рубашка Воскресенье для машинного вязания', 'Мужская одежда', '500', 'Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку. Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько. Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г. Артикулы, из которых мы вязали эту модель: Zermatt Zegna Baruffa, Cashfive, Baby Cashmere Loro Piana, Soft Donegal и другие. Примерный расход на шапку с подгибом 70-90г.', 1),
    ('Adidas Ultraboost 5.0', 'Running Performance', '15900', 'Мой выбор для этих шапок – кардные составы, которые раскрываются деликатным пушком. Кашемиры, мериносы, смесовки с ними отлично подойдут на шапку. Кардные составы берите в большое количество сложений, вязать будем резинку 1х1, плотненько. Пряжу 1400-1500м в 100г в 4 сложения, пряжу 700м в 2 сложения. Ориентир для конечной толщины – 300-350м в 100г. ', 2),
    ('Puma RS-X3', 'Retro Style', '9800', 'Массивный силуэт в стиле 80-х с современными материалами.', 1),
    ('LeBron XX', 'Basketball Elite', '19500', 'Именная модель Леброна Джеймса для взрывной игры.', 1),
    ('ASICS Gel-Kayano 29', 'Stability Running', '14200', 'Кроссовки с поддержкой стопы для длительных дистанций.', 2);


INSERT INTO cart_item (user_id, product_id, quantity)
VALUES
    (1, 1, 1), -- 1 пара Nike Air Max
    (2, 3, 2),
    (1, 3, 2); -- 2 пары Puma (себе и другу)
