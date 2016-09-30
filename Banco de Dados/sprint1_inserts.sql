INSERT INTO `witc`.`tipoperfil`
(`tipoPerfil`)
VALUES
("ESCRITOR");

INSERT INTO `witc`.`tipoperfil`
(`tipoPerfil`)
VALUES
("REVISOR");


INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("EDICAO");
INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("REVISAO");
INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("FINALIZACAO");
INSERT INTO `witc`.`tipostatus`
(`tipoStatus`)
VALUES
("PUBLICACAO");





INSERT INTO `witc`.`tipoperfil_tem_tipostatus`
(`TipoPerfil_id`,
`TipoStatus_id`)
VALUES
(1,1);

INSERT INTO `witc`.`tipogenero`
(`tipoGenero`)
VALUES
("PROSA");
INSERT INTO `witc`.`tipogenero`
(`tipoGenero`)
VALUES
("CONTO");
INSERT INTO `witc`.`tipogenero`
(`tipoGenero`)
VALUES
("POESIA");

INSERT INTO `witc`.`tipotexto`
(`tipoTexto`)
VALUES
("TERROR");
INSERT INTO `witc`.`tipotexto`
(`tipoTexto`)
VALUES
("FICCAO");
INSERT INTO `witc`.`tipotexto`
(`tipoTexto`)
VALUES
("BIOGRAFIA");





