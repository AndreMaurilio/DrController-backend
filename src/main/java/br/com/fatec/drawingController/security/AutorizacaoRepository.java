package br.com.fatec.drawingController.security;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.com.fatec.drawingController.usuario.Autorizacao;

public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Long> {

    public Autorizacao findByNomeAutorizacao(String nomeAutorizacao);

    public List<Autorizacao> findByNomeAutorizacaoContainsIgnoreCase(String nomeAutorizacao);

}
