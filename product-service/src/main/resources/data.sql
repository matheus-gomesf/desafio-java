
insert into users (created_at, updated_at, id, email, password, user_role)
select '2023-12-22 20:34:35.291000', null, 0xFF2371A2F92D46CAABA72E8947972C10, 'admin@adm.com',
        '$2a$10$i6TpXJ/ypJE8xB5nEX6Cu.Cif34wQwI1EYJ3uYVtsvTKq61r7zPbe', 'ADMIN'
where not exists(select * from users where email like 'admin@adm.com');

INSERT INTO products (id, category, name, price)
VALUES (0x22C2638CC59E4B1BA450DB4C8A7C6A2B, 4, 'Brigadeiro', 200);
INSERT INTO products (id, category, name, price)
VALUES (0x2423801CE42744B9B34076D655150F6E, 2, 'Batata frita', 300);
INSERT INTO products (id, category, name, price)
VALUES (0x569F5AB56B2E4AD7AB76386F0091F41E, 1, 'Agua', 400);
INSERT INTO products (id, category, name, price)
VALUES (0xA2A363AD26CE4AC699C857298A349100, 1, 'Coca 2litros', 1000);

INSERT INTO orders (id, total_price) VALUES (0x293D9F2EDD2F43D09EFC7B5FC468D14D, 1200);

INSERT INTO orders_products (order_entity_id, products_id) VALUES (0x293D9F2EDD2F43D09EFC7B5FC468D14D, 0x22C2638CC59E4B1BA450DB4C8A7C6A2B);
INSERT INTO orders_products (order_entity_id, products_id) VALUES (0x293D9F2EDD2F43D09EFC7B5FC468D14D, 0xA2A363AD26CE4AC699C857298A349100);

