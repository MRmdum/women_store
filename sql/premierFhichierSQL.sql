DROP DATABASE IF EXISTS women_store;
CREATE DATABASE IF NOT EXISTS women_store;
USE women_store;

#User root/root

#Création des tables

CREATE TABLE client (
  id_client INT NOT NULL AUTO_INCREMENT,
  email_client VARCHAR(255) NOT NULL UNIQUE,
  nom_client VARCHAR(255) NOT NULL,
  prenom_client VARCHAR(255) NOT NULL,
  telephone_client VARCHAR(255) NOT NULL, #VARCHAR car pas d'intérêt de stocker un aussi grand int 
  mot_de_passe_client VARCHAR(255) NOT NULL,
  adresse_facturation_client VARCHAR(255) NOT NULL,
  numero_carte_credit_client VARCHAR(255) NOT NULL, #VARCHAR car trop long en int
  grade_fidelite VARCHAR(255) NOT NULL default 'Sans_Grade',
  privilege INT default 0,
  PRIMARY KEY (id_client)
);

CREATE TABLE commande (
  id_commande INT NOT NULL AUTO_INCREMENT,
  id_client INT NOT NULL,
  nb_produit int not null,
  date_commande DATE NOT NULL,
  prix_commande DECIMAL NOT NULL,
  etat_commande VARCHAR(255) NOT NULL,
  reduc_appliquee INT DEFAULT 0,
  PRIMARY KEY (id_commande)
);
create table produits_dans_commande (
  id_produits_dans_commande INT NOT NULL AUTO_INCREMENT,
  id_produit1 INT NOT NULL,
  id_command int not null,
  reduc_appliquee INT DEFAULT 0,
  PRIMARY KEY (id_produits_dans_commande)
);
CREATE TABLE produit (
  id_produit INT NOT NULL AUTO_INCREMENT,
  taille INT NOT NULL,
  prix DECIMAL NOT NULL,
  nombre_stock int NOT NULL,
  reduc_appliquee INT DEFAULT 0,
  PRIMARY KEY (id_produit)
);

INSERT INTO produit (taille, prix, nombre_stock) VALUES
(42,12,5),
(43,17,4),
(34,8,5),
(48,12,6)
