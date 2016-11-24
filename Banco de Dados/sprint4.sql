use witc;

CREATE TABLE `HistoriasDesafios` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idTipoTexto` int(10) unsigned NOT NULL,
  `idDesafiosUsuarios` int(10) unsigned NOT NULL,
  `classificacao` varchar(45) NOT NULL,
  `disponivelBiblioteca` tinyint(1) NOT NULL,
  `reportadoConteudoImproprio` tinyint(1) NOT NULL,
  `avaliacao` float unsigned DEFAULT '0',
  `qtdAvaliacoes` int(10) unsigned DEFAULT '0',
  `somaAvaliacoes` float unsigned DEFAULT '0',
  `texto` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_HistoriaDesafio_TipoTextoDesafios_idx` (`idTipoTexto`),
  KEY `fk_HistoriaDesafio_DesafiosUsuarios_idx` (`idDesafiosUsuarios`),
  CONSTRAINT `fk_HistoriaDesafio_TipoTextoDesafios` FOREIGN KEY (`idTipoTexto`) REFERENCES `tipotexto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistoriaDesafio_DesafiosUsuarios` FOREIGN KEY (`idDesafiosUsuarios`) REFERENCES `desafiosusuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `Notificacoes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `texto` longtext NOT NULL,
  `idUsuarioRemetente` int unsigned NOT NULL,
  `idUsuarioDestinatario` int unsigned NOT NULL,
  `idDesafiosUsuarios` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Notificacoes_idUsuarioRemetente` FOREIGN KEY (`idUsuarioRemetente`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notificacoes_idUsuarioDestinatario` FOREIGN KEY (`idUsuarioDestinatario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notificacoes_idDesafiosUsuarios` FOREIGN KEY (`idDesafiosUsuarios`) REFERENCES `desafiosusuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

ALTER TABLE `witc`.`usuario` 
ADD COLUMN `fotoCapa` LONGBLOB NULL DEFAULT NULL AFTER `foto`;

-- -----------------------------------------------------
-- Table `witc`.`Publicacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `witc`.`Publicacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(10) UNSIGNED NOT NULL,
  `idAmigo` INT(10) UNSIGNED NOT NULL,
  `dataPublicacao` DATETIME NOT NULL,
  `mensagemPublicacao` VARCHAR(140) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_timeline_usuario_idx` (`idUsuario` ASC),
  INDEX `fk_timeline_usuario1_idx` (`idAmigo` ASC),
  CONSTRAINT `fk_timeline_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_timeline_usuario1`
    FOREIGN KEY (`idAmigo`)
    REFERENCES `witc`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

insert into witc.tipotexto(tipotexto) values ('DESAFIO');