INSERT INTO hermandad.usuario (nombre,dni,direccion,telefono,email,pass,activo,rol,fecha_ingreso) VALUES
	 ('Chemar',
	 '12312612A',
	 'Calle Falsa 123',
	 '123456789',
	 'chema@email.com',
	 '$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',
	 1,
	 'junta',
	 '2026-02-01');

INSERT INTO hermandad.noticia (titulo,fecha,descripcion,imagen) VALUES
	 ('Restauración del señor','2026-02-01','Concluye con éxito la restauración del Señor de la Humildad

La imagen del Señor de la Humildad ha regresado al culto tras culminar un meticuloso proceso de restauración que se ha prolongado durante varios meses y que ha permitido recuperar el esplendor original de esta venerada talla devocional.

Los trabajos han sido realizados por un equipo especializado en conservación de imaginería religiosa, siguiendo criterios estrictos de respeto histórico y artístico. Durante la intervención se ha llevado a cabo una limpieza integral de la policromía, eliminando depósitos de suciedad acumulados con el paso del tiempo, así como la consolidación estructural de la madera para garantizar su estabilidad a largo plazo.

Asimismo, se han corregido pequeñas fisuras provocadas por cambios de temperatura y humedad, y se ha procedido a una reintegración cromática puntual en aquellas zonas donde la policromía presentaba pérdidas, siempre utilizando técnicas reversibles y materiales compatibles con la obra original.

El resultado final devuelve al Señor de la Humildad una expresión serena y profunda, resaltando nuevamente los matices del modelado y la riqueza cromática que caracterizan a la imagen. Desde la hermandad se ha destacado que esta restauración no solo preserva el valor artístico de la talla, sino que refuerza su significado espiritual y patrimonial para los fieles.

La imagen será presentada oficialmente en un acto público de acción de gracias, previo a su reincorporación a los cultos y a la próxima estación de penitencia, un momento especialmente esperado por hermanos y devotos tras este importante hito en la conservación de su patrimonio',NULL);

INSERT INTO hermandad.grupo (nombre,descripcion) VALUES
	 ('Cruz de Guia','Grupo que habre el cortejo'),
	 ('Nazarenos Cristo','Nazarenos que procesionan delante del paso de Cristo'),
	 ('Acolitos Cristo','Parte de los nazarenos que hacen de unión con los nazarenos y el paso de cristo'),
	 ('Paso Cristo','Paso procesional del cristo'),
	 ('Banda Cristo','banda de cornetas y tambores'),
	 ('Nazarenos Virgen','Nazarenos que procesionan delante de el paso de virgen'),
	 ('Acolitos Virgen','Parte de los nazarenos que hacen de unión ente los nazarenos de la virgen y el paso de la virgen'),
	 ('Paso Virgen','Paso procesional de la virgen'),
	 ('Banda Virgen','banda de música de la virgen');

INSERT INTO hermandad.evento (fecha,descripcion,id_grupo,titulo) VALUES
	 ('2026-01-31','Convocatoria de costaleros, para la igualá del paso del señor.',4,'Igualá'),
	 ('2026-02-21','Ensayo Solidario de los costaleros del paso del señor',4,'Ensayo'),
	 ('2026-03-06','Traslado Paso Cristo',4,'Traslado'),
	 ('2026-03-14','Ensayo del paso de Cristo',4,'Ensayo'),
	 ('2026-03-20','Ensayo del paso de Cristo',4,'Ensayo'),
	 ('2026-03-21','Anual fiesta de la cerveza de la Hermandad del señor de los cojones y la puta virgen coño',NULL,'Fiesta de la cerveza');

