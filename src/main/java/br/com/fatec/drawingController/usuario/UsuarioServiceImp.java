package br.com.fatec.drawingController.usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.com.fatec.drawingController.security.AutorizacaoRepository;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public AutorizacaoRepository autorizacaoRepository;

    @Autowired
    public AutAutorizacaoRepository autAutorizacaorepository;

    public void setAutorizaRepository(AutorizacaoRepository autorizacaoRepository) {

        this.autorizacaoRepository = autorizacaoRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void setAutAutorizacaorepository(AutAutorizacaoRepository autAutorizacaorepository) {
        this.autAutorizacaorepository = autAutorizacaorepository;
    }

    @Override
    public boolean save(Usuario usuario) {
        usuarioRepository.save(usuario);
        // return usuarioRepository.existsById((int)usuario.getId());
        return usuarioRepository.existsById((usuario.getId()));

    }

    @Override
    public Usuario saveEnt(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario remove(Usuario usuario) {

        usuarioRepository.delete(usuario);
        return usuario;
    }

    @Override
    public boolean update(Usuario usuario, Usuario usuarioUpdate) {

        Optional<Usuario> c = usuarioRepository.findById(usuario.getId());
        if (c.isPresent()) {
            usuarioUpdate.setId(c.get().getId());
            usuarioUpdate.setSenha(md5(usuario.getSenha()));

            usuarioRepository.save(usuarioUpdate);

        }
        return usuarioRepository.existsById(usuarioUpdate.getId());
    }

    @Override
    public Optional<Usuario> findById(Long id) {

        return usuarioRepository.findById(id);

    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {

        usuario.setSenha(md5(usuario.getSenha()));
        AutAutorizacao aut = new AutAutorizacao();

        Usuario user = usuarioRepository.save(usuario);
        aut.setUsuario(user.getId());
        if (user.getPerfil().equals("ADMINISTRADOR")) {
            aut.setAutorizacao(3L);
            autAutorizacaorepository.save(aut);
        } else {
            aut.setAutorizacao(1L);
            autAutorizacaorepository.save(aut);
        }

        return user;
    }

    private String md5(String senha) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            hexString.append("{MD5}");
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
            // Unexpected - do nothing
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            // Unexpected - do nothing
        }
        return senha;
    }

    @Override
    public Usuario buscaUsuario(String nome) {

        return usuarioRepository.findByNome(nome);
    }

}