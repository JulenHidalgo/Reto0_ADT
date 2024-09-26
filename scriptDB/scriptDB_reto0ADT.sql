-- Borrado de la BD
DROP DATABASE examendb;
-- Creacion de la BD
CREATE DATABASE examendb;
-- Le dice al sistema que tiene que usar la BD
USE examendb;
-- Creacion de tablas
CREATE TABLE UnidadDidactica(
id Integer AUTO_INCREMENT primary key,
acronimo varchar(20),
titulo varchar(30),
evaluacion varchar(20),
descripcion varchar(100));

CREATE TABLE Enunciado(
id Integer AUTO_INCREMENT primary key,
descripcion varchar(100),
nivel enum("ALTA","MEDIA","BAJA"),
disponible boolean,
ruta Varchar(70)
);

CREATE TABLE ConvocatoriaExamen(
convocatoria varchar(20) primary key,
descripcion varchar(100),
fecha Date,
curso varchar(20),
id Integer,
foreign key (id) references Enunciado(id) on delete cascade
);

CREATE TABLE UnidadEnunciado(
id_unidad Integer,
id_enunciado Integer,
primary key(id_unidad, id_enunciado),
foreign key (id_unidad) references UnidadDidactica(id) on delete cascade,
foreign key (id_enunciado) references Enunciado(id) on delete cascade
);
-- Insercion de datos
INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES 
('UD1', 'Matemáticas', 'Final', 'Unidad de álgebra básica'),
('UD2', 'Historia', 'Continua', 'Estudio de la historia moderna'),
('UD3', 'Física', 'Final', 'Mecánica clásica introductoria'),
('UD4', 'Química', 'Continua', 'Conceptos fundamentales de la química'),
('UD5', 'Biología', 'Final', 'Estructura y función celular');

INSERT INTO Enunciado (descripcion, nivel, disponible, ruta) VALUES 
('Resolver una ecuación cuadrática', 'ALTA', true, "enunciados/Enunciado1.txt"),
('Describir la Revolución Francesa', 'MEDIA', true, "enunciados/Enunciado2.txt"),
('Explicar la ley de Newton', 'BAJA', false, "enunciados/Enunciado3.txt"),
('Balancear una reacción química', 'ALTA', true, "enunciados/Enunciado4.txt"),
('Identificar partes de una célula', 'MEDIA', false, "enunciados/Enunciado5.txt");

INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso, id) VALUES 
('Conv2023-01', 'Convocatoria enero 2023', '2023-01-15', 'Matemáticas', 1),
('Conv2023-02', 'Convocatoria febrero 2023', '2023-02-20', 'Historia', 2),
('Conv2023-03', 'Convocatoria marzo 2023', '2023-03-30', 'Física', 3),
('Conv2023-04', 'Convocatoria abril 2023', '2023-04-25', 'Química', 4),
('Conv2023-05', 'Convocatoria mayo 2023', '2023-05-10', 'Biología', 5);

INSERT INTO UnidadEnunciado (id_unidad, id_enunciado) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
