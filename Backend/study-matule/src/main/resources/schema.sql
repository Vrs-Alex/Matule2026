
-- Пользователи
CREATE TABLE app_user (
    user_id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    date_birthday VARCHAR(50) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    verified BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE refresh_token(
    token_id VARCHAR(255) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token_hash VARCHAR(512) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES app_user(user_id)
        ON DELETE CASCADE
);


CREATE TABLE banner(
    banner_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL
);


CREATE TABLE category(
    category_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);


CREATE TABLE product(
    product_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES category (category_id)
);

CREATE TABLE cart_item (
    cart_item_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    CONSTRAINT uk_user_product UNIQUE (user_id, product_id),
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE project (
    project_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    start_date VARCHAR(255) NOT NULL,
    end_date VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_user
        FOREIGN KEY (user_id)
            REFERENCES app_user (user_id)
);