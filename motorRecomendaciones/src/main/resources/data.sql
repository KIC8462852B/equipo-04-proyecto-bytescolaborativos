-- ================================
--              USERS
-- ================================
INSERT INTO users (id, username, email, password, created_at)
VALUES
  ('11111111-1111-1111-1111-111111111111', 'sofia_gamer', 'sofia@example.com', 'password123', NOW()),
  ('22222222-2222-2222-2222-222222222222', 'carlos_pro', 'carlos@example.com', 'password123', NOW()),
  ('33333333-3333-3333-3333-333333333333', 'luis_fps',  'luis@example.com', 'password123', NOW());

-- ================================
--          USER ROLES
-- ================================
INSERT INTO user_roles (user_id, role) VALUES
  ('11111111-1111-1111-1111-111111111111', 'PLAYER'),
  ('22222222-2222-2222-2222-222222222222', 'PLAYER'),
  ('22222222-2222-2222-2222-222222222222', 'ADMIN'),
  ('33333333-3333-3333-3333-333333333333', 'PLAYER');


-- ================================
--             PRODUCTS
-- ================================
INSERT INTO products (id, name, description, category, popularity_score)
VALUES
  ('44444444-4444-4444-4444-444444444444', 'Teclado Mecánico HyperX Alloy FPS Pro',
   'Teclado mecánico compacto con switches Cherry MX Red, ideal para juegos FPS.',
   'perifericos', 95),

  ('55555555-5555-5555-5555-555555555555', 'Ratón Logitech G502 HERO',
   'Ratón gaming con sensor HERO 25K y 11 botones programables.',
   'perifericos', 120),

  ('66666666-6666-6666-6666-666666666666', 'Silla Gaming Drift DR300',
   'Silla ergonómica con reposabrazos 4D y diseño profesional.',
   'mobiliario', 80),

  ('77777777-7777-7777-7777-777777777777', 'Auriculares Razer Kraken X',
   'Auriculares ultraligeros con sonido envolvente 7.1.',
   'audio', 70);


-- ================================
--          PRODUCT TAGS
-- ================================
INSERT INTO product_tags (product_id, tag) VALUES
  -- Teclado HyperX
  ('44444444-4444-4444-4444-444444444444', 'teclado'),
  ('44444444-4444-4444-4444-444444444444', 'mecanico'),
  ('44444444-4444-4444-4444-444444444444', 'fps'),
  ('44444444-4444-4444-4444-444444444444', 'compacto'),

  -- Logitech G502
  ('55555555-5555-5555-5555-555555555555', 'raton'),
  ('55555555-5555-5555-5555-555555555555', 'programable'),
  ('55555555-5555-5555-5555-555555555555', 'dpi25k'),
  ('55555555-5555-5555-5555-555555555555', 'fps'),

  -- Silla gaming
  ('66666666-6666-6666-6666-666666666666', 'silla'),
  ('66666666-6666-6666-6666-666666666666', 'ergonomica'),
  ('66666666-6666-6666-6666-666666666666', 'drift'),

  -- Auriculares Kraken X
  ('77777777-7777-7777-7777-777777777777', 'auriculares'),
  ('77777777-7777-7777-7777-777777777777', '7.1'),
  ('77777777-7777-7777-7777-777777777777', 'ligeros');


-- ================================
--             RATINGS
-- ================================
INSERT INTO ratings (id, user_id, product_id, score, created_at)
VALUES
  ('88888888-8888-8888-8888-888888888888',
   '11111111-1111-1111-1111-111111111111',
   '44444444-4444-4444-4444-444444444444',
   5, NOW()),

  ('99999999-9999-9999-9999-999999999999',
   '11111111-1111-1111-1111-111111111111',
   '55555555-5555-5555-5555-555555555555',
   4, NOW()),

  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
   '33333333-3333-3333-3333-333333333333',
   '55555555-5555-5555-5555-555555555555',
   5, NOW());


-- ================================
--         RECOMMENDATIONS
-- ================================
INSERT INTO recommendations (id, user_id, generated_at, algorithm_version)
VALUES
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
   '11111111-1111-1111-1111-111111111111',
   NOW(), 'v1'),
  
  ('cccccccc-cccc-cccc-cccc-cccccccccccc',
   '33333333-3333-3333-3333-333333333333',
   NOW(), 'v1');


-- ================================
--   RECOMMENDATION_PRODUCTS (N:M)
-- ================================
INSERT INTO recommendation_products (recommendation_id, product_id) VALUES

  -- Recomendaciones para SOFÍA
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '44444444-4444-4444-4444-444444444444'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '55555555-5555-5555-5555-555555555555'),

  -- Recomendaciones para LUIS (FPS player)
  ('cccccccc-cccc-cccc-cccc-cccccccccccc', '44444444-4444-4444-4444-444444444444'),
  ('cccccccc-cccc-cccc-cccc-cccccccccccc', '55555555-5555-5555-5555-555555555555');
