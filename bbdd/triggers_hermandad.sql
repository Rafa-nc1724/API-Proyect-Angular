/* ============================================================
   TRIGGERS DE INTEGRIDAD – PROYECTO HERMANDAD
   ============================================================

   Este archivo contiene todos los triggers necesarios para:
   - Garantizar coherencia del dominio
   - Evitar datos corruptos
   - Reducir lógica defensiva en la API

   IMPORTANTE:
   - Los triggers NO contienen lógica de negocio
   - La API controla los flujos
   ============================================================ */


-- ============================================================
-- TRIGGER: Asignar rol base al crear un hermano
-- ------------------------------------------------------------
-- Evento: AFTER INSERT ON hermano
-- Objetivo:
--   - Garantizar que todo hermano tenga el rol HERMANO_BASE
--   - Evitar que la API tenga que asignarlo manualmente
-- Endpoints relacionados:
--   - POST /hermanos
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_hermano_insert_rol_base
AFTER INSERT ON hermano
FOR EACH ROW
BEGIN
  DECLARE v_id_rol_base INT;

  SELECT id_rol
  INTO v_id_rol_base
  FROM rol
  WHERE nombre_rol = 'HERMANO_BASE'
  LIMIT 1;

  IF v_id_rol_base IS NULL THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: no existe el rol HERMANO_BASE';
  END IF;

  INSERT INTO hermano_rol (id_hermano, id_rol)
  VALUES (NEW.id_hermano, v_id_rol_base);
END$$

DELIMITER ;


-- ============================================================
-- TRIGGERS DE VALIDACIÓN: PARTICIPACIÓN ↔ ROL FUNCIONAL
-- ============================================================


-- ============================================================
-- TRIGGER: Validar COSTALERO (INSERT)
-- ------------------------------------------------------------
-- Evento: BEFORE INSERT ON participacion_costalero
-- Objetivo:
--   - Evitar asociar datos de costalero a una participación
--     cuyo rol no sea COSTALERO
-- Endpoints relacionados:
--   - POST /participaciones/costaleros
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_costalero_validar_insert
BEFORE INSERT ON participacion_costalero
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol IS NULL THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: la participación no existe';
  END IF;

  IF v_rol <> 'COSTALERO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: rol funcional distinto de COSTALERO';
  END IF;
END$$

DELIMITER ;


-- ============================================================
-- TRIGGER: Validar COSTALERO (UPDATE)
-- ------------------------------------------------------------
-- Evento: BEFORE UPDATE ON participacion_costalero
-- Objetivo:
--   - Evitar corrupción de datos por actualizaciones inválidas
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_costalero_validar_update
BEFORE UPDATE ON participacion_costalero
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol <> 'COSTALERO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Actualización inválida: la participación no es COSTALERO';
  END IF;
END$$

DELIMITER ;


-- ============================================================
-- TRIGGER: Validar MUSICO (INSERT)
-- ------------------------------------------------------------
-- Evento: BEFORE INSERT ON participacion_musico
-- Objetivo:
--   - Evitar asociar datos de músico a un rol incorrecto
-- Endpoints relacionados:
--   - POST /participaciones/musicos
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_musico_validar_insert
BEFORE INSERT ON participacion_musico
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol IS NULL THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: la participación no existe';
  END IF;

  IF v_rol <> 'MUSICO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: rol funcional distinto de MUSICO';
  END IF;
END$$

DELIMITER ;


-- ============================================================
-- TRIGGER: Validar MUSICO (UPDATE)
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_musico_validar_update
BEFORE UPDATE ON participacion_musico
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol <> 'MUSICO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Actualización inválida: la participación no es MUSICO';
  END IF;
END$$

DELIMITER ;


-- ============================================================
-- TRIGGER: Validar NAZARENO (INSERT)
-- ------------------------------------------------------------
-- Evento: BEFORE INSERT ON participacion_nazareno
-- Objetivo:
--   - Evitar asociar datos de nazareno a un rol incorrecto
-- Endpoints relacionados:
--   - POST /participaciones/nazarenos
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_nazareno_validar_insert
BEFORE INSERT ON participacion_nazareno
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol IS NULL THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: la participación no existe';
  END IF;

  IF v_rol <> 'NAZARENO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Error de integridad: rol funcional distinto de NAZARENO';
  END IF;
END$$

DELIMITER ;


-- ============================================================
-- TRIGGER: Validar NAZARENO (UPDATE)
-- ============================================================

DELIMITER $$

CREATE TRIGGER trg_nazareno_validar_update
BEFORE UPDATE ON participacion_nazareno
FOR EACH ROW
BEGIN
  DECLARE v_rol VARCHAR(20);

  SELECT rf.codigo
  INTO v_rol
  FROM participacion p
  JOIN rol_funcional rf
    ON p.id_rol_funcional = rf.id_rol_funcional
  WHERE p.id_participacion = NEW.id_participacion;

  IF v_rol <> 'NAZARENO' THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Actualización inválida: la participación no es NAZARENO';
  END IF;
END$$

DELIMITER ;