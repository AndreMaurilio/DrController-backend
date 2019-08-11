package br.com.fatec.drawingController.view;

import java.util.Optional;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import br.com.fatec.drawingController.desenho.BodyCountStatus;
import br.com.fatec.drawingController.desenho.BodyDataFinal;
import br.com.fatec.drawingController.desenho.BodyDesenho;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.desenho.DesenhoRepository;
import br.com.fatec.drawingController.desenho.DesenhoService;
import br.com.fatec.drawingController.maquete.MaqueteService;
import br.com.fatec.drawingController.usuario.UsuarioRepository;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
@RequestMapping(value = "/desenho")
public class DesenhoController {

    @Autowired
    DesenhoService desenhoService;

    @Autowired
    DesenhoRepository desenhoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MaqueteService maqueteService;

    /* ************ Plant3D **************/
    @RequestMapping(value = "/savedesenho", method = RequestMethod.POST)
    public ResponseEntity<Desenho> cadastrarDesenho(@Valid @RequestBody BodyDesenho bDesenho,
            UriComponentsBuilder uriComponentsBuilder) {

        Desenho desenho = desenhoService.registrarDesenho(bDesenho.getIdCad(), bDesenho.getTag(),
                bDesenho.getDesContratado(), bDesenho.getDesSubtitulo(), bDesenho.getStatus(), bDesenho.getRevisao(),
                bDesenho.getDataini(), bDesenho.getDatafim(), bDesenho.getComentarios(), bDesenho.getNomeVerificador(),
                bDesenho.getMaqueteId());

        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + desenho.getIdDesenho()).build().toUri());
        return new ResponseEntity<>(desenho, responHeaders, HttpStatus.CREATED);

    }

    /* ************ Plant3D **************/
    @RequestMapping(value = "/datafinal", method = RequestMethod.POST)
    public ResponseEntity<Desenho> emissaoFinal(@Valid @RequestBody BodyDataFinal bDataFinal,
            UriComponentsBuilder uriComponentsBuilder) {
        Desenho desenho = desenhoRepository.findByTagRev(bDataFinal.getReviFinal(), bDataFinal.getTagFinal());
        desenhoRepository.editEmissaoFinal(bDataFinal.getDataFinal(), bDataFinal.getStatusFinal(),
                bDataFinal.getComentFinal(), desenho.getIdDesenho());
        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + desenho.getIdDesenho()).build().toUri());
        return new ResponseEntity<Desenho>(desenho, responHeaders, HttpStatus.CREATED);
    }

    /* ************ Plant3D **************/
    @RequestMapping(value = "/feitos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_PROBLEM_XML_VALUE })
    public ResponseEntity<Iterable<Desenho>> desenhosFeitos(@Valid @RequestParam("tag") String tag) {
        List<Desenho> desenhos = desenhoService.mesmaTag(tag);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Iterable<Desenho>>(desenhos, responseHeaders, HttpStatus.OK);

    }

    /* ************ WEB **************/
    @GetMapping(value = "/contagemstatus", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<BodyCountStatus> atualizaContagemStatus() {
        BodyCountStatus bodyCountStatus = desenhoService.contagemPorStatus();
        return new ResponseEntity<BodyCountStatus>(bodyCountStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/todosdesenhos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Desenho>> getTodosDesenhos(@Valid @RequestParam("id") Long id) {
        List<Desenho> desenhos = desenhoService.desenhosPorMaquete(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<Desenho>>(desenhos, responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/graficousuario", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<BodyDesGraficoDTO>> getGrafico() {
        List<BodyDesGraficoDTO> bodyDesGraficoDTO = desenhoRepository.bodyGrafico();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<BodyDesGraficoDTO>>(bodyDesGraficoDTO, responseHeaders, HttpStatus.OK);

    }

}