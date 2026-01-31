/* ============================================================
   VISTAS PARA CONSUMO DE API – PROYECTO HERMANDAD
   ============================================================

   Este archivo contiene TODAS las vistas necesarias para:
   - Reducir joins en la API
   - Centralizar la lógica de lectura
   - Facilitar la creación de DTOs

   Cada vista indica:
   - Qué representa
   - Qué endpoints REST la utilizarán

   ============================================================ */


/* ============================================================
   VISTA BASE: vw_participaciones
   ------------------------------------------------------------
   Concepto:
   - Participación de un hermano en un tramo con un rol

   Endpoints:
   - GET /participaciones
   - GET /hermanos/{id}/participaciones
   - GET /pasos/{id}/participaciones
   - GET /tramos/{id}/participantes
   ============================================================ */

SET GLOBAL log_bin_trust_function_creators = 1;

CREATE OR REPLACE VIEW vw_participaciones AS
SELECT
  p.id_participacion,

  h.id_hermano,
  h.nombre           AS nombre_hermano,
  h.email            AS email_hermano,
  h.activo           AS hermano_activo,

  t.id_tramo,
  t.nombre           AS nombre_tramo,
  t.orden            AS orden_tramo,

  pa.id_paso,
  pa.nombre          AS nombre_paso,

  rf.codigo          AS rol_funcional
FROM participacion p
JOIN hermano h         ON h.id_hermano = p.id_hermano
JOIN tramo t           ON t.id_tramo = p.id_tramo
JOIN paso pa           ON pa.id_paso = t.id_paso
JOIN rol_funcional rf  ON rf.id_rol_funcional = p.id_rol_funcional;


/* ============================================================
   VISTA: vw_costaleros
   ------------------------------------------------------------
   Concepto:
   - Participaciones cuyo rol es COSTALERO

   Endpoints:
   - GET /pasos/{id}/costaleros
   - GET /tramos/{id}/costaleros
   ============================================================ */
CREATE OR REPLACE VIEW vw_costaleros AS
SELECT
  v.*,
  pc.funcion
FROM vw_participaciones v
JOIN participacion_costalero pc
  ON pc.id_participacion = v.id_participacion
WHERE v.rol_funcional = 'COSTALERO';


/* ============================================================
   VISTA: vw_musicos
   ------------------------------------------------------------
   Concepto:
   - Participaciones cuyo rol es MUSICO

   Endpoints:
   - GET /pasos/{id}/musicos
   - GET /tramos/{id}/musicos
   ============================================================ */
CREATE OR REPLACE VIEW vw_musicos AS
SELECT
  v.*,
  b.id_banda,
  b.nombre AS nombre_banda,
  b.tipo   AS tipo_banda,
  pm.instrumento
FROM vw_participaciones v
JOIN participacion_musico pm
  ON pm.id_participacion = v.id_participacion
JOIN banda b
  ON b.id_banda = pm.id_banda
WHERE v.rol_funcional = 'MUSICO';


/* ============================================================
   VISTA: vw_nazarenos
   ------------------------------------------------------------
   Concepto:
   - Participaciones cuyo rol es NAZARENO

   Endpoints:
   - GET /pasos/{id}/nazarenos
   - GET /tramos/{id}/nazarenos
   ============================================================ */
CREATE OR REPLACE VIEW vw_nazarenos AS
SELECT
  v.*,
  pn.numero
FROM vw_participaciones v
JOIN participacion_nazareno pn
  ON pn.id_participacion = v.id_participacion
WHERE v.rol_funcional = 'NAZARENO';


/* ============================================================
   VISTA: vw_tramos
   ------------------------------------------------------------
   Concepto:
   - Tramos con información del paso

   Endpoints:
   - GET /tramos
   - GET /pasos/{id}/tramos
   ============================================================ */
CREATE OR REPLACE VIEW vw_tramos AS
SELECT
  t.id_tramo,
  t.nombre        AS nombre_tramo,
  t.orden,
  p.id_paso,
  p.nombre        AS nombre_paso
FROM tramo t
JOIN paso p ON p.id_paso = t.id_paso;


/* ============================================================
   VISTA: vw_pasos_resumen
   ------------------------------------------------------------
   Concepto:
   - Resumen de participantes por paso

   Endpoints:
   - GET /pasos
   - GET /dashboard/pasos
   ============================================================ */
CREATE OR REPLACE VIEW vw_pasos_resumen AS
SELECT
  pa.id_paso,
  pa.nombre AS nombre_paso,

  COUNT(p.id_participacion) AS total_participantes,
  SUM(rf.codigo = 'COSTALERO') AS total_costaleros,
  SUM(rf.codigo = 'MUSICO')    AS total_musicos,
  SUM(rf.codigo = 'NAZARENO')  AS total_nazarenos
FROM paso pa
LEFT JOIN tramo t          ON t.id_paso = pa.id_paso
LEFT JOIN participacion p  ON p.id_tramo = t.id_tramo
LEFT JOIN rol_funcional rf ON rf.id_rol_funcional = p.id_rol_funcional
GROUP BY pa.id_paso, pa.nombre;


/* ============================================================
   VISTA: vw_hermanos_roles
   ------------------------------------------------------------
   Concepto:
   - Hermanos con sus roles de seguridad

   Endpoints:
   - GET /hermanos
   - GET /admin/hermanos
   ============================================================ */
CREATE OR REPLACE VIEW vw_hermanos_roles AS
SELECT
  h.id_hermano,
  h.nombre,
  h.email,
  h.activo,
  GROUP_CONCAT(r.nombre_rol ORDER BY r.nombre_rol SEPARATOR ', ') AS roles
FROM hermano h
LEFT JOIN hermano_rol hr ON hr.id_hermano = h.id_hermano
LEFT JOIN rol r         ON r.id_rol = hr.id_rol
GROUP BY h.id_hermano;


/* ============================================================
   VISTA: vw_hermano_permisos
   ------------------------------------------------------------
   Concepto:
   - Permisos efectivos por hermano

   Endpoints:
   - GET /hermanos/{id}/permisos
   - GET /auth/permisos
   ============================================================ */
CREATE OR REPLACE VIEW vw_hermano_permisos AS
SELECT DISTINCT
  h.id_hermano,
  p.nombre_permiso
FROM hermano h
JOIN hermano_rol hr ON hr.id_hermano = h.id_hermano
JOIN rol r          ON r.id_rol = hr.id_rol
JOIN rol_permiso rp ON rp.id_rol = r.id_rol
JOIN permiso p      ON p.id_permiso = rp.id_permiso;