-- Crear base de datos si no existe y usarla
CREATE DATABASE IF NOT EXISTS gardenia;
USE gardenia;

-- Eliminar las tablas si existen (en orden por dependencias)
DROP TABLE IF EXISTS detalles;
DROP TABLE IF EXISTS ordenes;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS usuarios;

-- Crear tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    tipo VARCHAR(20),
    password VARCHAR(255)
);

-- Crear tabla productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    imagen VARCHAR(255),
    precio DOUBLE,
    cantidad INT,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Crear tabla ordenes
CREATE TABLE IF NOT EXISTS ordenes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(100),
    fecha_creacion DATETIME,
    fecha_recibida DATETIME,
    total DOUBLE,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Crear tabla detalles (DetalleOrden)
CREATE TABLE IF NOT EXISTS detalles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    cantidad DOUBLE,
    precio DOUBLE,
    total DOUBLE,
    orden_id INT,
    producto_id INT,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Insertar usuario administrador
INSERT INTO usuarios (
    nombre, apellido, email, direccion, telefono, tipo, password
) VALUES (
    'Administrador', 'General', 'admin@gardenia.com', 'Av. Siempre Viva 123', '999999999', 'ADMIN',
    -- Contraseña hasheada (ejemplo con BCrypt para "Admin123")
    '$2a$10$xLFtBIXGtYvAbRqM95JhYeuNd/h6q5r6mhknU9t.ChkmY8b0F.Q0K'
);

-- Insertar productos de ejemplo
INSERT INTO productos (
    nombre, descripcion, imagen, precio, cantidad, usuario_id
) VALUES
('Laptop Lenovo', 'Laptop de alto rendimiento', 'laptop.jpg', 3500.00, 10, 1),
('Camiseta Negra', 'Camiseta de algodón 100%', 'camiseta.jpg', 45.00, 50, 1);
