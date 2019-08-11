package br.com.fatec.drawingController.security;

import java.util.List;

import br.com.fatec.drawingController.usuario.Autorizacao;

public interface AutorizacaoService {

    public Autorizacao salvar(Autorizacao autorizacao);

    public void excluir(Long idAutorizacao);

    public List<Autorizacao> todos();

    public List<Autorizacao> buscar(String nomeAutorizacao);

    public Autorizacao buscarPorId(Long idAutorizacao);

}