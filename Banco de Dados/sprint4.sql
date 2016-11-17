CREATE TABLE `witc`.`Messenger` (
  `idMessenger` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSend` INT(10) UNSIGNED NOT NULL,
  `idReceive` INT(10) UNSIGNED NOT NULL,
  `msn` VARCHAR(255) NULL,
  `msnFoto` LONGBLOB NULL,
  `seen` TINYINT(1) NOT NULL DEFAULT 0,
  `dateSend` DATETIME NOT NULL,
  PRIMARY KEY (`idMessenger`, `idSend`, `idReceive`),
  INDEX `fk_Messenger_Usuario_Send_idx` (`idSend` ASC),
  INDEX `fk_Messenger_Usuario_Receive_idx` (`idReceive` ASC),
  INDEX `id_Messenger_Get_idx` (`date` ASC, `idSend` ASC, `idReceive` ASC),
  CONSTRAINT `fk_Messenger_Usuario_Send`
    FOREIGN KEY (`idSend`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messenger_Usuario_Receive`
    FOREIGN KEY (`idReceive`)
    REFERENCES `witc`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

