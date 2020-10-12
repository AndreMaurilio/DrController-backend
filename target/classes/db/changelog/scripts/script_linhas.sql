--liquibase formatted sql 

--changeset andre:18
INSERT INTO `tg_bancodedados`.`linha`
    (li_tag,li_material,li_pendencias,li_fluido,li_area,li_site,li_bim_tag,maqu_id)
VALUES('12"-CSS-OP', "ACO", "ND", "AMONIA", "REUSO", "ON-SITE", "CSS-OP", 202019);
--roolback delete from tg_bancodedados.LINHA where li_tag = "12-CSS-OP" and maqu_id = 202019; 

--changeset andre:19
INSERT INTO `tg_bancodedados`.`linha`
    (li_tag,li_material,li_pendencias,li_fluido,li_area,li_site,li_bim_tag,maqu_id)
VALUES('6"-CSS-OP', "ACO", "ND", "AMONIA", "REUSO", "ON-SITE", "CSS-OP", 202019);
--roolback delete from tg_bancodedados.LINHA where li_tag = "6-CSS-OP" and maqu_id = 202019; 

--changeset andre:20
INSERT INTO `tg_bancodedados`.`linha`
    (li_tag,li_material,li_pendencias,li_fluido,li_area,li_site,li_bim_tag,maqu_id)
VALUES('8"-NN-ENX', "CHUMBO", "VALVULAS GAVETA", "ENXOFRE", "GRANULOSO", "OFF-SITE", "NN-ENX", 202019);
--roolback delete from tg_bancodedados.LINHA where li_tag = "8-NN-ENX" and maqu_id = 202019; 

--changeset andre:21
INSERT INTO `tg_bancodedados`.`linha`
    (li_tag,li_material,li_pendencias,li_fluido,li_area,li_site,li_bim_tag,maqu_id)
VALUES('2"-AM-AF', "PVC", "ND", "AGUA", "POTAVEL", "ON-SITE", "AM-AF", 202019);
--roolback delete from tg_bancodedados.LINHA where li_tag = "2-AM-AF" and maqu_id = 202019; 
