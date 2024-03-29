package br.com.fatec.drawingController.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fatec.drawingController.desenho.BodyCountStatus;
import br.com.fatec.drawingController.desenho.BodyDataFinal;
import br.com.fatec.drawingController.desenho.BodyDesenho;
import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.desenho.DesenhoRepository;
import br.com.fatec.drawingController.desenho.DesenhoService;
import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.linha.LinhaService;
import br.com.fatec.drawingController.maquete.MaqueteService;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;
import br.com.fatec.drawingController.usuario.UsuarioRepository;

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

    @Autowired
    LinhaService linhaService;

    /* ************ Plant3D **************/
    @RequestMapping(value = "/savedesenho", method = RequestMethod.POST)
    public ResponseEntity<Desenho> cadastrarDesenho(@Valid @RequestBody BodyDesenho bDesenho,
            UriComponentsBuilder uriComponentsBuilder) {

        Desenho desenho = desenhoService.registrarDesenho(bDesenho.getIdCad(), bDesenho.getTag(),
                bDesenho.getDesContratado(), bDesenho.getDesSubtitulo(), bDesenho.getStatus(), bDesenho.getRevisao(),
                bDesenho.getDataini(), bDesenho.getDatafim(), bDesenho.getComentarios(), bDesenho.getNomeVerificador(),
                bDesenho.getPipeService(), bDesenho.getPipeSpec(), bDesenho.getPID(), bDesenho.getNumFolhas(),
                bDesenho.getMaqueteId());

        HttpHeaders responHeaders = new HttpHeaders();
        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + desenho.getIdDesenho()).build().toUri());
        return new ResponseEntity<>(desenho, responHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/datafinal", method = RequestMethod.POST)
    public ResponseEntity<Desenho> emissaoFinal(@Valid @RequestBody BodyDataFinal bDataFinal,
            UriComponentsBuilder uriComponentsBuilder) {
        Desenho desenho = desenhoService.atualizaDesenho(bDataFinal);
        HttpHeaders responHeaders = new HttpHeaders();

        responHeaders.setLocation(uriComponentsBuilder.path("/get/" + desenho.getIdDesenho()).build().toUri());
        return new ResponseEntity<Desenho>(desenho, responHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/feitos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_PROBLEM_XML_VALUE })
    public ResponseEntity<Iterable<Desenho>> desenhosFeitos(@Valid @RequestParam("tag") String tag) {
        List<Desenho> desenhos = desenhoService.mesmaTag(tag);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Iterable<Desenho>>(desenhos, responseHeaders, HttpStatus.OK);

    }

    /* ************ WEB **************/

    // CONTAGEM COM DATAS SELECIONADAS e DEFAULT
    @GetMapping(value = "/contagemstatus", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<BodyCountStatus> atualizaContagemStatusSelc(@Valid @RequestParam("bol") boolean bol,
            Long nProj, String dIni, String dFim) throws ParseException {

        BodyCountStatus bodyCountStatus = new BodyCountStatus();
        if (nProj == -1) {
            if (bol == true) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataIni = sdf.parse(dIni);
                Date dataFim = sdf.parse(dFim);
                bodyCountStatus = desenhoService.contagemPorStatusSelec(dataIni, dataFim);
            } else {
                Date dataIni = new Date();
                dataIni.setDate(1);
                bodyCountStatus = desenhoService.contagemPorStatusDEFAULT(dataIni);
            }
        } else {
            if (bol == true) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataIni = sdf.parse(dIni);
                Date dataFim = sdf.parse(dFim);
                bodyCountStatus = desenhoService.contagemPorProjStatusSelec(nProj, dataIni, dataFim);
            } else {
                Date dataIni = new Date();
                dataIni.setDate(1);
                bodyCountStatus = desenhoService.contagemPorProjStatusDEFAULT(nProj, dataIni);
            }

        }
        return new ResponseEntity<BodyCountStatus>(bodyCountStatus, HttpStatus.OK);

    }

    // LISTA POR STATUS E DATAS no campo "VER DETALHES"
    @GetMapping(value = "/desenhosdatastatus", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Desenho>> getDesenhosStatData(@Valid @RequestParam("bol") boolean bol, Long nProj,
            String status, String dIni, String dFim) throws ParseException {
        List<Desenho> desenhos = new ArrayList<Desenho>();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (bol == true) {

            desenhos = desenhoService.desenhosPormaqueteVerDetCalend(nProj, status, dIni, dFim);
        } else {

            desenhos = desenhoService.desenhosPormaqueteVerDetDEFAULT(nProj, status, dIni);
        }
        return new ResponseEntity<List<Desenho>>(desenhos, responseHeaders, HttpStatus.OK);
    }

    // LISTA TODOS DESENHO NO PROJETO
    @RequestMapping(value = "/todosdesenhos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Desenho>> getTodosDesenhos(@Valid @RequestParam("id") Long id) {
        List<Desenho> desenhos = desenhoService.desenhosPorMaquete(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<Desenho>>(desenhos, responseHeaders, HttpStatus.OK);

    }

    // ALIMENTA GRAFICO DE PROGRESSÃO
    @RequestMapping(value = "/graficousuario", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<BodyDesGraficoDTO>> getGrafico() {
        List<BodyDesGraficoDTO> bodyDesGraficoDTO = new ArrayList<BodyDesGraficoDTO>();
        bodyDesGraficoDTO = desenhoService.desGrafico();
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<BodyDesGraficoDTO>>(bodyDesGraficoDTO, responseHeaders, HttpStatus.OK);

    }

    // ALIMENTA GRAFICO DE PROGRESSÃO POR SELECAO
    @RequestMapping(value = "/graficoselect", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<BodyDesGraficoDTO>> getGraficoSelect(@Valid @RequestParam("dIni") int data, Long proj) {
        List<BodyDesGraficoDTO> bodyDesGraficoDTO = new ArrayList<BodyDesGraficoDTO>();
        bodyDesGraficoDTO = desenhoService.desGraficoSelect(proj, data);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<BodyDesGraficoDTO>>(bodyDesGraficoDTO, responseHeaders, HttpStatus.OK);

    }

    @GetMapping(value = "/buscardesenhos")
    public ResponseEntity<List<Desenho>> buscarDesenhos(@Valid @RequestParam("nProj") Long nProj,
            @Valid @RequestParam("nCamp") String nCamp, @Valid @RequestParam("nBusca") String nBusca,
            UriComponentsBuilder uriComponentsBuilder) {
        List<Desenho> desenhos = new ArrayList<Desenho>();
        HttpHeaders responHeaders = new HttpHeaders();

        desenhos = desenhoService.buscaDeseParams(nProj, nCamp, nBusca);
        return new ResponseEntity<List<Desenho>>(desenhos, responHeaders, HttpStatus.OK);
    }

}