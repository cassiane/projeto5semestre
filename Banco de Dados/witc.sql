-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema witc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema witc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `witc` DEFAULT CHARACTER SET utf8 ;
USE `witc` ;

-- -----------------------------------------------------
-- Table `witc`.`tipoperfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`tipoperfil` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoPerfil` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`usuario` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `dataAniversario` DATE NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `foto` LONGBLOB NULL DEFAULT NULL,
  `fotoCapa` LONGBLOB NULL DEFAULT NULL,
  `senha` VARCHAR(64) NOT NULL,
  `status` ENUM('Pensando', 'Editando', 'Revisando', 'Lendo') NOT NULL DEFAULT 'Pensando',
  `ativo` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`perfil` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(10) UNSIGNED NOT NULL,
  `idTipoPerfil` INT(10) UNSIGNED NOT NULL,
  `avaliacao` INT(10) UNSIGNED NULL DEFAULT '0',
  `qtdAvaliacoes` INT(10) UNSIGNED NULL DEFAULT '0',
  `somaAvaliacoes` FLOAT UNSIGNED NULL DEFAULT '0',
  `pseudonimo` VARCHAR(100) NULL DEFAULT NULL,
  `perfilPadrao` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `fk_Perfil_Usuario1_idx` (`idUsuario` ASC),
  INDEX `fk_Perfil_TipoPerfil1_idx` (`idTipoPerfil` ASC),
  CONSTRAINT `fk_Perfil_TipoPerfil1`
    FOREIGN KEY (`idTipoPerfil`)
    REFERENCES `witc`.`tipoperfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Perfil_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`tipogenero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`tipogenero` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoGenero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`tipotexto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`tipotexto` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoTexto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`livro` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idTipoGenero` INT(10) UNSIGNED NULL DEFAULT NULL,
  `idTipoTexto` INT(10) UNSIGNED NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `nroPaginas` INT(11) NULL DEFAULT NULL,
  `capa` LONGBLOB NULL DEFAULT NULL,
  `classificacao` VARCHAR(45) NOT NULL,
  `disponivelBiblioteca` TINYINT(1) NOT NULL,
  `reportadoConteudoImproprio` TINYINT(1) NOT NULL,
  `avaliacao` FLOAT UNSIGNED NULL DEFAULT '0',
  `qtdAvaliacoes` INT(10) UNSIGNED NULL DEFAULT '0',
  `somaAvaliacoes` FLOAT UNSIGNED NULL DEFAULT '0',
  `texto` LONGTEXT NOT NULL,
  `bookLock` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `revisao` TINYINT(1) NOT NULL DEFAULT '0',
  `disponivelRevisao` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_Livro_TipoGenero1_idx` (`idTipoGenero` ASC),
  INDEX `fk_Livro_TipoTexto1_idx` (`idTipoTexto` ASC),
  CONSTRAINT `fk_Livro_TipoGenero1`
    FOREIGN KEY (`idTipoGenero`)
    REFERENCES `witc`.`tipogenero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_TipoTexto1`
    FOREIGN KEY (`idTipoTexto`)
    REFERENCES `witc`.`tipotexto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`convidadoperfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`convidadoperfil` (
  `idPerfil` INT(10) UNSIGNED NOT NULL,
  `idPerfilConvidado` INT(10) UNSIGNED NOT NULL,
  `idLivro` INT(10) UNSIGNED NOT NULL,
  `dataSolicitacao` DATETIME NOT NULL,
  UNIQUE INDEX `un_ConvidadoPerfil_1` (`idPerfilConvidado` ASC, `idLivro` ASC),
  INDEX `fk_ConvidadoPerfil_1` (`idPerfil` ASC),
  INDEX `fk_ConvidadoPerfil_3` (`idLivro` ASC),
  CONSTRAINT `fk_ConvidadoPerfil_1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `witc`.`perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ConvidadoPerfil_2`
    FOREIGN KEY (`idPerfilConvidado`)
    REFERENCES `witc`.`perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ConvidadoPerfil_3`
    FOREIGN KEY (`idLivro`)
    REFERENCES `witc`.`livro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`convidadousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`convidadousuario` (
  `idUsuario` INT(10) UNSIGNED NOT NULL,
  `emailConvidado` VARCHAR(150) NOT NULL,
  `dataSolicitacao` DATE NULL DEFAULT NULL,
  INDEX `fk_ConvidadoUsuario_Usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_ConvidadoUsuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`desafios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`desafios` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`desafiospalavras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`desafiospalavras` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `palavra` VARCHAR(100) NOT NULL,
  `idDesafio` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`desafiosusuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`desafiosusuarios` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(10) NOT NULL,
  `idUsuarioDesafiante` INT(10) NOT NULL,
  `idDesafio` INT(10) NOT NULL,
  `numeroPalavras` INT(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`tipostatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`tipostatus` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipoStatus` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`historicolivros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`historicolivros` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idPerfil` INT(10) UNSIGNED NOT NULL,
  `idTipoStatus` INT(10) UNSIGNED NOT NULL,
  `idLivro` INT(10) UNSIGNED NOT NULL,
  `dataInicio` DATETIME NOT NULL,
  `dataConclusao` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ui_HistoricoLivro_PerfilLivro` (`idPerfil` ASC, `idLivro` ASC),
  INDEX `fk_HistoricoLivros_Perfil1_idx` (`idPerfil` ASC),
  INDEX `fk_HistoricoLivros_TipoStatus1_idx` (`idTipoStatus` ASC),
  INDEX `fk_HistoricoLivros_Livro1_idx` (`idLivro` ASC),
  CONSTRAINT `fk_HistoricoLivros_Livro1`
    FOREIGN KEY (`idLivro`)
    REFERENCES `witc`.`livro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoLivros_Perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `witc`.`perfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoricoLivros_TipoStatus1`
    FOREIGN KEY (`idTipoStatus`)
    REFERENCES `witc`.`tipostatus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`recuperarconta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`recuperarconta` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(10) UNSIGNED NOT NULL,
  `dataSolicitacao` DATETIME NOT NULL,
  `dataUtilizacao` DATETIME NULL DEFAULT NULL,
  `hashRecuperacaoSenha` VARCHAR(64) NOT NULL,
  `inutilizado` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_RecuperacaoSenha_Usuario1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_RecuperacaoSenha_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`revisor_tem_tipotexto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`revisor_tem_tipotexto` (
  `idUsuario` INT(11) NOT NULL,
  `idTipoTexto` INT(11) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`tipoperfil_tem_tipostatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`tipoperfil_tem_tipostatus` (
  `TipoPerfil_id` INT(10) UNSIGNED NOT NULL,
  `TipoStatus_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TipoPerfil_id`, `TipoStatus_id`),
  INDEX `fk_TipoPerfil_has_TipoStatus_TipoStatus1_idx` (`TipoStatus_id` ASC),
  INDEX `fk_TipoPerfil_has_TipoStatus_TipoPerfil1_idx` (`TipoPerfil_id` ASC),
  CONSTRAINT `fk_TipoPerfil_has_TipoStatus_TipoPerfil1`
    FOREIGN KEY (`TipoPerfil_id`)
    REFERENCES `witc`.`tipoperfil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TipoPerfil_has_TipoStatus_TipoStatus1`
    FOREIGN KEY (`TipoStatus_id`)
    REFERENCES `witc`.`tipostatus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`usuario_tem_amigo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`usuario_tem_amigo` (
  `idUsuario` INT(10) UNSIGNED NOT NULL,
  `idAmigo` INT(10) UNSIGNED NOT NULL,
  `dataSolicitacao` DATE NULL DEFAULT NULL,
  `dataAceitacao` DATE NULL DEFAULT NULL,
  `amigoStatus` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`, `idAmigo`),
  INDEX `fk_Usuario_has_Usuario_Usuario2_idx` (`idAmigo` ASC),
  INDEX `fk_Usuario_has_Usuario_Usuario1_idx` (`idUsuario` ASC),
  INDEX `idx_Usuario_has_Amigo_idx` (`amigoStatus` ASC, `idUsuario` ASC, `idAmigo` ASC),
  CONSTRAINT `fk_Usuario_has_Usuario_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Usuario_Usuario2`
    FOREIGN KEY (`idAmigo`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `witc`.`usuario_tem_tipotexto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`usuario_tem_tipotexto` (
  `idUsuario` INT(11) NOT NULL,
  `idTipoTexto` INT(11) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `witc` ;

-- -----------------------------------------------------
-- procedure proc_amigo
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_amigo`(IN usuario INT)
BEGIN
START TRANSACTION;
(SELECT * FROM witc.Usuario WHERE id IN 
(SELECT a.idUsuario FROM witc.Usuario_tem_Amigo a WHERE a.idAmigo = usuario AND a.dataAceitacao IS NOT NULL AND a.amigoStatus = TRUE) OR id IN 
(SELECT b.idAmigo FROM witc.Usuario_tem_Amigo b WHERE b.idUsuario = usuario AND b.dataAceitacao IS NOT NULL AND b.amigoStatus = TRUE));
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_convite
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_convite`(IN email VARCHAR(150))
BEGIN
START TRANSACTION;
INSERT INTO witc.Usuario_tem_Amigo (idUsuario, idAmigo, dataSolicitacao)
 SELECT a.idUsuario, b.id, a.dataSolicitacao FROM witc.ConvidadoUsuario a
 JOIN witc.Usuario b ON a.emailConvidado = b.email
 WHERE a.emailConvidado = email AND b.ativo = 1;
DELETE FROM witc.ConvidadoUsuario WHERE emailConvidado = email;
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_edicao
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_edicao`(IN usuario INT, IN livro INT)
BEGIN
START TRANSACTION;
(SELECT a.* FROM witc.Perfil a WHERE a.idUsuario IN 
	(SELECT id FROM witc.Usuario b WHERE b.id IN 
		(SELECT c.idUsuario FROM witc.Usuario_tem_Amigo c WHERE c.idAmigo = usuario AND c.dataAceitacao IS NOT NULL AND c.amigoStatus = TRUE) 
		OR b.id IN 
		(SELECT d.idAmigo FROM witc.Usuario_tem_Amigo d WHERE d.idUsuario = usuario AND d.dataAceitacao IS NOT NULL AND d.amigoStatus = TRUE)) 
	AND a.id NOT IN 
		(SELECT e.idPerfil FROM witc.HistoricoLivros e WHERE e.idLivro = livro) 
	AND a.id NOT IN 
		(SELECT f.idPerfilConvidado FROM witc.ConvidadoPerfil f WHERE f.idLivro = livro));
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_historico_convite
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_historico_convite`(IN perfil INT, IN livro INT)
BEGIN
START TRANSACTION;
INSERT INTO witc.HistoricoLivros (idPerfil, idTipoStatus, idLivro, dataInicio)
 SELECT a.idPerfilConvidado, b.id, a.idLivro, a.dataSolicitacao
  FROM witc.ConvidadoPerfil a INNER JOIN witc.TipoStatus b
  WHERE a.idPerfilConvidado = perfil AND a.idLivro = livro AND b.id = 1;
DELETE FROM witc.ConvidadoPerfil WHERE idPerfilConvidado = perfil AND idLivro = livro;
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_solicitacao
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_solicitacao`(IN usuario INT)
BEGIN
START TRANSACTION;
(SELECT * FROM witc.Usuario WHERE id IN 
(SELECT a.idUsuario FROM witc.Usuario_tem_Amigo a WHERE a.idAmigo = usuario AND a.dataAceitacao IS NULL));
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_sugestao
-- -----------------------------------------------------

DELIMITER $$
USE `witc`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_sugestao`(IN usuario INT)
BEGIN
START TRANSACTION;
(SELECT * FROM witc.Usuario WHERE id NOT IN 
(SELECT a.idUsuario FROM witc.Usuario_tem_Amigo a WHERE a.idAmigo = usuario) AND id NOT IN 
(SELECT b.idAmigo FROM witc.Usuario_tem_Amigo b WHERE b.idUsuario = usuario) AND id <> usuario AND ativo = 1);
COMMIT;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




-- Cria usuário da aplicação:

CREATE USER 'USERWITCAPP'@'LOCALHOST' IDENTIFIED BY 'APPCTIWUSER';

--
-- Configura permissões do usuário da aplicação:
--

GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON witc.* TO 'USERWITCAPP'@'LOCALHOST';