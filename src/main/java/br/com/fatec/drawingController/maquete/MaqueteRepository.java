package br.com.fatec.drawingController.maquete;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaqueteRepository extends JpaRepository<Maquete, Long> {

    public Maquete findByProjetoNumero(Long projetoNumero);

    public Maquete findByProjetoNome(String projetoNome);

}