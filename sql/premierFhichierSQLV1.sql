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

insert into produit (Categorie,Prix,Taille,Stock,Descriptif) values
('Vêtement',30,40,10,'Dress1'),
('Vêtement',40,38,20,'Dress2'),
('Chaussure',20,40,20,'Shoes1'),
('Chaussure',10,-1,20,'Accessory1');

insert into client (nom_client,prenom_client,telephone_client) values
('women','shop','0495459723');

