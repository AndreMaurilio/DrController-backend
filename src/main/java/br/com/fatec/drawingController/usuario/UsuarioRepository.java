package br.com.fatec.drawingController.usuario;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNome(String nome);
    // public Usuario findById(Long id);

    public Usuario findByIdCad(String idCad);

    public Usuario findByDisciplina(String disciplina);
    // public List<Usuario>
    // findByNomeContainsIgnoreCaseOrAutorizacoesNomeContainsIgnoreCase(String nome,
    // String nomeAutorizacao);

    @Query("Select u from Usuario u where u.idCad like %?1%")
    public Usuario permiteRegistro(String idCad);

    @Query("select u from Usuario u where u.nome like %?1%")
    public HashSet<Usuario> buscaUsuarios(String nome);

}
