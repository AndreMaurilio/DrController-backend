package br.com.fatec.drawingController.view;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fatec.drawingController.security.AutorizacaoService;
import br.com.fatec.drawingController.usuario.Autorizacao;

@RestController
@RequestMapping(value = "/autorizacao")
@CrossOrigin
public class AutorizacaoController {

    @Autowired
    private AutorizacaoService autorizacaoService;

    public void setAutorizacaoService(AutorizacaoService autorizacaoService) {
        this.autorizacaoService = autorizacaoService;
    }

    @RequestMapping(value = "/get/{nome}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Autorizacao>> pesquisar(@PathVariable("nome") String nome) {
        return new ResponseEntity<Collection<Autorizacao>>(autorizacaoService.buscar(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Autorizacao> pesquisarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<Autorizacao>(autorizacaoService.buscarPorId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Collection<Autorizacao>> getAll() {
        return new ResponseEntity<Collection<Autorizacao>>(autorizacaoService.todos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Autorizacao> salvar(@RequestBody Autorizacao autorizacao,
            UriComponentsBuilder uriComponentsBuilder) {
        autorizacao = autorizacaoService.salvar(autorizacao);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + autorizacao.getId()).build().toUri());
        return new ResponseEntity<Autorizacao>(autorizacao, responseHeaders, HttpStatus.CREATED);
    }

}
