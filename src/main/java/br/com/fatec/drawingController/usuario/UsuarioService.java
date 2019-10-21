package br.com.fatec.drawingController.usuario;

import org.springframework.stereotype.Service;
import java.util.List;

import br.com.fatec.drawingController.generic.IGenericServiceCrud;
import br.com.fatec.drawingController.maquete.Maquete;

@Service
public interface UsuarioService extends IGenericServiceCrud<Usuario, Long> {

    public Usuario saveUsuario(Usuario usuario);

    public Usuario buscaUsuario(String nome);

}