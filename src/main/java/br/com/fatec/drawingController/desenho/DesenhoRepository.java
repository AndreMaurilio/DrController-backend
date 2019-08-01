package br.com.fatec.drawingController.desenho;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.usuario.Usuario;
import org.springframework.transaction.annotation.Transactional;

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

}