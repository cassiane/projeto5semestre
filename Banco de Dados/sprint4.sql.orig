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


use witc;
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