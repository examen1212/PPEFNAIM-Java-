drop database if exist intervention;
create database intervention;
use intervention;

create table client(
	idclient int (3) not null auto_increment,
	nom varchar(20),
	prenom varchar(20),
	adresse varchar(20),
	primary key (idclient)
);
create table technicien(
	idtech int (3) not null auto_increment,
	nom varchar(20),
	prenom varchar(20),
	competence varchar(100),
	primary key (idtech)
);
create table intervention(
	idinter int (3) not null auto_increment,
	description varchar(100),
	dateinter date,
	montant float,
	idclient int (3) not null,
	idtech int (3) not null,
	primary key (idinter),
	foreign key (idclient) references client (idclient),
	foreign key (idtech) references technicien(idtech)
);
create table user(
	login varchar(20) not null,
	mdp varchar(100) not null,
	droits enum ("admin","user","autre"),
	primary key (login)
);

insert into client values (null,"ruben","thibault","rue de paris"), (null,"alicia","laurene","rue de paris");

create view vueglobale as (select C.nom as nomClient, T.nom as nomTech, T.prenom as prenomTech, I.dateinter, I.montant from client C, technicien T, intervention I where C.idclient = I.idclient and T.idtech = I.idtech);