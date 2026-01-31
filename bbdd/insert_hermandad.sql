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