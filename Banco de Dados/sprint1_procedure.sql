-- -----------------------------------------------------
-- Procedure `proc_amigo` realizar a pesquisa de amigos
-- -----------------------------------------------------
DELIMITER $$
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
-- Procedure `proc_solicitacao` realizar a pesquisa de solicitação de amigos
-- -----------------------------------------------------
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_solicitacao`(IN usuario INT)
BEGIN
START TRANSACTION;
(SELECT * FROM witc.Usuario WHERE id IN 
(SELECT a.idUsuario FROM witc.Usuario_tem_Amigo a WHERE a.idAmigo = usuario AND a.dataAceitacao IS NULL));
COMMIT;
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure `proc_sugestao` realizar a pesquisa de amigos para sugerir
-- -----------------------------------------------------
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_sugestao`(IN usuario INT)
BEGIN
START TRANSACTION;
(SELECT * FROM witc.Usuario WHERE id NOT IN 
(SELECT a.idUsuario FROM witc.Usuario_tem_Amigo a WHERE a.idAmigo = usuario) AND id NOT IN 
(SELECT b.idAmigo FROM witc.Usuario_tem_Amigo b WHERE b.idUsuario = usuario) AND id <> usuario AND ativo = 1);
COMMIT;
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure `proc_convite` realizar a pesquisa de convites existente e grava na solicitação
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