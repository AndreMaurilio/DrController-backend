package br.com.fatec.drawingController.view;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

        usuario = usuarioService.saveUsuario(usuario);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + usuario.getId()).build().toUri());
        return new ResponseEntity<>(usuario, responHeaders, HttpStatus.CREATED);

    }

}