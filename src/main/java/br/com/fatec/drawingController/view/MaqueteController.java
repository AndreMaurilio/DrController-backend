package br.com.fatec.drawingController.view;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<>(maquete, responHeaders, HttpStatus.CREATED);

    }

}