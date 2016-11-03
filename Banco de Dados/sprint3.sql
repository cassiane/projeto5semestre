-- MySQL Script generated by MySQL Workbench
-- Qua 28 Set 2016 17:37:46 BRT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema witc
-- -----------------------------------------------------

USE `witc` ;

ALTER TABLE `witc`.`Usuario` 
ADD COLUMN `status` ENUM('Pensando', 'Editando', 'Revisando', 'Lendo') NOT NULL 
DEFAULT 'Pensando' AFTER `senha`;

ALTER TABLE `witc`.`Perfil`
ADD COLUMN `perfilPadrao` TINYINT(1) NOT NULL
DEFAULT 1;

CREATE TABLE `Usuario_tem_TipoTexto` (
  `idUsuario` int(11) NOT NULL,
  `idTipoTexto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `witc`.`Perfil` 
CHANGE COLUMN `qualificacao` `avaliacao` INT(10) UNSIGNED NULL DEFAULT 0 ,
ADD COLUMN `qtdAvaliacoes` INT(10) UNSIGNED NULL DEFAULT 0 AFTER `avaliacao`,
ADD COLUMN `somaAvaliacoes` FLOAT UNSIGNED NULL DEFAULT 0 AFTER `qtdAvaliacoes`;

INSERT INTO `witc`.`TipoPerfil`
(`tipoPerfil`)
VALUES
("REVISOR");


ALTER TABLE `witc`.`Livro`
ADD COLUMN `revisao` TINYINT(1) NOT NULL
DEFAULT 0,
ADD COLUMN `disponivelRevisao` TINYINT(1) NOT NULL
DEFAULT 0;

INSERT INTO `witc`.`TipoStatus`
(`tipoStatus`)
VALUES
("REVISAO");

ALTER TABLE `witc`.`Livro` 
CHANGE COLUMN `capa` `capa` LONGBLOB NULL DEFAULT NULL;

CREATE TABLE `Revisor_tem_TipoTexto` (
  `idUsuario` int(11) NOT NULL,
  `idTipoTexto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Desafios` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;