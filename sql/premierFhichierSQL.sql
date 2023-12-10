DROP DATABASE IF EXISTS women_store;
CREATE DATABASE IF NOT EXISTS women_store;
USE women_store;

#Création des tables

CREATE TABLE client (
  id_client INT NOT NULL AUTO_INCREMENT,
  nom_client VARCHAR(255) NOT NULL,
  prenom_client VARCHAR(255) NOT NULL,
  telephone_client VARCHAR(255) NOT NULL,  
  PRIMARY KEY (id_client)
);

CREATE TABLE commande (
  id_commande INT NOT NULL AUTO_INCREMENT,
  id_client INT NOT NULL,
  date_commande DATE NOT NULL,
  PRIMARY KEY (id_commande)
);

create table produits_dans_commande (
  id_produits_dans_commande INT NOT NULL AUTO_INCREMENT,
  id_produit1 INT NOT NULL,
  id_command int not null,
  reduc_appliquee INT DEFAULT 0,
  PRIMARY KEY (id_produits_dans_commande)
);

create table produit_vendu(
	Id_vendu int not null auto_increment,
    Id_produit int not null,
    reduc_appliquee int default 0,
    primary key (Id_vendu)
    );

CREATE TABLE produit (
  Id INT NOT NULL AUTO_INCREMENT,
  Categorie enum('Accesoire','Vêtement','Chaussure'),
  Prix DECIMAL NOT NULL,
  Taille INT,  
  Stock int NOT NULL,
  PRIMARY KEY (Id)
);

INSERT INTO produit (Categorie,Taille, Prix, Stock) VALUES
('Chaussure',42,12,5),
('Chaussure',43,17,4),
('Vêtement',34,8,5),
('Vêtement',48,12,6);

INSERT INTO produit (Categorie, Prix, Stock) VALUES
('Accesoire',12,8),
('Accesoire',10,3)


