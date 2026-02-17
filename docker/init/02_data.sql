USE hermandad;

-- =========================
-- USUARIO
-- =========================
INSERT INTO usuario (
    id, nombre, dni, direccion, telefono, email, pass, activo, rol, fecha_ingreso
) VALUES
      (2,'admin','23456789A','calle falsa 12','123123123','admin@email.com',
       '$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'admin','2026-02-01'),

      (3,'junta','23456789B','calle falsa 12','123123124','junta@email.com',
       '$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'junta','2026-02-01'),

      (4,'capataz','23456789C','calle falsa 12','123123125','capataz@email.com',
       '$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'capataz','2026-02-01'),

      (17,'usuario','23456789V','Calle Falsa','123123123','ususario@email.com',
       '$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'usuario','2026-02-01'),

      (20,'Rafael Nieto Campos','20621350X','Guadix','649233798','rafanietocampos@gmail.com',
       '$2a$10$BAYsgYP8uUCcwNTjeARjK.jB4lEm22hj0/hts67oF7AndWTZwXRV.',1,'admin','2026-02-13');


-- =========================
-- TRAMO
-- =========================
INSERT INTO tramo (
    id, go_out, enter, description
) VALUES
      (2,'2026-02-10','2026-02-10','Salida procesional del Martes'),
      (5,'2026-02-12','2026-02-12','Martes'),
      (7,'2026-02-16','2026-02-16','Miercoles');


-- =========================
-- GRUPO
-- =========================
INSERT INTO grupo (
    id, name, description, image_id
) VALUES
      (1,'Cruz de Guia','Grupo que abre el cortejo',NULL),
      (2,'Nazarenos Cristo','Nazarenos delante del Cristo',NULL),
      (3,'Acolitos Cristo','Acolitos del Cristo',NULL),
      (4,'Paso Cristo','Paso procesional del Cristo',NULL),
      (5,'Banda Cristo','Banda del Cristo',NULL);


-- =========================
-- EVENTO
-- =========================
INSERT INTO evento (
    id, fecha, description, id_grupo, title
) VALUES
      (1,'2026-01-31','Convocatoria de costaleros',4,'Igualá'),
      (2,'2026-02-21','Ensayo Solidario',4,'Ensayo');


-- =========================
-- NOTICIA
-- =========================
INSERT INTO noticia (
    id, title, fecha, description, image_id
) VALUES
      (8,'rafa','2026-02-13','rafa',NULL),
      (9,'rayo','2026-02-13','rayo cachau',NULL);


-- =========================
-- TRAMO_GRUPO_USUARIO
-- =========================
INSERT INTO tramo_grupo_usuario (
    id_tramo, id_grupo, id_usuario
) VALUES
      (2,2,17),
      (2,4,4),
      (7,3,17);