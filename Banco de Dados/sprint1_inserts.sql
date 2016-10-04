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


