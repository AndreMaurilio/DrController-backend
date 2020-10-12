--liquibase formatted sql

--changeset andre:06
--comment: Criando a estrutura da tabela Autorizacao

CREATE TABLE
IF NOT EXISTS `tg_bancodedados`.`aut_autorizacao`
(
  `aut_id` BIGINT
(20)  NOT NULL AUTO_INCREMENT,
  `aut_nome` VARCHAR
(35) NOT NULL,
  PRIMARY KEY
(`aut_id`),
  --UNIQUE INDEX `UNI_AUT_NOME` (`AUT_NOME` ASC))ENGINE = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET=utf8;
    UNIQUE INDEX UNI_AUT_NOME
(`aut_nome` ASC))ENGINE = InnoDB DEFAULT CHARSET=utf8;

--ROLLBACK drop table AUT_AUTORIZACAO;

--changeset andre:07
--comment: Criando a estrutura da tabela UAU_USUARIO_AUTORIZACAO
CREATE TABLE
IF NOT EXISTS `tg_bancodedados`.`uau_usuario_autorizacao`
( 

  `usu_id` BIGINT
(20)  NOT NULL,
  `aut_id` BIGINT
(20)  NOT NULL,
  PRIMARY KEY
(`usu_id`),
  INDEX `aut_autorizacao_fk`
(`aut_id` ASC),
  CONSTRAINT `uau_usuario_autorizacao_ibfk_1`
    FOREIGN KEY
(`usu_id`)
    REFERENCES `tg_bancodedados`.`usuario`
(`usu_id`)
    ON
UPDATE CASCADE,
  CONSTRAINT `uau_usuario_autorizacao_ibfk_2`
    FOREIGN KEY
(`aut_id`)
    REFERENCES `tg_bancodedados`.`aut_autorizacao`
(`aut_id`)
    ON
UPDATE CASCADE)ENGINE = InnoDB
DEFAULT CHARSET=utf8;
--ROLLBACK drop table UAU_USUARIO_AUTORIZACAO;

--changeset andre:08
INSERT INTO tg_bancodedados.usuario
  (usu_idcad,usu_disciplina,usu_email,usu_nome,usu_funcao,usu_senha)
VALUES
  ('André', 'TUBULAÇÃO', 'lob@petro.com', 'Gustavo', 'Engenheiro',
    CONCAT
('{MD5}', MD5
('banho2021')))

--rollback delete from tg_bancodedados.usuario where usu_id=3566;

--changeset andre:09
INSERT INTO `tg_bancodedados`.`usuario`
  (usu_idcad,usu_disciplina,usu_email,usu_nome,usu_funcao,usu_senha)
VALUES
  ('MacPro-RN', 'TUBULAÇÃO', 'ronald@petro.com', 'Ronaldo', 'Supervisor', CONCAT
('{MD5}', MD5
('corre11')))

--rollback delete from tg_bancodedados.usuario where usu_id=8977;

--changeset andre:10
INSERT INTO `tg_bancodedados`.`usuario`
  (usu_idcad,usu_disciplina,usu_email,usu_nome,usu_funcao,usu_senha)
VALUES
  ('ASUS-ICQ', 'TI', 'muh@petro.com', 'Murilo', 'Administrador', CONCAT
('{MD5}', MD5
('pizza900')))

--rollback delete from tg_bancodedados.usuario where usu_id=1008;



--changeset andre:11
INSERT INTO `tg_bancodedados`.`aut_autorizacao`
  (`aut_nome`)
VALUES
  ('ROLE_DESEN');

--rollback delete from tg_bancodedados.aut_autorizacao where AUT_NOME='ROLE_DESEN';

--changeset andre:12
INSERT INTO `tg_bancodedados`.`aut_autorizacao`
  (`aut_nome`)
VALUES
  ('ROLE_SUP');

--rollback delete from tg_bancodedados.aut_autorizacao where AUT_NOME='ROLE_SUP';

--changeset andre:13
INSERT INTO `tg_bancodedados`.`aut_autorizacao`
  (`aut_nome`)
VALUES
  ('ROLE_ADMIN');

--rollback delete from tg_bancodedados.aut_autorizacao where AUT_NOME='ROLE_ADMIN';

--changeset andre:14
INSERT INTO `tg_bancodedados`.`uau_usuario_autorizacao`
  (usu_id, aut_id)
SELECT usu_id, AUT_ID
FROM usuario, aut_autorizacao
WHERE usu_nome = 'Gustavo'
  AND AUT_NOME = 'ROLE_DESEN';

--changeset andre:15
INSERT INTO tg_bancodedados.uau_usuario_autorizacao
  (usu_id, AUT_ID)
SELECT usu_id, AUT_ID
FROM usuario, aut_autorizacao
WHERE usu_nome = 'Murilo'
  and aut_nome ="ROLE_ADMIN"
;

--changeset andre:16
INSERT INTO tg_bancodedados.uau_usuario_autorizacao
  (usu_id, AUT_ID)
SELECT usu_id, AUT_ID
FROM usuario, aut_autorizacao
WHERE usu_nome = 'Ronaldo'
  and aut_nome ="ROLE_SUP" ;