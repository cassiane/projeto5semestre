<<<<<<< 485cffe3b3e7e9c42067d6e8c865ed794939e534
INSERT INTO `witc`.`TipoPerfil`
(`tipoPerfil`)
VALUES
("ESCRITOR");


INSERT INTO `witc`.`TipoStatus`
(`tipoStatus`)
VALUES
("PADRAO");


INSERT INTO `witc`.`TipoPerfil_tem_TipoStatus`
(`TipoPerfil_id`,
`TipoStatus_id`)
VALUES
(1,1);


INSERT INTO `witc`.`tipotexto`
(`tipoTexto`)
VALUES
("TERROR");

INSERT INTO `witc`.`tipotexto`
(`tipoTexto`)
VALUES
("BIOGRAFIA");
=======
INSERT INTO `witc`.`tipoperfil`
(`tipoPerfil`)
VALUES
("ESCRITOR");


INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("PADRAO");

INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("REVISAO");


INSERT INTO `witc`.`tipoperfil_tem_tipostatus`
(`TipoPerfil_id`,
`TipoStatus_id`)
VALUES
(1,1);


>>>>>>> Implementacao SB12 - lista revisao
