package br.com.fatec.drawingController.maquete;

import java.util.List;

import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.generic.IGenericServiceCrud;


public interface MaqueteService extends IGenericServiceCrud<Maquete,Long>{

   // public boolean autentificaDesenho(Desenho desenho);
    
    public List<BodyMaqueBox> projeCombo(); 




}