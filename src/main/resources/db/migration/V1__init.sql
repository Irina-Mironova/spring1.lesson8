CREATE TABLE categories
(
    id bigserial primary key,
    title varchar(255)
);

insert into categories (title) values ('Рыба'), ('Сыр'),('Фрукты'),('Овощи'),('Мясо');


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       float,
    category_id bigint references categories (id)
);

insert into products (title, price, category_id)
values ('Горбуша', 250, 1),
       ('Кета', 300, 1),
       ('Сыр Гауда', 210, 2),
       ('Сыр Голландский', 160, 2),
       ('Дыня', 100, 3),
       ('Арбуз', 55, 3),
       ('Слива', 280, 3),
       ('Ананас',100 , 3);