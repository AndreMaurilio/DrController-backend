package br.com.fatec.drawingController.view;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fatec.drawingController.maquete.BodyMaqueBox;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteService;

@CrossOrigin
@RestController
@RequestMapping(value = "/maquete")
public class MaqueteController {

    @Autowired
    MaqueteService maqueteService;

    @RequestMapping(value = "/savemaquete", method = RequestMethod.POST)
    public ResponseEntity<Maquete> cadastrarMaquete(@Valid @RequestBody Maquete maquete,
            UriComponentsBuilder uriComponentsBuilder) {
        maquete = maqueteService.saveEnt(maquete);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + maquete.getProjetoNumero()).build().toUri());
        return new ResponseEntity<Maquete>(maquete, responHeaders, HttpStatus.CREATED);

    }

    @GetMapping(value = "/projetos", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<BodyMaqueBox>> comboLoad() {
        List<BodyMaqueBox> maquetes = maqueteService.projeCombo();
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<List<BodyMaqueBox>>(maquetes, responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatemaquete", method = RequestMethod.POST)
    public ResponseEntity<String> updateUser(@Valid @RequestBody Maquete maquete,
            UriComponentsBuilder uriComponentsBuilder) {
        Optional<Maquete> us = maqueteService.findById(maquete.getProjetoNumero());
        Maquete user = us.get();
        String atualizacao;
        if (maqueteService.update(user, maquete) == true) {
            atualizacao = " Atualizado com sucesso";
        } else {
            atualizacao = "Não foi possivel concluir";
        }

        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + maquete.getProjetoNumero()).build().toUri());
        return new ResponseEntity<>(atualizacao, responHeaders, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/deltmaque")
    public ResponseEntity<Maquete> deletarUsuario(@RequestBody Maquete maquete,
            UriComponentsBuilder uriComponentsBuilder) {
        maquete = maqueteService.remove(maquete);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + maquete.getProjetoNumero()).build().toUri());
        return new ResponseEntity<>(maquete, responHeaders, HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "/buscarprojeto", method = RequestMethod.GET)
    public ResponseEntity<Maquete> buscaUsuer(@Valid @RequestParam("buscar") Long buscador,
            UriComponentsBuilder uriComponentsBuilder) {
        Optional<Maquete> maquete = maqueteService.findById(buscador);
        Maquete maq = maquete.get();
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + maq.getProjetoNumero()).build().toUri());
        return new ResponseEntity<>(maq, responHeaders, HttpStatus.CREATED);

    }

}
