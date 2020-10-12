--liquibase formatted sql 

--changeset andre:17
INSERT INTO `tg_bancodedados`.`maquete`
    (maqu_id, maqu_descricao,maqu_data,maqu_local,maqu_projeto,maqu_cliente)
VALUES(202019, 'PROJETO DE GRADUACAO', '2015-09-22', 'SJC/SP', 'REFINARIA', 'FATEC');

--rollback delete from tg_bancodedados.MAQUETE where maqu_id = 202019;