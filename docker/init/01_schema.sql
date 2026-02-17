DROP TABLE IF EXISTS tramo_grupo_usuario;
DROP TABLE IF EXISTS noticia;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS grupo;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS tramo;
DROP TABLE IF EXISTS sesiones;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
                         id INT NOT NULL AUTO_INCREMENT,
                         nombre VARCHAR(100) NOT NULL,
                         dni VARCHAR(100) NOT NULL,
                         direccion VARCHAR(100) NOT NULL,
                         telefono VARCHAR(100),
                         email VARCHAR(100) NOT NULL,
                         pass VARCHAR(100) NOT NULL,
                         activo TINYINT NOT NULL DEFAULT 1,
                         rol SET('admin','usuario','capataz','junta') NOT NULL DEFAULT 'usuario',
                         fecha_ingreso DATE,
                         PRIMARY KEY (id),
                         UNIQUE KEY uk_usuario_email (email)
);

CREATE TABLE sesiones (
                          id INT NOT NULL AUTO_INCREMENT,
                          id_usuario INT NOT NULL,
                          token LONGTEXT NOT NULL,
                          fecha_emision BIGINT UNSIGNED NOT NULL,
                          fecha_expiracion BIGINT UNSIGNED NOT NULL,
                          finguer_print LONGTEXT NOT NULL,
                          active TINYINT NOT NULL DEFAULT 1,
                          PRIMARY KEY (id),
                          CONSTRAINT sesiones_usuario_FK
                              FOREIGN KEY (id_usuario) REFERENCES usuario(id)
                                  ON DELETE CASCADE
);

CREATE TABLE tramo (
                       id INT NOT NULL AUTO_INCREMENT,
                       go_out DATE NOT NULL,
                       enter DATE NOT NULL,
                       description VARCHAR(100) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE image (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       type VARCHAR(100) NOT NULL,
                       base64 LONGTEXT NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY images_unique (name)
);

CREATE TABLE grupo (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       description TEXT,
                       image_id INT,
                       PRIMARY KEY (id),
                       CONSTRAINT grupo_image_FK
                           FOREIGN KEY (image_id) REFERENCES image(id)
);

CREATE TABLE evento (
                        id INT NOT NULL AUTO_INCREMENT,
                        fecha DATE NOT NULL,
                        description VARCHAR(100) NOT NULL,
                        id_grupo INT,
                        title VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT ensayo_grupo_FK
                            FOREIGN KEY (id_grupo) REFERENCES grupo(id)
                                ON DELETE CASCADE
);

CREATE TABLE noticia (
                         id INT NOT NULL AUTO_INCREMENT,
                         title VARCHAR(100) NOT NULL,
                         fecha DATE DEFAULT CURDATE(),
                         description TEXT NOT NULL,
                         image_id INT,
                         PRIMARY KEY (id),
                         CONSTRAINT noticia_image_FK
                             FOREIGN KEY (image_id) REFERENCES image(id)
);

CREATE TABLE tramo_grupo_usuario (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     id_tramo INT NOT NULL,
                                     id_grupo INT NOT NULL,
                                     id_usuario INT NOT NULL,
                                     PRIMARY KEY (id),
                                     UNIQUE KEY uk_tramo_grupo_usuario (id_tramo, id_grupo, id_usuario),
                                     CONSTRAINT tramo_grupo_FK
                                         FOREIGN KEY (id_grupo) REFERENCES grupo(id)
                                             ON DELETE CASCADE,
                                     CONSTRAINT tramo_usuario_FK
                                         FOREIGN KEY (id_usuario) REFERENCES usuario(id)
                                             ON DELETE CASCADE,
                                     CONSTRAINT tramo_salida_entrada_FK
                                         FOREIGN KEY (id_tramo) REFERENCES tramo(id)
);