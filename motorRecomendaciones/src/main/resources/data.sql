-- =================================================================================================
-- 1. USUARIOS (Users)
-- Password para todos es: "password" (Hash BCrypt)
-- =================================================================================================
INSERT INTO users (id, username, password, email, created_at) VALUES
                                                                  ('5fd07071-9785-4ae9-9bb4-c3dc71796089', 'admin_user', '$2a$12$vzV/8bj2g/s8arzqfUZzJuvC73/oSTZK.grB1G6/w6Lx2zLpjFv3i', 'admin@test.com', NOW()),
                                                                  ('576ad124-1e0e-4298-aa68-52e062bd6eb5', 'juan_perez', '$2a$12$vzV/8bj2g/s8arzqfUZzJuvC73/oSTZK.grB1G6/w6Lx2zLpjFv3i', 'juan@test.com', NOW()),
                                                                  ('51cbd765-db6e-493e-9d7b-cfb98e3939c6', 'maria_dev', '$2a$12$vzV/8bj2g/s8arzqfUZzJuvC73/oSTZK.grB1G6/w6Lx2zLpjFv3i', 'maria@test.com', NOW());

-- ROLES DE USUARIO (ElementCollection)
INSERT INTO user_roles (user_id, role) VALUES
                                           ('5fd07071-9785-4ae9-9bb4-c3dc71796089', 'ADMIN'),
                                           ('5fd07071-9785-4ae9-9bb4-c3dc71796089', 'PLAYER'),
                                           ('576ad124-1e0e-4298-aa68-52e062bd6eb5', 'PLAYER'),
                                           ('51cbd765-db6e-493e-9d7b-cfb98e3939c6', 'PLAYER');

-- =================================================================================================
-- 2. PRODUCTOS (Products)
-- =================================================================================================
INSERT INTO products (id, name, description, category, popularity_score) VALUES
                                                                             ('d15b79bd-ca03-48fd-8d52-a22e6b9aaf6b', 'Laptop Gamer Xtreme', 'Laptop de alto rendimiento para gaming y desarrollo', 'Electronics', 95),
                                                                             ('1d2aeabb-0292-4255-9631-59bbfea40a62', 'Mouse Ergonómico', 'Mouse vertical para evitar tunel carpiano', 'Accessories', 70),
                                                                             ('759877e5-49bb-44f4-ba5d-e9ff3f204767', 'Monitor 4K Ultra', 'Monitor de 27 pulgadas con colores calibrados', 'Electronics', 88),
                                                                             ('a3014224-b357-4667-8e54-6a4704aa86c3', 'Teclado Mecánico RGB', 'Switches azules y retroiluminación', 'Accessories', 82);

-- ETIQUETAS DE PRODUCTOS (ElementCollection)
INSERT INTO product_tags (product_id, tag) VALUES
                                               ('d15b79bd-ca03-48fd-8d52-a22e6b9aaf6b', 'Gaming'),
                                               ('d15b79bd-ca03-48fd-8d52-a22e6b9aaf6b', 'Portable'),
                                               ('1d2aeabb-0292-4255-9631-59bbfea40a62', 'Salud'),
                                               ('1d2aeabb-0292-4255-9631-59bbfea40a62', 'Oficina'),
                                               ('759877e5-49bb-44f4-ba5d-e9ff3f204767', 'Diseño'),
                                               ('a3014224-b357-4667-8e54-6a4704aa86c3', 'Gaming');

-- =================================================================================================
-- 3. RATINGS
-- =================================================================================================
INSERT INTO ratings (id, user_id, product_id, score, computed_at) VALUES
-- Juan califica la Laptop con 5
('5f77afd0-ae01-459a-8715-b691cb4e1890', '576ad124-1e0e-4298-aa68-52e062bd6eb5', 'd15b79bd-ca03-48fd-8d52-a22e6b9aaf6b', 5, NOW()),
-- Juan califica el Mouse con 4
('9fe9d6e0-3c43-4797-a192-162228bdd2fd', '576ad124-1e0e-4298-aa68-52e062bd6eb5', '1d2aeabb-0292-4255-9631-59bbfea40a62', 4, NOW()),
-- Maria califica el Monitor con 5
('0b29ad6d-ef8d-4cbf-85b0-489b9bd7b974', '51cbd765-db6e-493e-9d7b-cfb98e3939c6', '759877e5-49bb-44f4-ba5d-e9ff3f204767', 5, NOW());

-- =================================================================================================
-- 4. RECOMENDACIONES (Recommendations)
-- =================================================================================================
INSERT INTO recommendations (id, user_id, computed_at, algorithm_version) VALUES
-- Una recomendación para Juan
('799cefca-d196-41b1-9c7e-17dfb3feca21', '576ad124-1e0e-4298-aa68-52e062bd6eb5', NOW(), 'v1.0-collaborative-filtering'),
-- Una recomendación para Maria
('25fb9e39-bf9b-4218-9323-687a8166ad3b', '51cbd765-db6e-493e-9d7b-cfb98e3939c6', NOW(), 'v2.0-content-based');

-- PRODUCTOS RECOMENDADOS (Tabla Intermedia N:M)
INSERT INTO recommendation_products (recommendation_id, product_id) VALUES
-- A Juan (que le gustó la laptop) le recomendamos el Monitor y el Teclado
('799cefca-d196-41b1-9c7e-17dfb3feca21', '759877e5-49bb-44f4-ba5d-e9ff3f204767'),
('799cefca-d196-41b1-9c7e-17dfb3feca21', 'a3014224-b357-4667-8e54-6a4704aa86c3'),
-- A Maria le recomendamos el Mouse
('25fb9e39-bf9b-4218-9323-687a8166ad3b', '1d2aeabb-0292-4255-9631-59bbfea40a62');

-- =================================================================================================
-- 5. TORNEOS (Tournaments)
-- =================================================================================================
INSERT INTO tournaments (id, name, start_date, end_date, registration_open_at, registration_close_at, status, created_at) VALUES
                                                                                                                              ('56b5ed23-deb3-4229-9ec5-a2b2d1fedb9a', 'Copa Verano Java', '2025-12-01 10:00:00', '2025-12-05 18:00:00', '2025-11-01 00:00:00', '2025-11-30 23:59:59', 'OPEN', NOW()),
                                                                                                                              ('f774ed76-e158-4055-913e-591f2a2d4206', 'Hackathon Spring Boot', '2026-01-15 09:00:00', '2026-01-17 20:00:00', '2025-12-01 00:00:00', '2026-01-10 23:59:59', 'UPCOMING', NOW());