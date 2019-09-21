package br.com.fatec.drawingController.linha;

import java.util.List;
import br.com.fatec.drawingController.generic.IGenericServiceCrudString;
import br.com.fatec.drawingController.linha.LinhaRepository;

public interface LinhaService extends IGenericServiceCrudString<Linha, String> {

    public Linha buscaLinha(String tag);

    public Linha saveLinha(BodyLinha linha);

    public List<Linha> saveLoteLinha(List<BodyLinha> linhas);

    public List<Linha> servBuscaLinhas(Long maquete, String nCamp, String busca);

}