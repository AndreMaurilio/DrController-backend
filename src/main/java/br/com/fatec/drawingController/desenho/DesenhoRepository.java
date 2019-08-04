package br.com.fatec.drawingController.desenho;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.stereotype.Repository;

import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DesenhoRepository extends JpaRepository<Desenho, Long> {

    public Desenho findByDataini(Date data);

    public Desenho findByUsuario(Usuario usuario);

    public Desenho findByTag(String tag);

    /********** PLANT3D *******************/
    @Query("Select u from Desenho u where u.revisao = ?1 and u.tag = ?2")
    public Desenho findByTagRev(String rev, String Tag);

    @Query("Update Desenho u set u.status = ?1 where u.idDesenho = ?2 ")
    public boolean editStatus(String status, long idDesenho);

    @Transactional
    @Modifying
    @Query("Update Desenho u set u.datafim = ?1, u.status = ?2, u.comentarios = ?3 where u.id = ?4")
    public void editEmissaoFinal(Date datafim, String status, String comentarioFinal, long idDesenho);

    @Query("Select u from Desenho u where u.tag = ?1")
    public List<Desenho> desDeMesmaTag(String tag);

    /****************** WEB *********/

    @Query("Select count(u) as total from Desenho u where u.status like 'EMITIDO'")
    public Long contagemEmitidos();

    @Query("Select count(u) as total from Desenho u where u.status like 'VERIFICANDO'")
    public Long contagemVerificado();

    @Query("Select count(u) as total from Desenho u where u.status like 'CANCELADO'")
    public Long contagemCancelado();

    @Query("Select u from Desenho u where u.maquete = ?1")
    public List<Desenho> buscaPorMaquete(Maquete numMaquete);

    /*
     * @Query("Select NEW br.com.fatec.drawingController.usuario.BodyDesGraficoDTO( count(u) as"
     * +
     * " documentos , DATE_FORMAT(d.dataini,'%e/%c/%Y'), u.nome, u.id ) From Desenho d JOIN d.usuario u GROUP "
     * + "BY d.dataini,u.nome ")
     */
    @Query("Select NEW br.com.fatec.drawingController.usuario.BodyDesGraficoDTO(DATE_FORMAT(d.dataini,'%e/%c/%Y') as DATA,"
            + "sum(case when d.status = 'EMITIDO' then 1 else 0 END) as EMITIDO,"
            + "sum(case when d.status = 'VERIFICANDO' then 1 else 0 END) as VERIFICANDO,"
            + "sum(case when d.status = 'CANCELADO' then 1 else 0 END) as CANCELADO)"
            + "from Desenho d  group by d.dataini")
    List<BodyDesGraficoDTO> bodyGrafico();

}