--   1. TABLA CATEGORIA
CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL
);

--   2. TABLA PRODUCTO
CREATE TABLE producto (
    id_producto  SERIAL PRIMARY KEY,
    nombre       VARCHAR(100)   NOT NULL,
    precio       NUMERIC(10, 2) NOT NULL,
    existencias  INT            NOT NULL,
    id_categoria INT,
    CONSTRAINT fk_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categoria(id_categoria)
);

--   3. TABLA PROVEEDOR
CREATE TABLE proveedor (
    id_proveedor       SERIAL PRIMARY KEY,
    nombre             VARCHAR(100) NOT NULL,
    telefono           VARCHAR(20),
    correo_electronico VARCHAR(100)
);

--   4. TABLA INTERMEDIA PROVEEDOR_PRODUCTO (N:M)
CREATE TABLE proveedor_producto (
    id_proveedor_producto SERIAL PRIMARY KEY,
    id_proveedor          INT,
    id_producto           INT,
    CONSTRAINT fk_proveedor
        FOREIGN KEY (id_proveedor)
        REFERENCES proveedor(id_proveedor),
    CONSTRAINT fk_producto
        FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
);

--   5. TABLA CLIENTE
CREATE TABLE cliente (
    id_cliente         SERIAL PRIMARY KEY,
    nombre             VARCHAR(100) NOT NULL,
    telefono           VARCHAR(20),
    correo_electronico VARCHAR(100)
);

--   6. TABLA EMPLEADO
--      Incluye campos de autenticación (JWT)
CREATE TABLE empleado (
    id_empleado SERIAL PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    posicion    VARCHAR(100),
    username    VARCHAR(50)  UNIQUE,
    password    VARCHAR(255),
    rol         VARCHAR(20)  DEFAULT 'EMPLEADO'
);

--   7. TABLA VENTA
CREATE TABLE venta (
    id_venta    SERIAL PRIMARY KEY,
    fecha       DATE NOT NULL,
    id_cliente  INT,
    id_empleado INT,
    CONSTRAINT fk_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente),
    CONSTRAINT fk_empleado
        FOREIGN KEY (id_empleado)
        REFERENCES empleado(id_empleado)
);

--   8. TABLA DETALLE_VENTA
CREATE TABLE detalle_venta (
    id_detalle  SERIAL PRIMARY KEY,
    cantidad    INT            NOT NULL,
    precio      NUMERIC(10, 2) NOT NULL,
    id_venta    INT,
    id_producto INT,
    CONSTRAINT fk_venta
        FOREIGN KEY (id_venta)
        REFERENCES venta(id_venta),
    CONSTRAINT fk_producto_detalle
        FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
);

--   DATOS DE PRUEBA
-- Categorías
INSERT INTO categoria (nombre) VALUES
    ('Medicamentos'),
    ('Alimentos'),
    ('Accesorios'),
    ('Vacunas');

-- Productos
INSERT INTO producto (nombre, precio, existencias, id_categoria) VALUES
    ('Vacuna Antirrábica',     35000.00, 50, 4),
    ('Desparasitante Oral',    15000.00, 80, 1),
    ('Concentrado Premium',    45000.00, 60, 2),
    ('Collar Antipulgas',      22000.00, 40, 3),
    ('Antibiótico Amoxicilina',28000.00, 35, 1),
    ('Arena para Gatos',       18000.00, 90, 3);

-- Proveedores
INSERT INTO proveedor (nombre, telefono, correo_electronico) VALUES
    ('Laboratorios VetFarma',  '3001234567', 'ventas@vetfarma.com'),
    ('Distribuidora PetLife',  '3109876543', 'info@petlife.co'),
    ('Nutrición Animal S.A.',  '3205551234', 'pedidos@nutrianimal.com');

-- Relación proveedor-producto
INSERT INTO proveedor_producto (id_proveedor, id_producto) VALUES
    (1, 1), (1, 2), (1, 5),
    (2, 3), (2, 4), (2, 6),
    (3, 3), (3, 6);

-- Clientes
INSERT INTO cliente (nombre, telefono, correo_electronico) VALUES
    ('Juan Pérez',     '3145678901', 'juan@gmail.com'),
    ('María López',    '3208765432', 'maria@outlook.com'),
    ('Carlos Gómez',   '3112345678', 'carlos@gmail.com'),
    ('Ana Rodríguez',  '3189876543', 'ana@hotmail.com');

-- Empleados (sin credenciales — se registran desde /api/auth/registrar)
INSERT INTO empleado (nombre, posicion) VALUES
    ('Dra. Laura Gómez',   'Veterinaria'),
    ('Pedro Martínez',     'Auxiliar'),
    ('Sandra Torres',      'Recepcionista');

-- Ventas
INSERT INTO venta (fecha, id_cliente, id_empleado) VALUES
    ('2026-03-10', 1, 1),
    ('2026-03-11', 2, 1),
    ('2026-03-12', 3, 2),
    ('2026-03-13', 4, 3),
    ('2026-03-14', 1, 1);

-- Detalles de venta
INSERT INTO detalle_venta (cantidad, precio, id_venta, id_producto) VALUES
    -- Venta 1
    (2, 35000.00, 1, 1),
    (1, 15000.00, 1, 2),
    -- Venta 2
    (1, 45000.00, 2, 3),
    (2, 22000.00, 2, 4),
    -- Venta 3
    (3, 15000.00, 3, 2),
    (1, 28000.00, 3, 5),
    -- Venta 4
    (2, 18000.00, 4, 6),
    -- Venta 5
    (1, 35000.00, 5, 1),
    (2, 45000.00, 5, 3);

