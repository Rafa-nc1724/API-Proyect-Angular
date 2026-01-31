# 📘 Memoria del Proyecto  
## Sistema de Gestión de Hermandad

---

## 1. Introducción

El presente proyecto aborda el **diseño y desarrollo de una base de datos relacional** orientada a la gestión integral de una hermandad. El sistema permite organizar de manera estructurada la información relativa a sus miembros, los grupos procesionales, los tramos horarios de la procesión y los ensayos previos.

La solución propuesta tiene como objetivo facilitar la **organización interna**, optimizar la **consulta de la información** y permitir una **gestión eficaz de roles y funciones**, adaptándose a las necesidades reales de funcionamiento de una hermandad a lo largo del año, con especial relevancia durante la estación de penitencia.

---

## 2. Objetivos del proyecto

### 2.1 Objetivo general

Diseñar y desarrollar una base de datos que permita **modelar, almacenar y consultar** toda la información necesaria para la correcta organización y gestión de una hermandad.

### 2.2 Objetivos específicos

- Gestionar los usuarios y sus distintos roles dentro de la hermandad.  
- Organizar los grupos procesionales y definir su orden dentro del cortejo.  
- Asignar usuarios a grupos y tramos, indicando la función desempeñada en cada caso.  
- Registrar y consultar los ensayos realizados por cada grupo.  
- Facilitar el acceso a la información mediante vistas SQL optimizadas y reutilizables.  

---

## 3. Tecnologías utilizadas

- **Sistema gestor de bases de datos:** MariaDB 10.4  
- **Lenguaje:** SQL (MySQL / MariaDB)  
- **Motor de almacenamiento:** InnoDB  
- **Codificación de caracteres:** UTF-8 (`utf8mb4`)  
- **Gestión temporal:** Timestamps Unix (`BIGINT`)  

---

## 4. Diseño de la base de datos

La base de datos, denominada **`hermandad`**, sigue un **modelo relacional normalizado**, garantizando la integridad y consistencia de los datos mediante el uso de claves primarias y foráneas.

Las tablas principales que conforman el sistema son:

- `usuario`  
- `grupo`  
- `tramo`  
- `ensayo`  
- `tramo_grupo_usuario`  

Cada una de estas tablas cumple una función específica dentro del sistema y se relaciona de forma coherente con el resto para reflejar la estructura organizativa de la hermandad.

---

## 5. Descripción de las tablas

### 5.1 Tabla `usuario`

Almacena la información personal y organizativa de cada miembro de la hermandad.

**Campos principales:**

- Identificador único (`id`).  
- Datos personales: nombre, DNI, dirección, teléfono y correo electrónico.  
- Credenciales de acceso al sistema.  
- Estado de activación del usuario.  
- Rol asignado (`admin`, `usuario`, `capataz`, `junta`).  

Esta tabla permite implementar mecanismos de **control de acceso**, **gestión de permisos** y diferenciación de responsabilidades dentro del sistema.

---

### 5.2 Tabla `grupo`

Representa los distintos **grupos procesionales** que conforman el cortejo.

Cada grupo dispone de un nombre y una descripción. Su identificador permite establecer de forma implícita el **orden procesional**, facilitando la organización del desfile.

---

### 5.3 Tabla `tramo`

Define los **intervalos temporales** en los que se divide la procesión.

Los campos de salida y entrada se almacenan como **timestamps Unix**, lo que simplifica el tratamiento horario, los cálculos temporales y la conversión a formatos de fecha y hora legibles.

---

### 5.4 Tabla `ensayo`

Registra los ensayos realizados por los distintos grupos procesionales.

Cada ensayo se asocia a un grupo mediante una clave foránea, lo que permite llevar un **control cronológico** de la actividad de preparación a lo largo del tiempo.

---

### 5.5 Tabla `tramo_grupo_usuario`

Tabla intermedia que relaciona:

- Usuarios  
- Grupos  
- Tramos  

Incluye además la **función desempeñada** por cada usuario, como por ejemplo:

- Músico  
- Costalero  
- Nazareno  
- Administrativa  

Esta tabla constituye el **núcleo funcional del sistema**, ya que refleja la participación real de cada miembro en la procesión.

---

## 6. Vistas de la base de datos

Con el fin de simplificar las consultas y mejorar el rendimiento, se han definido diversas **vistas SQL**.

### 6.1 Usuarios por tramo y grupo

Permite conocer qué usuarios participan en cada tramo y grupo, junto con la función que desempeñan.

### 6.2 Perfil de usuario

Muestra la información completa de cada usuario, incluyendo los grupos, tramos y funciones asignadas.

### 6.3 Tramos formateados

Convierte los timestamps Unix en fechas y horas legibles para una correcta visualización.

### 6.4 Organización del cortejo

Representa el cortejo completo ordenado según la estructura procesional definida.

### 6.5 Usuarios por función

Clasifica a los usuarios en función del papel que desempeñan dentro de la hermandad.

### 6.6 Calendario de ensayos

Muestra los ensayos ordenados cronológicamente junto con el grupo responsable.

### 6.7 Usuarios por rol

Clasifica a los usuarios según su rol, facilitando la gestión administrativa y el control de permisos.

---

## 7. Conclusiones

El sistema diseñado proporciona una solución **robusta, escalable y estructurada** para la gestión de una hermandad. El uso de un modelo relacional normalizado junto con vistas específicas permite:

- Reducir la complejidad de las consultas.  
- Mejorar la mantenibilidad del sistema.  
- Facilitar la integración con aplicaciones web.  
- Garantizar la coherencia e integridad de los datos.  

---

## 8. Posibles ampliaciones

- Gestión de asistencia a ensayos.  
- Histórico de procesiones por año.  
- Gestión documental.  
- Sistema de notificaciones.  
- Auditoría y registro de cambios.  
