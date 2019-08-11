package br.com.fatec.drawingController.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioRepository;

@Service("segurancaService")
public class SegurancaService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return usuario;
    }

}
