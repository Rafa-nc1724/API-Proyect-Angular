/*
 Listado de usuarios por tramo y grupo
Objetivo: saber quién va en cada tramo y en qué grupo.
*/

CREATE OR REPLACE VIEW vw_usuarios_por_tramo_grupo AS
SELECT 
    t.id              AS tramo_id,
    g.id              AS grupo_id,
    g.nombre          AS grupo,
    u.id              AS usuario_id,
    u.nombre          AS usuario,
    u.rol             AS rol_usuario,
    tgu.funcion       AS funcion
FROM tramo_grupo_usuario tgu
JOIN usuario u ON u.id = tgu.id_usuario
JOIN grupo g   ON g.id = tgu.id_grupo
JOIN tramo t   ON t.id = tgu.id_tramo
ORDER BY t.id, g.id;

/*
Vista de perfil de usuario
(grupos, tramos y función)
*/

CREATE OR REPLACE VIEW vw_perfil_usuario AS
SELECT
    u.id              AS usuario_id,
    u.nombre,
    u.email,
    u.rol,
    g.nombre          AS grupo,
    t.id              AS tramo_id,
    FROM_UNIXTIME(t.salida)  AS salida,
    FROM_UNIXTIME(t.entrada) AS entrada,
    tgu.funcion
FROM usuario u
LEFT JOIN tramo_grupo_usuario tgu ON u.id = tgu.id_usuario
LEFT JOIN grupo g ON g.id = tgu.id_grupo
LEFT JOIN tramo t ON t.id = tgu.id_tramo
ORDER BY u.id;

/*
Vista de tramo (salida / entrada formateadas)
*/

CREATE OR REPLACE VIEW vw_tramos_formateados AS
SELECT
    id AS tramo_id,
    salida,
    entrada,
    FROM_UNIXTIME(salida, '%d/%m/%Y %H:%i')  AS salida_formateada,
    FROM_UNIXTIME(entrada, '%d/%m/%Y %H:%i') AS entrada_formateada
FROM tramo;

/*
Organización completa del cortejo por orden
El orden se basa en el id del grupo (tal y como los has definido).
*/

CREATE OR REPLACE VIEW vw_cortejo_completo AS
SELECT
    g.id        AS orden,
    g.nombre    AS grupo,
    u.nombre    AS usuario,
    tgu.funcion
FROM grupo g
LEFT JOIN tramo_grupo_usuario tgu ON g.id = tgu.id_grupo
LEFT JOIN usuario u ON u.id = tgu.id_usuario
ORDER BY g.id;

/*
Vistas específicas por función
(músicos, costaleros, nazarenos, administrativa)
*/

CREATE OR REPLACE VIEW vw_usuarios_por_funcion AS
SELECT
    tgu.funcion,
    u.nombre AS usuario,
    g.nombre AS grupo
FROM tramo_grupo_usuario tgu
JOIN usuario u ON u.id = tgu.id_usuario
JOIN grupo g ON g.id = tgu.id_grupo
ORDER BY tgu.funcion, g.id;

/*
ejemplo de uso:
SELECT * FROM vw_usuarios_por_funcion WHERE funcion = 'musico';
*/

/*
Calendario de ensayos por grupo
*/

CREATE OR REPLACE VIEW vw_calendario_ensayos AS
SELECT
    e.id,
    e.fecha,
    e.descripcion,
    g.nombre AS grupo
FROM ensayo e
JOIN grupo g ON g.id = e.id_grupo
ORDER BY e.fecha;

/*
Vistas diferenciadas por rol
(admin / junta / capataz)
*/

CREATE OR REPLACE VIEW vw_usuarios_por_rol AS
SELECT
    id,
    nombre,
    email,
    rol,
    activo
FROM usuario
ORDER BY rol, nombre;

/*
ejemplo de uso:
SELECT * FROM vw_usuarios_por_rol WHERE rol = 'capataz';
*/

