package br.com.fatec.drawingController.desenho;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;
import br.com.fatec.drawingController.usuario.Usuario;

@Repository
public interface DesenhoRepository extends JpaRepository<Desenho, Long> {

        public Desenho findByDataini(Date data);

        public Desenho findByUsuario(Usuario usuario);

        // public Desenho findByTag(String tag);

        /********** PLANT3D *******************/
        @Query("Select u from Desenho u where u.revisao = ?1 and u.tag = ?2")
        public Desenho findByTagRev(String rev, Linha Tag);

        @Query("Select u from Desenho u where u.tag = ?1 and u.pID = ?2 and u.revisao = ?3")
        public Desenho findByTagPidRev(Linha tag, String pID,String rev);

        // CHECA SE EXISTE DESENHO COM MESMA TAG E REV
        @Query("Select u from Desenho u where u.revisao = ?1 and u.tag = ?2")
        public boolean findByTagRevBol(String rev, Linha Tag);

        @Query("Update Desenho u set u.status = ?1 where u.idDesenho = ?2 ")
        public boolean editStatus(String status, long idDesenho);

        @Transactional
        @Modifying
        @Query("Update Desenho u set u.datafim = ?1, u.status = ?2, u.comentarios = ?3 where u.id = ?4")
        public void editEmissaoFinal(Date datafim, String status, String comentarioFinal, long idDesenho);

        @Query("Select u from Desenho u where u.tag = ?1")
        public List<Desenho> desDeMesmaTag(Linha tag);

        /****************** WEB *********/

        // LISTAGEM TOTAL DE DESENHOS NO PROJETO
        @Query("Select u from Desenho u where u.maquete = ?1")
        public List<Desenho> buscaPorMaquete(Maquete numMaquete);

        // *******LISTAGEM POR STATUS****** */
        // LISTAGEM POR STATUS SEM SELECAO DE PROJETO E CALENDARIO
        @Query("Select u from Desenho u where u.status like ?1 and u.dataini between ?2 and ?3")
        public List<Desenho> listaPorStatus(String status, Date dIni, Date dFim);

        // LISTAGEM POR STATUS COM SELECAO DE PROJETO E CALENDARIO
        @Query("Select u from Desenho u where u.status like ?1 and u.maquete = ?2 and u.dataini between ?3 and ?4")
        public List<Desenho> listaPorProjStatus(String status, Maquete maque, Date dIni, Date dFim);

        // LISTAGEM POR STATUS E DATA DEFAULT SEM SELECAO DE PROJETO
        @Query("Select u from Desenho u where u.status like ?1 and u.dataini between ?2 and SYSDATE()")
        List<Desenho> listaPorStatusDefault(String status, Date dIni);

        // LISTAGEM POR STATUS E DATA DEFAULT COM SELECAO DE PROJETO
        @Query("Select u from Desenho u where u.status like ?1 and u.maquete = ?2 and u.dataini between ?3 and SYSDATE()")
        List<Desenho> listaPorProjeStatusDefault(String status, Maquete maque, Date dIni);

        // ************ CONTAGEM DE DESENHOS **************
        // CONTAGEM COM CALENDARIO E SEM SELECAO DE PROJETO
        @Query("Select count(u) as total from Desenho u where u.status like 'EMITIDO' and u.dataini between ?1 and ?2")
        public Long contagemEmitidoSelec(Date dIni, Date dFim);

        @Query("Select count(u) as total from Desenho u where u.status like 'VERIFICANDO' and u.dataini between ?1 and ?2")
        public Long contagemVerificadoSelec(Date dIni, Date dFim);

        @Query("Select count(u) as total from Desenho u where u.status like 'CANCELADO' and u.dataini between ?1 and ?2")
        public Long contagemCanceladoSelec(Date dIni, Date dFim);

        // CONTAGEM COM CALENDARIO E COM SELECAO DE PROJETO
        @Query("Select count(u) as total from Desenho u where u.status like 'EMITIDO' and u.maquete = ?1 and u.dataini between ?2 and ?3")
        public Long contagemProjEmitidoSelec(Maquete maque, Date dIni, Date dFim);

        @Query("Select count(u) as total from Desenho u where u.status like 'VERIFICANDO' and u.maquete = ?1 and u.dataini between ?2 and ?3")
        public Long contagemProjVerificadoSelec(Maquete maque, Date dIni, Date dFim);

        @Query("Select count(u) as total from Desenho u where u.status like 'CANCELADO' and u.maquete = ?1 and u.dataini between ?2 and ?3")
        public Long contagemProjCanceladoSelec(Maquete maque, Date dIni, Date dFim);

        // STATUS CONTAGEM DEFAULT SEM SELECAO DE PROJETO

        @Query("Select count(u) as total from Desenho u where u.status like 'EMITIDO' and u.dataini between ?1 and SYSDATE()")
        public Long contagemEmitidoDEFAULT(Date dIni);

        @Query("Select count(u) as total from Desenho u where u.status like 'VERIFICANDO' and u.dataini between ?1 and SYSDATE()")
        public Long contagemVerificadoDEFAULT(Date dIni);

        @Query("Select count(u) as total from Desenho u where u.status like 'CANCELADO' and u.dataini between ?1 and SYSDATE()")
        public Long contagemCanceladoDEFAULT(Date dIni);

        // STATUS CONTAGEM DEFAULT COM SELECAO DE PROJETO

        @Query("Select count(u) as total from Desenho u where u.status like 'EMITIDO' and u.maquete = ?1 and u.dataini between ?2 and SYSDATE()")
        public Long contagemProjEmitidoDEFAULT(Maquete maque, Date dIni);

        @Query("Select count(u) as total from Desenho u where u.status like 'VERIFICANDO' and u.maquete = ?1 and u.dataini between ?2 and SYSDATE()")
        public Long contagemProjVerificadoDEFAULT(Maquete maque, Date dIni);

        @Query("Select count(u) as total from Desenho u where u.status like 'CANCELADO' and  u.maquete = ?1 and u.dataini between ?2 and SYSDATE()")
        public Long contagemProjCanceladoDEFAULT(Maquete maque, Date dIni);

        // ************GRAFICO DE PROGRESSÃO************* */

        // QUERY DEFAULT
        @Query("Select NEW br.com.fatec.drawingController.usuario.BodyDesGraficoDTO(DATE_FORMAT(d.dataini,'%Y-%c-%e') as DATA,"
                        + "sum(case when d.status = 'EMITIDO' then 1 else 0 END) as EMITIDO,"
                        + "sum(case when d.status = 'VERIFICANDO' then 1 else 0 END) as VERIFICANDO,"
                        + "sum(case when d.status = 'CANCELADO' then 1 else 0 END) as CANCELADO )"
                        + " from Desenho d where d.dataini between ?1 and SYSDATE() group by d.dataini order by d.dataini")
        List<BodyDesGraficoDTO> bodyGrafico(Date dIni);

        @Query("Select NEW br.com.fatec.drawingController.usuario.BodyDesGraficoDTO(DATE_FORMAT(d.dataini,'%Y-%c-%e') as DATA,"
                        + "sum(case when d.status = 'EMITIDO' then 1 else 0 END) as EMITIDO,"
                        + "sum(case when d.status = 'VERIFICANDO' then 1 else 0 END) as VERIFICANDO,"
                        + "sum(case when d.status = 'CANCELADO' then 1 else 0 END) as CANCELADO )"
                        + " from Desenho d where d.dataini between ?1 and SYSDATE() and d.maquete = ?2 group by d.dataini order by d.dataini")
        List<BodyDesGraficoDTO> bodyGraficoSelectProjec(Date dIni, Maquete maquete);

        @Query("Select NEW br.com.fatec.drawingController.usuario.BodyDesGraficoDTO(DATE_FORMAT(d.dataini,'%Y-%c-%e') as DATA,"
                        + "sum(case when d.status = 'EMITIDO' then 1 else 0 END) as EMITIDO,"
                        + "sum(case when d.status = 'VERIFICANDO' then 1 else 0 END) as VERIFICANDO,"
                        + "sum(case when d.status = 'CANCELADO' then 1 else 0 END) as CANCELADO )"
                        + " from Desenho d where d.dataini between ?1 and SYSDATE() group by d.dataini order by d.dataini")
        List<BodyDesGraficoDTO> bodyGraficoSelectDef(Date dIni);

        /********* BUSCAS DINAMICAS ************/
        @Query("Select u from Desenho u where u.maquete = ?1 and u.tag = ?2")
        public List<Desenho> buscaDeseTag(Maquete maquete, Linha busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.desContratado = ?2")
        public List<Desenho> buscaDeseCont(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.desSubtitulo = ?2")
        public List<Desenho> buscaDeseSub(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.status = ?2")
        public List<Desenho> buscaDeseStatus(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.revisao = ?2")
        public List<Desenho> buscaDeseRev(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.comentarios = ?2")
        public List<Desenho> buscaDeseComen(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.desIdCad = ?2")
        public List<Desenho> buscaDeseUs(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.nomeVerificador = ?2")
        public List<Desenho> buscaDeseVerif(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.pipeService = ?2")
        public List<Desenho> buscaDesePipSer(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.pipeSpec = ?2")
        public List<Desenho> buscaDesePipeSpec(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.pID = ?2")
        public List<Desenho> buscaDesePid(Maquete maquete, String busca);

        @Query("Select u from Desenho u where u.maquete = ?1 and u.tag = ?2")
        public List<Desenho> buscaDeseNumf(Maquete maquete, Long busca);

}