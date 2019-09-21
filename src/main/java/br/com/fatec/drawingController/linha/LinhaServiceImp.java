package br.com.fatec.drawingController.linha;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteRepository;
import br.com.fatec.drawingController.maquete.MaqueteService;

@Service
public class LinhaServiceImp implements LinhaService {

    @Autowired
    LinhaRepository linhaRepository;

    @Autowired
    MaqueteRepository maqueteRepository;

    @Autowired
    EntityManager em;

    @Autowired
    MaqueteService maqueteService;

    @Override
    public boolean save(Linha linha) {
        linhaRepository.save(linha);
        // return usuarioRepository.existsById((int)usuario.getId());
        return linhaRepository.existsById((linha.getLiTag()));

    }

    @Override
    public Linha saveEnt(Linha linha) {
        return linhaRepository.save(linha);
    }

    @Override
    public Linha remove(Linha linha) {

        linhaRepository.delete(linha);
        return linha;
    }

    @Override
    public boolean update(Linha linha, Linha linhaUpdate) {

        Optional<Linha> c = linhaRepository.findById(linha.getLiTag());
        if (c.isPresent()) {
            linhaUpdate.setLiTag((c.get().getLiTag()));
            linhaRepository.save(linha);

        }
        return linhaRepository.existsById(linhaUpdate.getLiTag());
    }

    @Override
    public Optional<Linha> findById(String tag) {

        return linhaRepository.findById(tag);

    }

    @Override
    public java.util.List<Linha> findAll() {
        // TODO Auto-generated method stub
        return linhaRepository.findAll();
    }

    @Override
    public Linha buscaLinha(String tag) {
        // TODO Auto-generated method stub
        return linhaRepository.findByLiTag(tag);
    }

    @Transactional
    @Override
    public Linha saveLinha(BodyLinha bod) {
        // TODO Auto-generated method stub
        Optional<Maquete> maq = maqueteService.findById(bod.getBlMaquete());
        Maquete maquete = maq.get();
        Linha linha1 = new Linha();
        linha1.setLiTag(bod.getBlTag());
        linha1.setLiMaterial(bod.getBlMaterial());
        linha1.setLiPendencias(bod.getBlPendencias());
        linha1.setLiFluido(bod.getBlFluido());
        linha1.setLiArea(bod.getBlArea());
        linha1.setLiSite(bod.getBlSite());
        linha1.setLiBimTag(bod.getBlBimTag());
        linha1.setMaquete(maquete);

        return linhaRepository.save(linha1);
    }

    @Override
    public List<Linha> saveLoteLinha(List<BodyLinha> linhas) {

        List<Linha> linhaLote = new ArrayList<Linha>();
        // linhas.forEach(lis->saveLinha(lis));
        for (BodyLinha lis : linhas) {
            linhaLote.add(saveLinha(lis));
        }
        // TODO Auto-generated method stub
        return linhaLote;
    }

    @Override
    public List<Linha> servBuscaLinhas(Long maquete, String nCamp, String busca) {
        Optional<Maquete> maq = maqueteService.findById(maquete);
        Maquete mqt = maq.get();
        List<Linha> linhas = new ArrayList<Linha>();

        switch (nCamp) {
        case "liTag":
            linhas = linhaRepository.repBuscaLinhasTag(mqt, busca);
            break;
        case "liMaterial":
            linhas = linhaRepository.repBuscaLinhasMaterial(mqt, busca);
            break;
        case "liPendencias":
            linhas = linhaRepository.repBuscaLinhasPende(mqt, busca);
            break;
        case "liFluido":
            linhas = linhaRepository.repBuscaLinhasFluido(mqt, busca);
            break;
        case "liArea":
            linhas = linhaRepository.repBuscaLinhasArea(mqt, busca);
            break;
        case "liSite":
            linhas = linhaRepository.repBuscaLinhasSite(mqt, busca);
            break;
        case "liBimTag":
            linhas = linhaRepository.repBuscaLinhasBimTag(mqt, busca);
            break;
        }

        return linhas;

    }

}