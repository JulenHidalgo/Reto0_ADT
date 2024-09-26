CREATE DATABASE examendb;

USE examendb;

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
disponible boolean
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
