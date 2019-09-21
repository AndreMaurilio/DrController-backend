package br.com.fatec.drawingController.linha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import br.com.fatec.drawingController.maquete.Maquete;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, String> {

    public Linha findByLiTag(String liTag);

    // **BUSCA LINHAS PELOS PARAMETROS** */
    // por Tag
    @Query("Select l from Linha l where l.maquete = ?1 and l.liTag = ?2")
    public List<Linha> repBuscaLinhasTag(Maquete maquete, String busca);

    // por material
    @Query("Select l from Linha l where l.maquete = ?1 and l.liMaterial = ?2")
    public List<Linha> repBuscaLinhasMaterial(Maquete maquete, String busca);

    // por pendencias
    @Query("Select l from Linha l where l.maquete = ?1 and l.liPendencias = ?2")
    public List<Linha> repBuscaLinhasPende(Maquete maquete, String busca);

    // por Fluido
    @Query("Select l from Linha l where l.maquete = ?1 and l.liFluido = ?2")
    public List<Linha> repBuscaLinhasFluido(Maquete maquete, String busca);

    // por Area
    @Query("Select l from Linha l where l.maquete = ?1 and l.liArea = ?2")
    public List<Linha> repBuscaLinhasArea(Maquete maquete, String busca);

    // por Site
    @Query("Select l from Linha l where l.maquete = ?1 and l.liSite = ?2")
    public List<Linha> repBuscaLinhasSite(Maquete maquete, String busca);

    // por BimTag
    @Query("Select l from Linha l where l.maquete = ?1 and l.liBimTag = ?2")
    public List<Linha> repBuscaLinhasBimTag(Maquete maquete, String busca);

}