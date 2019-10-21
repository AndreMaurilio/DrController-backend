package br.com.fatec.drawingController.view;

import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController extends HttpServlet {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/home")
    public String wellCome() {

        return "Testando ";
    }

    @RequestMapping(value = "/saveusuario", method = RequestMethod.POST)
    public ResponseEntity<Usuario> saveUsuer(@Valid @RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder) {
        /*
         * Usuario usuario = new Usuario(); usuario.setNome(nome);
         * usuario.setIdCad(idCad); usuario.setDisciplina(disciplina);
         * usuario.setEmail(email); usuario.setSenha(senha); usuario.setPerfil(perfil);
         */
        usuario = usuarioService.saveUsuario(usuario);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + usuario.getId()).build().toUri());
        return new ResponseEntity<>(usuario, responHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/buscarusuario", method = RequestMethod.GET)
    public ResponseEntity<Usuario> buscaUsuer(@Valid @RequestParam("buscar") String buscador,
            UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.buscaUsuario(buscador);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + usuario.getId()).build().toUri());
        return new ResponseEntity<>(usuario, responHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public ResponseEntity<String> updateUser(@Valid @RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> us = usuarioService.findById(usuario.getId());
        Usuario user = us.get();
        String atualizacao;
        if (usuarioService.update(user, usuario) == true) {
            atualizacao = " Atualizado com sucesso";
        } else {
            atualizacao = "Não foi possivel concluir";
        }

        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + usuario.getId()).build().toUri());
        return new ResponseEntity<>(atualizacao, responHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/deltuser")
    public ResponseEntity<Usuario> deletarUsuario(@RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder) {
        usuario = usuarioService.remove(usuario);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + usuario.getId()).build().toUri());
        return new ResponseEntity<>(usuario, responHeaders, HttpStatus.ACCEPTED);

    }

}
