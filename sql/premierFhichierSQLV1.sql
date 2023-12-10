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
Drop schema women_store;
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
-- Table `women_store`.`commande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`commande` (
  `id_commande` INT NOT NULL AUTO_INCREMENT,
  `id_client` INT NOT NULL,
  `date_commande` DATE NOT NULL,
  PRIMARY KEY (`id_commande`),
  INDEX `fk_client_idx` (`id_client` ASC) VISIBLE,
  CONSTRAINT `fk_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `women_store`.`client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `women_store`.`produit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`produit` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Categorie` ENUM('Accesoire', 'Vêtement', 'Chaussure') NULL DEFAULT NULL,
  `Prix` DECIMAL(10,0) NOT NULL,
  `Taille` INT NULL DEFAULT NULL,
  `Stock` INT NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `women_store`.`produit_vendu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`produit_vendu` (
  `Id_vendu` INT NOT NULL AUTO_INCREMENT,
  `Id_produit` INT NOT NULL,
  `reduc_appliquee` INT NULL DEFAULT '0',
  PRIMARY KEY (`Id_vendu`),
  INDEX `fk_produit_idx` (`Id_produit` ASC) VISIBLE,
  CONSTRAINT `fk_produit`
    FOREIGN KEY (`Id_produit`)
    REFERENCES `women_store`.`produit` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `women_store`.`produits_dans_commande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `women_store`.`produits_dans_commande` (
  `id_produits_dans_commande` INT NOT NULL AUTO_INCREMENT,
  `id_produit1` INT NOT NULL,
  `id_command` INT NOT NULL,
  `reduc_appliquee` INT NULL DEFAULT '0',
  PRIMARY KEY (`id_produits_dans_commande`),
  INDEX `fk_commande_idx` (`id_command` ASC) VISIBLE,
  INDEX `fk_produit_commandé_idx` (`id_produit1` ASC) VISIBLE,
  CONSTRAINT `fk_commande`
    FOREIGN KEY (`id_command`)
    REFERENCES `women_store`.`commande` (`id_commande`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produit_commandé`
    FOREIGN KEY (`id_produit1`)
    REFERENCES `women_store`.`produit_vendu` (`Id_vendu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
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
('Accesoire',12,8),
('Accesoire',10,3);

INSERT INTO produit_vendu (Id_produit,reduc_appliquee) values
(7,0),
(8,5);
