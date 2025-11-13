-- ============================================
-- Script de Datos de Ejemplo - PostgreSQL
-- Sistema de Recomendaciones con Ratings
-- Generado: 2025-11-13 11:40:07
-- ============================================

-- Deshabilitar triggers temporalmente para inserción masiva
SET session_replication_role = replica;

BEGIN;

-- ============================================
-- 1️⃣ Insertar Usuarios (30 registros)
-- ============================================
INSERT INTO "User" (username, email, created_at) VALUES ('carlos_dev', 'carlos_dev@example.com', '2025-03-22 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('maria_tech', 'maria_tech@example.com', '2024-11-29 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('juan_code', 'juan_code@example.com', '2024-12-15 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('laura_systems', 'laura_systems@example.com', '2025-10-10 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('pedro_backend', 'pedro_backend@example.com', '2025-03-14 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('ana_frontend', 'ana_frontend@example.com', '2025-09-16 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('luis_cloud', 'luis_cloud@example.com', '2025-07-31 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('sofia_data', 'sofia_data@example.com', '2025-09-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('diego_mobile', 'diego_mobile@example.com', '2024-11-18 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('carmen_ai', 'carmen_ai@example.com', '2025-04-25 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('miguel_devops', 'miguel_devops@example.com', '2025-01-29 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('elena_ux', 'elena_ux@example.com', '2025-04-11 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('javier_security', 'javier_security@example.com', '2025-07-02 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('paula_ml', 'paula_ml@example.com', '2025-02-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('antonio_db', 'antonio_db@example.com', '2025-08-14 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('isabel_architect', 'isabel_architect@example.com', '2025-05-15 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('roberto_tester', 'roberto_tester@example.com', '2025-05-31 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('marta_scrum', 'marta_scrum@example.com', '2025-03-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('fernando_kotlin', 'fernando_kotlin@example.com', '2025-01-12 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('raquel_java', 'raquel_java@example.com', '2024-12-09 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('alberto_python', 'alberto_python@example.com', '2025-04-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('cristina_react', 'cristina_react@example.com', '2025-03-23 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('manuel_angular', 'manuel_angular@example.com', '2024-12-05 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('beatriz_vue', 'beatriz_vue@example.com', '2025-08-14 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('sergio_node', 'sergio_node@example.com', '2025-02-12 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('natalia_golang', 'natalia_golang@example.com', '2025-06-19 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('francisco_rust', 'francisco_rust@example.com', '2024-12-07 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('victoria_swift', 'victoria_swift@example.com', '2025-04-20 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('adrian_php', 'adrian_php@example.com', '2025-04-19 11:40:07'::TIMESTAMPTZ);
INSERT INTO "User" (username, email, created_at) VALUES ('monica_ruby', 'monica_ruby@example.com', '2025-02-17 11:40:07'::TIMESTAMPTZ);

-- ============================================
-- 2️⃣ Insertar Productos (30 registros)
-- ============================================
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Laptop Gaming Pro', 'Perfecta para gaming y desarrollo profesional', 375.33, TRUE, '2025-03-30 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Teclado Mecánico RGB', 'Switches mecánicos con iluminación personalizable', 892.6, TRUE, '2025-01-23 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Mouse Inalámbrico Premium', 'Precisión máxima con sensor óptico de alta gama', 620.59, TRUE, '2025-06-03 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Monitor 4K UHD', 'Colores vibrantes y tiempo de respuesta de 1ms', 147.37, TRUE, '2025-08-25 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Auriculares Bluetooth', 'Cancelación de ruido activa y 30h de autonomía', 214.71, TRUE, '2025-10-03 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Webcam HD 1080p', 'Ideal para videoconferencias y streaming', 566.2, TRUE, '2025-03-25 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Micrófono USB Profesional', 'Calidad de audio profesional para podcasts', 1027.69, TRUE, '2025-04-25 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('SSD NVMe 1TB', 'Velocidad de lectura hasta 3500 MB/s', 1160.37, TRUE, '2025-08-29 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Memoria RAM 32GB DDR4', 'Optimizada para multitarea y gaming intenso', 138.68, TRUE, '2025-03-10 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Tarjeta Gráfica RTX', 'Ray tracing y DLSS para máximo rendimiento', 1174.73, TRUE, '2025-08-28 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Procesador Intel i9', '12 núcleos y frecuencia turbo de 5.3 GHz', 769.54, TRUE, '2025-07-24 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Placa Base Gaming', 'Chipset avanzado con overclocking automático', 142.95, TRUE, '2025-08-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Fuente Alimentación 850W', 'Certificación 80 Plus Gold para eficiencia', 515.29, TRUE, '2025-07-05 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Caja PC RGB', 'Diseño minimalista con ventiladores silenciosos', 859.44, TRUE, '2025-10-13 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Refrigeración Líquida', 'Sistema AIO de 360mm para temperaturas óptimas', 984.08, TRUE, '2025-08-20 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Hub USB-C', '7 puertos con carga rápida Power Delivery', 1288.26, FALSE, '2025-08-06 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Cable HDMI 2.1', 'Soporta 8K a 60Hz y HDR dinámico', 664.7, TRUE, '2025-05-04 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Alfombrilla Gaming XL', 'Superficie de tela premium, base antideslizante', 756.11, TRUE, '2025-08-10 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Soporte Monitor Ajustable', 'Altura y ángulo regulables, gestión de cables', 1246.55, TRUE, '2025-04-10 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Lámpara LED Escritorio', 'Temperatura de color ajustable, reduce fatiga visual', 499.14, TRUE, '2025-06-05 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Silla Gaming Ergonómica', 'Respaldo ergonómico con soporte lumbar ajustable', 725.58, TRUE, '2025-07-11 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Escritorio Elevable', 'Altura eléctrica programable, diseño moderno', 326.56, FALSE, '2025-06-27 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Router WiFi 6', 'Velocidad hasta 6 Gbps, cobertura 200m²', 1155.7, TRUE, '2025-10-09 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Switch Ethernet Gigabit', '8 puertos 1000 Mbps, plug and play', 670.71, FALSE, '2025-04-25 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Disco Duro Externo 4TB', 'USB 3.0, resistente a caídas, backup automático', 1285.24, FALSE, '2024-12-04 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Capturadora Video 4K', 'Graba hasta 4K HDR con latencia ultra baja', 1216.14, TRUE, '2025-02-22 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Controlador Gaming', 'Compatible con PC, consolas y móviles', 1269.88, TRUE, '2025-07-02 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Tablet Gráfica', '8192 niveles de presión, lápiz sin batería', 519.89, FALSE, '2025-05-04 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Impresora Multifunción', 'Impresión, escaneo y copia, WiFi integrado', 214.23, FALSE, '2025-03-14 11:40:07'::TIMESTAMPTZ);
INSERT INTO "Product" (name, description, price, is_active, created_at) VALUES ('Scanner Documentos', 'Alimentador automático de documentos, OCR', 586.43, FALSE, '2024-11-13 11:40:07'::TIMESTAMPTZ);

-- ============================================
-- 3️⃣ Insertar Ratings (100 registros)
-- ============================================
-- Nota: user_id y product_id se referencian por UUID generado automáticamente
-- En producción, deberías capturar los UUIDs después de insertar

-- Crear tabla temporal con IDs de usuarios
CREATE TEMP TABLE temp_users AS SELECT id, username FROM "User" ORDER BY created_at;
CREATE TEMP TABLE temp_products AS SELECT id, name FROM "Product" ORDER BY created_at;

-- Insertar ratings con referencias válidas
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    3,
    'Funciona pero no es lo que esperaba',
    '2025-08-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 12 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    2,
    'Funciona bien pero tiene margen de mejora',
    '2025-06-02 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 12 LIMIT 1),
    1,
    'Correcto para el precio',
    '2025-08-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 13 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 24 LIMIT 1),
    5,
    'Exactamente lo que necesitaba',
    '2025-07-20 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 24 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    5,
    'Por este precio no se puede pedir más',
    '2025-05-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    4,
    'Buena calidad precio, lo recomiendo',
    '2025-07-30 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 23 LIMIT 1),
    3,
    'Un poco caro pero merece la pena',
    '2025-08-12 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    4,
    'Fácil de configurar y usar',
    '2025-09-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 22 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    4,
    'Buena calidad precio, lo recomiendo',
    '2025-07-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    3,
    'Un poco caro pero merece la pena',
    '2025-09-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 3 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 4 LIMIT 1),
    5,
    'Funciona perfectamente, muy satisfecho',
    '2025-08-31 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 14 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 14 LIMIT 1),
    3,
    '',
    '2025-09-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 9 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    5,
    'Llegó rápido y bien empaquetado',
    '2025-08-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    3,
    'Buen rendimiento en general',
    '2025-08-25 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 10 LIMIT 1),
    2,
    'Precio elevado para lo que ofrece',
    '2025-06-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 4 LIMIT 1),
    3,
    'Precio elevado para lo que ofrece',
    '2025-07-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 27 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 7 LIMIT 1),
    5,
    'Cumple con lo prometido',
    '2025-07-28 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    1,
    'Funciona pero no es lo que esperaba',
    '2025-10-05 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    4,
    'Un poco caro pero merece la pena',
    '2025-09-30 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 9 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 4 LIMIT 1),
    4,
    'Vale cada euro invertido',
    '2025-10-13 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 24 LIMIT 1),
    4,
    'Llegó rápido y bien empaquetado',
    '2025-05-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 25 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 5 LIMIT 1),
    4,
    'Por este precio no se puede pedir más',
    '2025-06-02 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 5 LIMIT 1),
    2,
    'Precio elevado para lo que ofrece',
    '2025-07-31 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 20 LIMIT 1),
    4,
    'Diseño elegante y funcional',
    '2025-06-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 27 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 21 LIMIT 1),
    4,
    'Llegó rápido y bien empaquetado',
    '2025-10-04 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 3 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    3,
    'Diseño elegante y funcional',
    '2025-09-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    3,
    'No está mal pero esperaba algo más',
    '2025-09-29 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 8 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 19 LIMIT 1),
    2,
    'No está mal pero esperaba algo más',
    '2025-11-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 20 LIMIT 1),
    5,
    'Exactamente lo que necesitaba',
    '2025-06-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 21 LIMIT 1),
    5,
    'Muy buena construcción y materiales',
    '2025-10-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 13 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 13 LIMIT 1),
    4,
    'Calidad premium, se nota la diferencia',
    '2025-10-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 28 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 26 LIMIT 1),
    3,
    '',
    '2025-06-10 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 19 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    3,
    'Funciona perfectamente, muy satisfecho',
    '2025-06-05 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 25 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 7 LIMIT 1),
    4,
    'Diseño elegante y funcional',
    '2025-09-28 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 7 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 13 LIMIT 1),
    3,
    'Funciona perfectamente, muy satisfecho',
    '2025-10-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 17 LIMIT 1),
    5,
    'Calidad premium, se nota la diferencia',
    '2025-08-25 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    5,
    'Buena calidad precio, lo recomiendo',
    '2025-09-04 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 23 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    5,
    'Vale cada euro invertido',
    '2025-10-24 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    4,
    'Funciona perfectamente, muy satisfecho',
    '2025-06-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    2,
    'Podría ser mejor por este coste',
    '2025-08-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 8 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 0 LIMIT 1),
    5,
    'Cumple con lo prometido',
    '2025-08-21 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 19 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    1,
    'Podría ser mejor por este coste',
    '2025-08-13 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 16 LIMIT 1),
    5,
    'Llegó rápido y bien empaquetado',
    '2025-09-28 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 15 LIMIT 1),
    3,
    'Excelente producto, superó mis expectativas',
    '2025-07-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 9 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    2,
    'No cumplió completamente mis expectativas',
    '2025-05-18 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    5,
    'Buen rendimiento en general',
    '2025-08-23 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    5,
    'Excelente producto, superó mis expectativas',
    '2025-07-16 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 14 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    5,
    'Llegó rápido y bien empaquetado',
    '2025-09-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 14 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    4,
    'Vale cada euro invertido',
    '2025-06-23 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 15 LIMIT 1),
    4,
    'Diseño elegante y funcional',
    '2025-10-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 27 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    3,
    'Gran compra, muy contento',
    '2025-06-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    3,
    'Perfecto para mi setup',
    '2025-07-24 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    4,
    'Por este precio no se puede pedir más',
    '2025-08-07 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 14 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    3,
    '',
    '2025-08-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 23 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    4,
    'Buen rendimiento en general',
    '2025-08-08 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 6 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    3,
    'Buena calidad precio, lo recomiendo',
    '2025-08-10 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 9 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 0 LIMIT 1),
    5,
    'Muy buena construcción y materiales',
    '2025-06-14 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 26 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 20 LIMIT 1),
    4,
    'Vale cada euro invertido',
    '2025-05-20 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    4,
    'Diseño elegante y funcional',
    '2025-08-05 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 29 LIMIT 1),
    4,
    'Perfecto para mi setup',
    '2025-06-16 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 28 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 17 LIMIT 1),
    4,
    'Un poco caro pero merece la pena',
    '2025-07-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 15 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    4,
    'Exactamente lo que necesitaba',
    '2025-06-05 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 26 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 12 LIMIT 1),
    2,
    'Funciona bien pero tiene margen de mejora',
    '2025-10-23 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 23 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 14 LIMIT 1),
    5,
    'Fácil de configurar y usar',
    '2025-11-02 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 17 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    4,
    'Perfecto para mi setup',
    '2025-09-21 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 10 LIMIT 1),
    5,
    'Buen rendimiento en general',
    '2025-06-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    2,
    'Funciona bien pero tiene margen de mejora',
    '2025-06-04 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 10 LIMIT 1),
    5,
    'Perfecto para mi setup',
    '2025-06-07 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 12 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 15 LIMIT 1),
    2,
    'Tiene algunos detalles mejorables',
    '2025-07-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 18 LIMIT 1),
    1,
    'Tiene algunos detalles mejorables',
    '2025-11-07 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 3 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    3,
    'Por este precio no se puede pedir más',
    '2025-08-25 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 13 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 21 LIMIT 1),
    4,
    'Calidad premium, se nota la diferencia',
    '2025-06-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 21 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    3,
    'Gran compra, muy contento',
    '2025-09-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 12 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 18 LIMIT 1),
    5,
    'Funciona perfectamente, muy satisfecho',
    '2025-09-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 8 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 13 LIMIT 1),
    3,
    'Diseño elegante y funcional',
    '2025-06-07 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 28 LIMIT 1),
    3,
    'Muy buena construcción y materiales',
    '2025-07-23 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 25 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 23 LIMIT 1),
    4,
    'Cumple con lo prometido',
    '2025-09-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 13 LIMIT 1),
    4,
    'Buen rendimiento en general',
    '2025-07-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 26 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    3,
    'Funciona pero no es lo que esperaba',
    '2025-06-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 12 LIMIT 1),
    3,
    'Cumple con lo prometido',
    '2025-06-24 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    5,
    'Buen rendimiento en general',
    '2025-06-10 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 21 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 4 LIMIT 1),
    3,
    'Muy buena construcción y materiales',
    '2025-11-03 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    4,
    'Fácil de configurar y usar',
    '2025-06-21 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 7 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    4,
    'Por este precio no se puede pedir más',
    '2025-07-02 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    5,
    'Muy buena construcción y materiales',
    '2025-07-18 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 24 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 10 LIMIT 1),
    4,
    'Buena calidad precio, lo recomiendo',
    '2025-10-18 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 24 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 23 LIMIT 1),
    5,
    'Fácil de configurar y usar',
    '2025-07-05 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 5 LIMIT 1),
    1,
    'Precio elevado para lo que ofrece',
    '2025-08-13 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 7 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    2,
    'Tiene algunos detalles mejorables',
    '2025-07-04 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 21 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 19 LIMIT 1),
    3,
    '',
    '2025-06-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 8 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    4,
    'Calidad premium, se nota la diferencia',
    '2025-06-25 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 14 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 0 LIMIT 1),
    4,
    'Buena calidad precio, lo recomiendo',
    '2025-07-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 24 LIMIT 1),
    3,
    'Excelente producto, superó mis expectativas',
    '2025-06-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 26 LIMIT 1),
    3,
    'Diseño elegante y funcional',
    '2025-11-10 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 15 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    5,
    'Llegó rápido y bien empaquetado',
    '2025-06-20 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    5,
    'Por este precio no se puede pedir más',
    '2025-10-28 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 26 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 25 LIMIT 1),
    4,
    'Calidad premium, se nota la diferencia',
    '2025-10-29 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 28 LIMIT 1),
    3,
    'Funciona bien pero tiene margen de mejora',
    '2025-07-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 12 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    2,
    'Funciona bien pero tiene margen de mejora',
    '2025-06-28 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Rating" (user_id, product_id, score, comment, created_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 28 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    3,
    '',
    '2025-08-30 11:40:34'::TIMESTAMPTZ
);

-- ============================================
-- 4️⃣ Insertar Recomendaciones (50 registros)
-- ============================================
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 27 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    0.6194,
    'Descuento especial para ti',
    TRUE,
    TRUE,
    '2025-11-04 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    0.8537,
    'Nuevo lanzamiento que te puede interesar',
    FALSE,
    FALSE,
    '2025-11-02 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.7961,
    'Usuarios similares también compraron esto',
    TRUE,
    FALSE,
    '2025-10-25 11:40:34'::TIMESTAMPTZ,
    '2025-11-15 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 26 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    0.83,
    'Basado en tus compras anteriores',
    FALSE,
    FALSE,
    '2025-11-13 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 13 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    0.9391,
    'Complementa perfectamente tu último pedido',
    FALSE,
    FALSE,
    '2025-11-08 11:40:34'::TIMESTAMPTZ,
    '2025-11-24 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    0.7127,
    'Nuevo lanzamiento que te puede interesar',
    FALSE,
    FALSE,
    '2025-11-04 11:40:34'::TIMESTAMPTZ,
    '2025-11-14 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 22 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 17 LIMIT 1),
    0.603,
    'Recomendado por expertos',
    TRUE,
    FALSE,
    '2025-10-17 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 14 LIMIT 1),
    0.6165,
    'Complementa perfectamente tu último pedido',
    TRUE,
    TRUE,
    '2025-10-23 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 0 LIMIT 1),
    0.8513,
    'Tendencia en tu categoría favorita',
    FALSE,
    FALSE,
    '2025-11-01 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 14 LIMIT 1),
    0.9501,
    'Alta valoración en productos similares',
    TRUE,
    FALSE,
    '2025-10-27 11:40:34'::TIMESTAMPTZ,
    '2025-11-06 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 15 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    0.6993,
    'Basado en tus compras anteriores',
    FALSE,
    FALSE,
    '2025-10-26 11:40:34'::TIMESTAMPTZ,
    '2025-11-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 13 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 23 LIMIT 1),
    0.6098,
    'Tendencia en tu categoría favorita',
    TRUE,
    FALSE,
    '2025-11-06 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    0.7053,
    'Descuento especial para ti',
    FALSE,
    FALSE,
    '2025-10-17 11:40:34'::TIMESTAMPTZ,
    '2025-11-10 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    0.6031,
    'Usuarios similares también compraron esto',
    TRUE,
    FALSE,
    '2025-11-06 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 18 LIMIT 1),
    0.8028,
    'Complementa perfectamente tu último pedido',
    TRUE,
    FALSE,
    '2025-10-24 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 21 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 1 LIMIT 1),
    0.7372,
    'Alta valoración en productos similares',
    TRUE,
    FALSE,
    '2025-11-03 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 16 LIMIT 1),
    0.7787,
    'Alta valoración en productos similares',
    TRUE,
    FALSE,
    '2025-11-06 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 7 LIMIT 1),
    0.6264,
    'Descuento especial para ti',
    FALSE,
    FALSE,
    '2025-10-27 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 4 LIMIT 1),
    0.7515,
    'Descuento especial para ti',
    TRUE,
    FALSE,
    '2025-11-11 11:40:34'::TIMESTAMPTZ,
    '2025-12-01 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 16 LIMIT 1),
    0.9336,
    'Producto más vendido esta semana',
    FALSE,
    FALSE,
    '2025-10-28 11:40:34'::TIMESTAMPTZ,
    '2025-11-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 22 LIMIT 1),
    0.8757,
    'Compatible con tus dispositivos',
    FALSE,
    FALSE,
    '2025-11-11 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 28 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    0.9726,
    'Compatible con tus dispositivos',
    TRUE,
    FALSE,
    '2025-10-24 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 6 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.6034,
    'Basado en tus compras anteriores',
    TRUE,
    FALSE,
    '2025-10-23 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    0.657,
    'Alta valoración en productos similares',
    FALSE,
    FALSE,
    '2025-11-08 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 19 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.7888,
    'Nuevo lanzamiento que te puede interesar',
    FALSE,
    FALSE,
    '2025-11-08 11:40:34'::TIMESTAMPTZ,
    '2025-11-21 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 15 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 26 LIMIT 1),
    0.7925,
    'Tendencia en tu categoría favorita',
    FALSE,
    FALSE,
    '2025-11-08 11:40:34'::TIMESTAMPTZ,
    '2025-11-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 4 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 13 LIMIT 1),
    0.9429,
    'Compatible con tus dispositivos',
    FALSE,
    FALSE,
    '2025-10-31 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 11 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 18 LIMIT 1),
    0.7888,
    'Complementa perfectamente tu último pedido',
    TRUE,
    FALSE,
    '2025-11-05 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    0.8308,
    'Complementa perfectamente tu último pedido',
    FALSE,
    FALSE,
    '2025-10-29 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 21 LIMIT 1),
    0.8606,
    'Tendencia en tu categoría favorita',
    FALSE,
    FALSE,
    '2025-10-23 11:40:34'::TIMESTAMPTZ,
    '2025-11-11 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 22 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.6815,
    'Usuarios similares también compraron esto',
    FALSE,
    FALSE,
    '2025-10-28 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 16 LIMIT 1),
    0.7959,
    'Usuarios similares también compraron esto',
    FALSE,
    FALSE,
    '2025-10-17 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 20 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 29 LIMIT 1),
    0.8817,
    'Tendencia en tu categoría favorita',
    TRUE,
    FALSE,
    '2025-10-29 11:40:34'::TIMESTAMPTZ,
    '2025-11-16 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 28 LIMIT 1),
    0.9404,
    'Compatible con tus dispositivos',
    TRUE,
    FALSE,
    '2025-10-16 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 7 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    0.8083,
    'Basado en tus compras anteriores',
    TRUE,
    FALSE,
    '2025-10-27 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 8 LIMIT 1),
    0.9446,
    'Usuarios similares también compraron esto',
    TRUE,
    FALSE,
    '2025-11-09 11:40:34'::TIMESTAMPTZ,
    '2025-11-23 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 2 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 27 LIMIT 1),
    0.8746,
    'Recomendado por expertos',
    TRUE,
    FALSE,
    '2025-11-04 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 19 LIMIT 1),
    0.7225,
    'Recomendado por expertos',
    TRUE,
    TRUE,
    '2025-11-10 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 9 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 2 LIMIT 1),
    0.6899,
    'Complementa perfectamente tu último pedido',
    TRUE,
    FALSE,
    '2025-11-03 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 16 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.6578,
    'Producto más vendido esta semana',
    TRUE,
    TRUE,
    '2025-11-07 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 10 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 6 LIMIT 1),
    0.605,
    'Nuevo lanzamiento que te puede interesar',
    TRUE,
    TRUE,
    '2025-11-06 11:40:34'::TIMESTAMPTZ,
    '2025-11-17 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 22 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 16 LIMIT 1),
    0.6128,
    'Compatible con tus dispositivos',
    FALSE,
    FALSE,
    '2025-10-16 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 0 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 29 LIMIT 1),
    0.6825,
    'Alta valoración en productos similares',
    FALSE,
    FALSE,
    '2025-10-22 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 24 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 11 LIMIT 1),
    0.7725,
    'Tendencia en tu categoría favorita',
    FALSE,
    FALSE,
    '2025-10-19 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 15 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 14 LIMIT 1),
    0.6454,
    'Alta valoración en productos similares',
    FALSE,
    FALSE,
    '2025-11-08 11:40:34'::TIMESTAMPTZ,
    '2025-11-19 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 28 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 9 LIMIT 1),
    0.8473,
    'Usuarios similares también compraron esto',
    TRUE,
    FALSE,
    '2025-11-01 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 3 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 28 LIMIT 1),
    0.7734,
    'Complementa perfectamente tu último pedido',
    TRUE,
    FALSE,
    '2025-11-01 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 18 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 7 LIMIT 1),
    0.8147,
    'Compatible con tus dispositivos',
    FALSE,
    FALSE,
    '2025-11-07 11:40:34'::TIMESTAMPTZ,
    NULL
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 5 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 24 LIMIT 1),
    0.7458,
    'Alta valoración en productos similares',
    FALSE,
    FALSE,
    '2025-11-13 11:40:34'::TIMESTAMPTZ,
    '2025-11-22 11:40:34'::TIMESTAMPTZ
);
INSERT INTO "Recommendation" (user_id, product_id, score, reason, is_shown, is_clicked, created_at, expires_at)
VALUES (
    (SELECT id FROM temp_users OFFSET 1 LIMIT 1),
    (SELECT id FROM temp_products OFFSET 3 LIMIT 1),
    0.6934,
    'Complementa perfectamente tu último pedido',
    TRUE,
    FALSE,
    '2025-11-11 11:40:34'::TIMESTAMPTZ,
    NULL
);

-- Limpiar tablas temporales
DROP TABLE temp_users;
DROP TABLE temp_products;

COMMIT;

-- Rehabilitar triggers
SET session_replication_role = DEFAULT;

-- Actualizar estadísticas para el optimizador
ANALYZE "User";
ANALYZE "Product";
ANALYZE "Rating";
ANALYZE "Recommendation";

-- Refrescar vista materializada
REFRESH MATERIALIZED VIEW product_stats;

-- ============================================
-- 📊 RESUMEN DE DATOS INSERTADOS
-- ============================================
-- ✅ Usuarios: 30
-- ✅ Productos: 30
-- ✅ Ratings: 100
-- ✅ Recomendaciones: 50
-- ============================================

-- Consultas de verificación:
SELECT COUNT(*) as total_users FROM "User";
SELECT COUNT(*) as total_products FROM "Product";
SELECT COUNT(*) as total_ratings FROM "Rating";
SELECT COUNT(*) as total_recommendations FROM "Recommendation";
SELECT * FROM product_stats LIMIT 10;