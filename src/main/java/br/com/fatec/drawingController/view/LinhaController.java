package br.com.fatec.drawingController.view;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import br.com.fatec.drawingController.linha.BodyLinha;
import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.linha.LinhaService;
import br.com.fatec.drawingController.linha.ListBodyLinha;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteRepository;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
@RequestMapping(value = "/linha")
public class LinhaController {

    @Autowired
    LinhaService linhaService;

    @Autowired
    MaqueteRepository maqueteRepository;

    @RequestMapping(value = "/savelinha", method = RequestMethod.POST)
    public ResponseEntity<Linha> cadastrarLinha(@Valid @RequestBody BodyLinha linha,
            UriComponentsBuilder uriComponentsBuilder) {
        Linha linha1 = linhaService.saveLinha(linha);
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + linha1.getLiTag()).build().toUri());
        return new ResponseEntity<Linha>(linha1, responHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/pippings", method = RequestMethod.POST)
    public ResponseEntity<String> cadastrarLinhas(@Valid @RequestBody ListBodyLinha linhas,
            UriComponentsBuilder uriComponentsBuilder) {

        List<Linha> linhaLote = new ArrayList<Linha>();
        // linhas.forEach(lis->saveLinha(lis));
        for (BodyLinha lis : linhas.getLinhas()) {
            linhaLote.add(linhaService.saveLinha(lis));
        }
        // HttpHeaders responHeaders = new HttpHeaders();
        // responHeaders.setLocation(uriComponentsBuilder.path("/get/" +
        // linha1.getLiTag()).build().toUri());
        return new ResponseEntity<String>(linhaLote.toString(), HttpStatus.CREATED);

    }

    @GetMapping(value = "/buscarlinhas")
    public ResponseEntity<List<Linha>> buscarLinhas(@Valid @RequestParam("nProj") Long nProj,
            @Valid @RequestParam("nCamp") String nCamp, @Valid @RequestParam("nBusca") String nBusca,
            UriComponentsBuilder uriComponentsBuilder) {
        List<Linha> linhas = new ArrayList<Linha>();
        HttpHeaders responHeaders = new HttpHeaders();

        linhas = linhaService.servBuscaLinhas(nProj, nCamp, nBusca);
        return new ResponseEntity<List<Linha>>(linhas, responHeaders, HttpStatus.OK);
    }

}