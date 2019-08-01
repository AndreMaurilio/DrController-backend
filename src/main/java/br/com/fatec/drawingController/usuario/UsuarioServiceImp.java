package br.com.fatec.drawingController.usuario;

import java.util.List;
import java.util.Optional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

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
        return usuarioRepository.save(usuario);

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

}