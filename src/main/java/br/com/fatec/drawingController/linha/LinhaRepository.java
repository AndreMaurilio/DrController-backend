package br.com.fatec.drawingController.linha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, String> {

    public Linha findByLiTag(String liTag);

}