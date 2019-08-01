--liquibase formatted sql

--changeset andre:02
--comment: Criando a estrutura da tabela USUARIO

CREATE TABLE
IF NOT EXISTS `tg_bancodedados`.`usuario`
(
  `usu_id` BIGINT
(20) NOT NULL AUTO_INCREMENT,
  `usu_idcad` VARCHAR
(50) NOT NULL,
  `usu_disciplina` VARCHAR
(100) NULL DEFAULT NULL,
  `usu_email` VARCHAR
(50) NULL DEFAULT NULL,
  `usu_nome` VARCHAR
(30) NULL DEFAULT NULL,
  `usu_funcao` VARCHAR
(40) NULL DEFAULT NULL,
  `usu_senha` VARCHAR
(255) NULL DEFAULT NULL,
UNIQUE KEY
(`usu_idcad`),
  PRIMARY KEY
(`usu_id`))ENGINE = InnoDB DEFAULT CHARSET=utf8;
--rollback DROP TABLE `tg_bancodedados`.`usuario`;

--changeset andre:03
--comment: Criando a estrutura da tabela MAQUETE
CREATE TABLE
IF NOT EXISTS `tg_bancodedados`.`maquete`
(
  `maqu_id` BIGINT
(20) NOT NULL,
  `maqu_data` DATE NULL DEFAULT NULL,
  `maqu_cliente` VARCHAR
(100) NULL DEFAULT NULL,
  `maqu_local` VARCHAR
(100) NULL DEFAULT NULL,
  `maqu_descricao` VARCHAR
(255) NULL DEFAULT NULL,
  `maqu_projeto` VARCHAR
(100) NULL DEFAULT NULL,
  PRIMARY KEY
(`maqu_id`))ENGINE = InnoDB DEFAULT CHARSET=utf8;
--rollback DROP TABLE `tg_bancodedados`.`maquete`;

--changeset andre:04
--comment: Criando a estrutura da tabela DESENHO 
CREATE TABLE
IF NOT EXISTS `tg_bancodedados`.`desenho`
(
  `id_desenho` BIGINT
(20) NOT NULL,
`des_revisao` VARCHAR
(3) NOT NULL,
  `des_comentario` VARCHAR
(255) NULL DEFAULT NULL,

  `des_idcad` VARCHAR
(40) NULL DEFAULT NULL,
  `des_data_fim` DATE NULL DEFAULT NULL,
  `des_data_ini` DATE NULL DEFAULT NULL,
  `des_verificador` VARCHAR
(255) NULL DEFAULT NULL,
  `des_status` VARCHAR
(30) NULL DEFAULT NULL,
  `des_tag` VARCHAR
(100) NULL DEFAULT NULL,
`des_contratado` VARCHAR
(100) NULL DEFAULT NULL,
`des_subtitulo`VARCHAR
(100) NULL DEFAULT NULL,
  `des_maquete` BIGINT
(20) NOT NULL,
  `usu_id` BIGINT
(20) NOT NULL,
  PRIMARY KEY
(`id_desenho`),
  INDEX `FKkhmdpddmbq4e5woqunoxfiwsm`
(`des_maquete` ASC),
  INDEX `FKtlraayslm9yi8tqn2b430ew1y`
(`usu_id` ASC),
  CONSTRAINT `FKkhmdpddmbq4e5woqunoxfiwsm`
    FOREIGN KEY
(`des_maquete`)
    REFERENCES `tg_bancodedados`.`maquete`
(`maqu_id`),
  CONSTRAINT `FKtlraayslm9yi8tqn2b430ew1y`
    FOREIGN KEY
(`usu_id`)
    REFERENCES `tg_bancodedados`.`usuario`
(`usu_id`))ENGINE = InnoDB DEFAULT CHARSET=utf8;
--rollback DROP TABLE `tg_bancodedados`.`desenho`;