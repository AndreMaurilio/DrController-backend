package br.com.fatec.drawingController.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.drawingController.usuario.Autorizacao;
import br.com.fatec.drawingController.security.AutorizacaoRepository;

@Service("autorizacaoService")
@Transactional
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class AutorizacaoServiceImp implements AutorizacaoService {

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    public void setAutorizacaoRepo(AutorizacaoRepository autorizacaoRepo) {
        this.autorizacaoRepo = autorizacaoRepo;
    }

    @Override
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Autorizacao salvar(Autorizacao autorizacao) {
        return autorizacaoRepo.save(autorizacao);
    }

    @Override
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void excluir(Long idAutorizacao) {
        autorizacaoRepo.deleteById(idAutorizacao);
    }

    @Override
    // @PreAuthorize("isAuthenticated()")
    public List<Autorizacao> todos() {
        List<Autorizacao> retorno = new ArrayList<Autorizacao>();
        for (Autorizacao autorizacao : autorizacaoRepo.findAll()) {
            retorno.add(autorizacao);
        }
        return retorno;
    }

    @Override
    // @PreAuthorize("isAuthenticated()")
    public List<Autorizacao> buscar(String nome) {
        if (nome == null || nome.isEmpty()) {
            return todos();
        }
        return autorizacaoRepo.findByNomeAutorizacaoContainsIgnoreCase(nome);
    }

    @Override
    // @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarPorId(Long idAutorizacao) {
        Optional<Autorizacao> autorizacao = autorizacaoRepo.findById(idAutorizacao);
        if (autorizacao.isPresent()) {
            return autorizacao.get();
        }
        return null;
    }

}
