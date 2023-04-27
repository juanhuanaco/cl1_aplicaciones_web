SET GLOBAL time_zone = "+8:00";
-- borra la bd si existe
DROP DATABASE IF EXISTS CL1_HuanacoQuispe;
-- creamos la bd
CREATE DATABASE CL1_HuanacoQuispe;
-- activamos la bd
USE CL1_HuanacoQuispe;

create table tb_doctores(
id_doctor int not null primary key auto_increment,
nom_doctor varchar(60),
especialidad_doctor varchar(30)
);

create table tb_citas(
id_cita	int not null primary key auto_increment,
num_cita int,
fecha_cita date,
nom_paciente_cita varchar(60),
id_doctor int not null references tb_doctores(id_doctor)
);

