insert into desafio_java.users (created_at, updated_at, id, email, password, user_role)
select '2023-12-22 20:34:35.291000', null, 0xFF2371A2F92D46CAABA72E8947972C10, 'admin@adm.com',
        '$2a$10$i6TpXJ/ypJE8xB5nEX6Cu.Cif34wQwI1EYJ3uYVtsvTKq61r7zPbe', 'ADMIN'
where not exists(select * from users where email like 'admin@adm.com');