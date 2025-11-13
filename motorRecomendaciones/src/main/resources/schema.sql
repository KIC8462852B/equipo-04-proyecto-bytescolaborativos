-- ============================================
-- Script SQL Optimizado - PostgreSQL 13+
-- Mejoras: UUIDv7, índices estratégicos, constraints mejoradas
-- Sistema de Recomendaciones con Ratings
-- ============================================

-- 0️⃣ Limpiar tablas existentes (si es necesario)
DROP TABLE IF EXISTS "Recommendation" CASCADE;
DROP TABLE IF EXISTS "Rating" CASCADE;
DROP TABLE IF EXISTS "Product" CASCADE;
DROP TABLE IF EXISTS "User" CASCADE;

-- 1️⃣ Extensiones necesarias
CREATE EXTENSION IF NOT EXISTS pgcrypto;  -- Para gen_random_uuid()

-- ============================================
-- 2️⃣ Tabla User - Optimizada
-- ============================================
CREATE TABLE "User" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- TIMESTAMPTZ incluye zona horaria
    updated_at TIMESTAMPTZ,

    -- Constraints con nombres explícitos para debugging
    CONSTRAINT user_username_unique UNIQUE (username),
    CONSTRAINT user_email_unique UNIQUE (email),
    CONSTRAINT user_email_format CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

-- Índices para búsquedas comunes por email (login)
CREATE INDEX idx_user_email_lower ON "User" (LOWER(email));  -- Para búsquedas case-insensitive

-- ============================================
-- 3️⃣ Tabla Product - Optimizada
-- ============================================
CREATE TABLE "Product" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL CHECK (price >= 0),  -- Precio no puede ser negativo
    is_active BOOLEAN DEFAULT true,  -- Soft delete
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

-- Índice para búsquedas de productos activos (90% de las consultas)
CREATE INDEX idx_product_active ON "Product" (is_active) WHERE is_active = true;  -- Índice parcial

-- Índice para búsquedas de texto en nombre (requiere extensión pg_trgm)
-- CREATE EXTENSION IF NOT EXISTS pg_trgm;  -- Descomentar si necesitas búsqueda fuzzy
-- CREATE INDEX idx_product_name_trgm ON "Product" USING gin (name gin_trgm_ops);

-- ============================================
-- 4️⃣ Tabla Rating - Optimizada
-- ============================================
CREATE TABLE "Rating" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    product_id UUID NOT NULL,
    score SMALLINT NOT NULL CHECK (score BETWEEN 1 AND 5),  -- SMALLINT usa menos espacio
    comment TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,

    -- Foreign keys con índices automáticos
    CONSTRAINT fk_rating_user FOREIGN KEY (user_id)
        REFERENCES "User"(id) ON DELETE CASCADE,
    CONSTRAINT fk_rating_product FOREIGN KEY (product_id)
        REFERENCES "Product"(id) ON DELETE CASCADE,

    -- Un usuario solo puede valorar un producto una vez
    CONSTRAINT rating_user_product_unique UNIQUE (user_id, product_id)
);

-- Índices compuestos estratégicos (orden importa)
CREATE INDEX idx_rating_product_score ON "Rating" (product_id, score);  -- Para cálculo de ratings promedio
CREATE INDEX idx_rating_user_created ON "Rating" (user_id, created_at DESC);  -- Para historial del usuario
CREATE INDEX idx_rating_created ON "Rating" (created_at DESC) WHERE score <= 2;  -- Para identificar productos mal valorados

-- ============================================
-- 5️⃣ Tabla Recommendation - Optimizada
-- ============================================
CREATE TABLE "Recommendation" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    product_id UUID NOT NULL,
    score NUMERIC(5,4),  -- Puntuación del algoritmo (0.0000-1.0000)
    reason TEXT,
    is_shown BOOLEAN DEFAULT false,  -- Para tracking de impresiones
    is_clicked BOOLEAN DEFAULT false,  -- Para CTR
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMPTZ,  -- Recomendaciones temporales

    CONSTRAINT fk_recommendation_user FOREIGN KEY (user_id)
        REFERENCES "User"(id) ON DELETE CASCADE,
    CONSTRAINT fk_recommendation_product FOREIGN KEY (product_id)
        REFERENCES "Product"(id) ON DELETE CASCADE,
    CONSTRAINT recommendation_user_product_unique UNIQUE (user_id, product_id),
    CONSTRAINT recommendation_score_valid CHECK (score IS NULL OR (score >= 0 AND score <= 1))
);

-- Índices optimizados para queries típicas
CREATE INDEX idx_recommendation_user_score ON "Recommendation" (user_id, score DESC NULLS LAST, created_at DESC);  -- Para obtener top recomendaciones
CREATE INDEX idx_recommendation_active ON "Recommendation" (user_id, created_at DESC)
    WHERE expires_at IS NULL OR expires_at > CURRENT_TIMESTAMP;  -- Solo recomendaciones válidas

-- ============================================
-- 6️⃣ Vistas materializadas para analytics
-- ============================================
CREATE MATERIALIZED VIEW product_stats AS
SELECT
    p.id,
    p.name,
    COUNT(r.id) as total_ratings,
    AVG(r.score) as avg_score,
    COUNT(rec.id) as times_recommended
FROM "Product" p
LEFT JOIN "Rating" r ON p.id = r.product_id
LEFT JOIN "Recommendation" rec ON p.id = rec.product_id
GROUP BY p.id, p.name;

CREATE UNIQUE INDEX idx_product_stats_id ON product_stats (id);

-- ============================================
-- 7️⃣ Función para actualizar updated_at automáticamente
-- ============================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Aplicar trigger a todas las tablas
CREATE TRIGGER update_user_updated_at BEFORE UPDATE ON "User"
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_product_updated_at BEFORE UPDATE ON "Product"
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_rating_updated_at BEFORE UPDATE ON "Rating"
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ============================================
-- 8️⃣ Configuraciones recomendadas (ejecutar como superuser)
-- ============================================
-- Descomentar y ajustar según tu hardware:
/*
ALTER SYSTEM SET shared_buffers = '2GB';          -- 25% de RAM disponible
ALTER SYSTEM SET effective_cache_size = '6GB';   -- 75% de RAM disponible
ALTER SYSTEM SET work_mem = '64MB';               -- Para sorts y joins
ALTER SYSTEM SET maintenance_work_mem = '512MB';  -- Para VACUUM, CREATE INDEX
ALTER SYSTEM SET random_page_cost = 1.1;          -- Para SSD (por defecto es 4)
SELECT pg_reload_conf();
*/

-- ============================================
-- 9️⃣ Comandos de mantenimiento
-- ============================================
-- Ejecutar periódicamente:
-- VACUUM ANALYZE "Rating";
-- VACUUM ANALYZE "Recommendation";
-- REFRESH MATERIALIZED VIEW CONCURRENTLY product_stats;
