-- ---------------------------------------------------------
-- RECREAR BASE DE DATOS hermandad
-- ---------------------------------------------------------

DROP DATABASE IF EXISTS hermandad;
CREATE DATABASE hermandad
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE hermandad;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

SET NAMES utf8mb4;

-- ---------------------------------------------------------
-- TABLA: hermano
-- ---------------------------------------------------------
CREATE TABLE hermano (
  id_hermano INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  dni VARCHAR(15) NOT NULL UNIQUE,
  direccion VARCHAR(200),
  telefono VARCHAR(20),
  email VARCHAR(100),
  activo TINYINT(1) NOT NULL DEFAULT 1,
  password VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: rol (seguridad / permisos)
-- ---------------------------------------------------------
CREATE TABLE rol (
  id_rol INT AUTO_INCREMENT PRIMARY KEY,
  nombre_rol VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: permiso
-- ---------------------------------------------------------
CREATE TABLE permiso (
  id_permiso INT AUTO_INCREMENT PRIMARY KEY,
  nombre_permiso VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: rol_permiso
-- ---------------------------------------------------------
CREATE TABLE rol_permiso (
  id_rol INT NOT NULL,
  id_permiso INT NOT NULL,
  PRIMARY KEY (id_rol, id_permiso),
  FOREIGN KEY (id_rol) REFERENCES rol(id_rol) ON DELETE CASCADE,
  FOREIGN KEY (id_permiso) REFERENCES permiso(id_permiso) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: hermano_rol
-- ---------------------------------------------------------
CREATE TABLE hermano_rol (
  id_hermano INT NOT NULL,
  id_rol INT NOT NULL,
  PRIMARY KEY (id_hermano, id_rol),
  FOREIGN KEY (id_hermano) REFERENCES hermano(id_hermano) ON DELETE CASCADE,
  FOREIGN KEY (id_rol) REFERENCES rol(id_rol) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: paso
-- ---------------------------------------------------------
CREATE TABLE paso (
  id_paso INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: banda
-- ---------------------------------------------------------
CREATE TABLE banda (
  id_banda INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  tipo VARCHAR(50)
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: tramo
-- ---------------------------------------------------------
CREATE TABLE tramo (
  id_tramo INT AUTO_INCREMENT PRIMARY KEY,
  id_paso INT NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  orden INT NOT NULL,
  FOREIGN KEY (id_paso) REFERENCES paso(id_paso) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: rol_funcional (rol en un tramo)
-- ---------------------------------------------------------
CREATE TABLE rol_funcional (
  id_rol_funcional INT AUTO_INCREMENT PRIMARY KEY,
  codigo ENUM('COSTALERO','MUSICO','NAZARENO') NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: participacion
-- Un hermano solo puede tener UN rol por tramo
-- ---------------------------------------------------------
CREATE TABLE participacion (
  id_participacion INT AUTO_INCREMENT PRIMARY KEY,
  id_hermano INT NOT NULL,
  id_tramo INT NOT NULL,
  id_rol_funcional INT NOT NULL,

  UNIQUE (id_hermano, id_tramo),

  FOREIGN KEY (id_hermano) REFERENCES hermano(id_hermano) ON DELETE CASCADE,
  FOREIGN KEY (id_tramo) REFERENCES tramo(id_tramo) ON DELETE CASCADE,
  FOREIGN KEY (id_rol_funcional) REFERENCES rol_funcional(id_rol_funcional)
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: participacion_costalero
-- ---------------------------------------------------------
CREATE TABLE participacion_costalero (
  id_participacion INT PRIMARY KEY,
  funcion ENUM(
    'Corriente Izquierdo',
    'Corriente Derecho',
    'Patero Frente Derecho',
    'Patero Frente Izquierdo',
    'Patero Trasero Izquierdo',
    'Patero Trasero Derecho',
    'Costero Izquierdo',
    'Costero Derecho'
  ) NOT NULL,
  FOREIGN KEY (id_participacion)
    REFERENCES participacion(id_participacion)
    ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: participacion_musico
-- ---------------------------------------------------------
CREATE TABLE participacion_musico (
  id_participacion INT PRIMARY KEY,
  id_banda INT NOT NULL,
  instrumento VARCHAR(50),
  FOREIGN KEY (id_participacion)
    REFERENCES participacion(id_participacion)
    ON DELETE CASCADE,
  FOREIGN KEY (id_banda)
    REFERENCES banda(id_banda)
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: participacion_nazareno
-- ---------------------------------------------------------
CREATE TABLE participacion_nazareno (
  id_participacion INT PRIMARY KEY,
  numero INT,
  FOREIGN KEY (id_participacion)
    REFERENCES participacion(id_participacion)
    ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: ensayo
-- ---------------------------------------------------------
CREATE TABLE ensayo (
  id_ensayo INT AUTO_INCREMENT PRIMARY KEY,
  id_paso INT NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  lugar VARCHAR(100),
  FOREIGN KEY (id_paso) REFERENCES paso(id_paso) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------
-- TABLA: paso_capataz
-- ---------------------------------------------------------
CREATE TABLE paso_capataz (
  id_paso INT NOT NULL,
  id_hermano INT NOT NULL,
  PRIMARY KEY (id_paso, id_hermano),
  FOREIGN KEY (id_paso) REFERENCES paso(id_paso) ON DELETE CASCADE,
  FOREIGN KEY (id_hermano) REFERENCES hermano(id_hermano) ON DELETE CASCADE
) ENGINE=InnoDB;

COMMIT;

SET GLOBAL log_bin_trust_function_creators = 1;

INSERT INTO rol (nombre_rol) VALUES
('ADMINISTRADOR'),
('HERMANO_MAYOR'),
('HERMANO_JUNTA'),
('CAPATAZ'),
('HERMANO_BASE');

INSERT INTO permiso (nombre_permiso) VALUES
('VER_PERFIL'),
('EDITAR_PERFIL'),
('GESTIONAR_USUARIOS'),
('CREAR_NOTICIAS'),
('GESTIONAR_ENSAYOS'),
('ADMIN_TOTAL');

INSERT INTO rol_funcional (codigo) VALUES
('COSTALERO'),
('MUSICO'),
('NAZARENO');

-- ADMINISTRADOR → todo
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r, permiso p
WHERE r.nombre_rol = 'ADMINISTRADOR';

-- HERMANO_MAYOR → todo
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r, permiso p
WHERE r.nombre_rol = 'HERMANO_MAYOR';

-- HERMANO_JUNTA
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r
JOIN permiso p ON p.nombre_permiso IN (
  'VER_PERFIL',
  'EDITAR_PERFIL',
  'GESTIONAR_USUARIOS',
  'CREAR_NOTICIAS'
)
WHERE r.nombre_rol = 'HERMANO_JUNTA';

-- CAPATAZ
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r
JOIN permiso p ON p.nombre_permiso IN (
  'VER_PERFIL',
  'EDITAR_PERFIL',
  'GESTIONAR_USUARIOS',
  'GESTIONAR_ENSAYOS'
)
WHERE r.nombre_rol = 'CAPATAZ';

-- HERMANO_BASE
INSERT INTO rol_permiso (id_rol, id_permiso)
SELECT r.id_rol, p.id_permiso
FROM rol r
JOIN permiso p ON p.nombre_permiso IN (
  'VER_PERFIL',
  'EDITAR_PERFIL'
)
WHERE r.nombre_rol = 'HERMANO_BASE';
