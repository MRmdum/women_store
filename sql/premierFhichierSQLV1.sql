-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema women_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema women_store
-- -----------------------------------------------------
drop schema if exists `women_store` ;
CREATE SCHEMA IF NOT EXISTS `women_store` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `women_store` ;

-- -----------------------------------------------------
-- Table `women_store`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`client` (
  `id_client` INT NOT NULL AUTO_INCREMENT,
  `nom_client` VARCHAR(255) NOT NULL,
  `prenom_client` VARCHAR(255) NOT NULL,
  `telephone_client` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_client`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `women_store`.`produit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`produit` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Categorie` ENUM('Accessoire', 'Vêtement', 'Chaussure') NULL DEFAULT NULL,
  `Prix` DECIMAL(10,2) NOT NULL,
  `Taille` INT NULL DEFAULT NULL,
  `Stock` INT NOT NULL,
  `Descriptif` VARCHAR(255),
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `women_store`.`Commande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`Commande` (
  `Id_commande` INT NOT NULL AUTO_INCREMENT,
  `Type_produit` ENUM('Accessoire', 'Vêtement', 'Chaussure') NULL DEFAULT NULL,
  `reduc_appliquee` DECIMAL(10,2) NULL DEFAULT '0',
  `Id_client` INT NULL,
  `quantite` INT NOT NULL,
  `Descriptif` VARCHAR(255) DEFAULT NULL,
  `prix_vendu_unite` DECIMAL NULL,
  PRIMARY KEY (`Id_commande`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- --- Remplissage --- --
INSERT INTO produit (Categorie,Taille, Prix, Stock) VALUES
('Chaussure',42,12,5),
('Chaussure',43,17,4),
('Vêtement',34,8,5),
('Vêtement',48,12,6);

INSERT INTO produit (Categorie, Prix, Stock) VALUES
('Accessoire',12,8),
('Accessoire',10,3);

Insert into commande(Type_produit,quantite,prix_vendu_unite) values
('Chaussure',5,15),
('Accessoire',3,120),
('Vêtement',5,1),
('Vêtement',1,-2000);

Insert into client(nom_client,prenom_client,telephone_client) values
('Maison','Mere','0485359696'),
('client','premier','888888'),
('test_client','super','777777');

select * from client;
select * from produit;
select * from commande;
Insert into Commande(Type_produit,reduc_appliquee,Id_client, quantite, Descriptif,prix_vendu_unite) values ('Chaussure',3.3999999999999986,1,1,'aled',12);