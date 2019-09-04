package br.com.fatec.drawingController.maquete;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaqueteRepository extends JpaRepository<Maquete, Long> {

    public Maquete findByProjetoNumero(Long projetoNumero);

    public Maquete findByProjetoNome(String projetoNome);

    @Query("Select new br.com.fatec.drawingController.maquete.BodyMaqueBox(u.projetoNumero as numProj, u.projetoNome as nomProj)"
            + " from Maquete u ")
    public List<BodyMaqueBox> comboProje();

}