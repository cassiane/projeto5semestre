-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema witc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema witc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `witc` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `witc` ;

-- -----------------------------------------------------
-- Table `witc`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`Usuario` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `dataAniversario` DATE NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `foto` BLOB NULL,
  `senha` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`TipoPerfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`TipoPerfil` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoPerfil` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`Perfil` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT UNSIGNED NOT NULL,
  `idTipoPerfil` INT UNSIGNED NOT NULL,
  `qualificacao` INT NULL,
  `pseudonimo` VARCHAR(100) NULL,
  INDEX `fk_Perfil_Usuario1_idx` (`idUsuario` ASC),
  INDEX `fk_Perfil_TipoPerfil1_idx` (`idTipoPerfil` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Perfil_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Perfil_TipoPerfil1`
    FOREIGN KEY (`idTipoPerfil`)
    REFERENCES `witc`.`TipoPerfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`TipoStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`TipoStatus` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoStatus` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`TipoPerfil_tem_TipoStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`TipoPerfil_tem_TipoStatus` (
  `TipoPerfil_id` INT UNSIGNED NOT NULL,
  `TipoStatus_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`TipoPerfil_id`, `TipoStatus_id`),
  INDEX `fk_TipoPerfil_has_TipoStatus_TipoStatus1_idx` (`TipoStatus_id` ASC),
  INDEX `fk_TipoPerfil_has_TipoStatus_TipoPerfil1_idx` (`TipoPerfil_id` ASC),
  CONSTRAINT `fk_TipoPerfil_has_TipoStatus_TipoPerfil1`
    FOREIGN KEY (`TipoPerfil_id`)
    REFERENCES `witc`.`TipoPerfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TipoPerfil_has_TipoStatus_TipoStatus1`
    FOREIGN KEY (`TipoStatus_id`)
    REFERENCES `witc`.`TipoStatus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`TipoGenero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`TipoGenero` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoGenero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`TipoTexto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`TipoTexto` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoTexto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`Livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`Livro` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idTipoGenero` INT UNSIGNED NULL,
  `idTipoTexto` INT UNSIGNED NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `nroPaginas` INT NULL,
  `capa` BLOB NULL,
  `classificacao` VARCHAR(45) NOT NULL,
  `disponivelBiblioteca` TINYINT(1) NOT NULL,
  `reportadoConteudoImproprio` TINYINT(1) NOT NULL,
  `qualificacao` INT NOT NULL,
  `texto` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Livro_TipoGenero1_idx` (`idTipoGenero` ASC),
  INDEX `fk_Livro_TipoTexto1_idx` (`idTipoTexto` ASC),
  CONSTRAINT `fk_Livro_TipoGenero1`
    FOREIGN KEY (`idTipoGenero`)
    REFERENCES `witc`.`TipoGenero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_TipoTexto1`
    FOREIGN KEY (`idTipoTexto`)
    REFERENCES `witc`.`TipoTexto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`HistoricoLivros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`HistoricoLivros` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idPerfil` INT UNSIGNED NOT NULL,
  `idTipoStatus` INT UNSIGNED NOT NULL,
  `idLivro` INT UNSIGNED NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataConclusao` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_HistoricoLivros_Perfil1_idx` (`idPerfil` ASC),
  INDEX `fk_HistoricoLivros_TipoStatus1_idx` (`idTipoStatus` ASC),
  INDEX `fk_HistoricoLivros_Livro1_idx` (`idLivro` ASC),
  CONSTRAINT `fk_HistoricoLivros_Perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `witc`.`Perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoLivros_TipoStatus1`
    FOREIGN KEY (`idTipoStatus`)
    REFERENCES `witc`.`TipoStatus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoLivros_Livro1`
    FOREIGN KEY (`idLivro`)
    REFERENCES `witc`.`Livro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`Usuario_tem_Amigo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`Usuario_tem_Amigo` (
  `idUsuario` INT UNSIGNED NOT NULL,
  `idAmigo` INT UNSIGNED NOT NULL,
  `dataSolicitacao` DATE NULL,
  `dataAceitacao` DATE NULL,
  `amigoStatus` TINYINT(1) NULL,
  PRIMARY KEY (`idUsuario`, `idAmigo`),
  INDEX `fk_Usuario_has_Usuario_Usuario2_idx` (`idAmigo` ASC),
  INDEX `fk_Usuario_has_Usuario_Usuario1_idx` (`idUsuario` ASC),
  INDEX `idx_Usuario_has_Amigo_idx` (`amigoStatus` ASC, `idUsuario` ASC, `idAmigo` ASC),
  CONSTRAINT `fk_Usuario_has_Usuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Usuario_Usuario2`
    FOREIGN KEY (`idAmigo`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`RecuperarConta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`RecuperarConta` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT UNSIGNED NOT NULL,
  `dataSolicitacao` DATETIME NOT NULL,
  `dataUtilizacao` DATETIME NULL,
  `hashRecuperacaoSenha` VARCHAR(64) NOT NULL,
  `inutilizado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_RecuperacaoSenha_Usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_RecuperacaoSenha_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `witc`.`ConvidadoUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`ConvidadoUsuario` (
  `idUsuario` INT UNSIGNED NOT NULL,
  `emailConvidado` VARCHAR(150) NOT NULL,
  `dataSolicitacao` DATE NULL,
  INDEX `fk_ConvidadoUsuario_Usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_ConvidadoUsuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
