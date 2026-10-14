-- ============================================================
-- Script de creación de la base de datos: sistema_pedidos
-- Motor: MySQL (XAMPP)
-- ============================================================

-- Crear la base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS sistema_pedidos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE sistema_pedidos;

-- ------------------------------------------------------------
-- Tabla: usuarios
-- Almacena la información de los usuarios del sistema.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
                                        id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nombre          VARCHAR(100) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    fecha_registro  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- ------------------------------------------------------------
-- Tabla: pedidos
-- Almacena los pedidos realizados por los usuarios.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pedidos (
                                       id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       usuario_id     BIGINT NOT NULL,
                                       producto       VARCHAR(150) NOT NULL,
    cantidad       INT NOT NULL,
    estado         VARCHAR(50) DEFAULT 'pendiente',
    fecha_pedido   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pedido_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
    );

-- ------------------------------------------------------------
-- Datos de prueba
-- ------------------------------------------------------------
INSERT INTO usuarios (nombre, email) VALUES
                                         ('Ana Torres', 'ana.torres@example.com'),
                                         ('Luis Gomez', 'luis.gomez@example.com');

INSERT INTO pedidos (usuario_id, producto, cantidad, estado) VALUES
                                                                 (1, 'Laptop HP', 1, 'pendiente'),
                                                                 (2, 'Mouse Inalámbrico', 2, 'enviado');