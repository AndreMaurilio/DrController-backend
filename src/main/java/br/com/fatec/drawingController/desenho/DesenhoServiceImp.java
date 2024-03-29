package br.com.fatec.drawingController.desenho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.linha.LinhaService;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteRepository;
import br.com.fatec.drawingController.maquete.MaqueteService;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;
import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioRepository;

@Service
public class DesenhoServiceImp implements DesenhoService {

    @Autowired
    private DesenhoRepository desenhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MaqueteRepository maqueteRepository;

    @Autowired
    private MaqueteService maqueteService;

    @Autowired
    private LinhaService linhaService;

    public void setDesenhoRepository(DesenhoRepository desenhoRepository) {
        this.desenhoRepository = desenhoRepository;
    }

    public void setMaqueteRepository(MaqueteRepository maqueteRepository) {
        this.maqueteRepository = maqueteRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean save(Desenho desenho) {
        desenhoRepository.save(desenho);
        // return usuarioRepository.existsById((int)usuario.getId());
        return desenhoRepository.existsById((desenho.getIdDesenho()));

    }

    @Override
    public Desenho saveEnt(Desenho desenho) {
        return desenhoRepository.save(desenho);
    }

    @Override
    public Desenho remove(Desenho desenho) {

        desenhoRepository.delete(desenho);
        return desenho;
    }

    @Override
    public boolean update(Desenho desenho, Desenho desenhoUpdate) {

        Optional<Desenho> c = desenhoRepository.findById(desenho.getIdDesenho());
        if (c.isPresent()) {
            desenhoUpdate.setIdDesenho(c.get().getIdDesenho());
            desenhoRepository.save(desenhoUpdate);

        }
        return desenhoRepository.existsById(desenhoUpdate.getIdDesenho());
    }

    @Override
    public Optional<Desenho> findById(Long id) {

        Optional<Desenho> desenho = desenhoRepository.findById(id);

        return desenho.isPresent() ? desenho : null;
    }

    @Override
    public List<Desenho> findAll() {
        return desenhoRepository.findAll();
    }

    @Transactional
    @Override
    public Desenho registrarDesenho(String idCad, String tag, String desContratado, String desSubtitulo, String status,
            String revisao, Date dataIni, Date dataFim, String comentarios, String nomeVerificador, String pipeServ,
            String pipeSpec, String pID, int numFolha, Long idMaq) {
        try {
            if(tag.contains("--")){
                StringBuilder s = new StringBuilder(tag);
                s.setCharAt(tag.indexOf("--"),'"');
                tag = s.toString();
            }

            Linha tags = linhaService.buscaLinha(tag);
            //Desenho d = desenhoRepository.findByTagRev(revisao, tags);
            Desenho d = desenhoRepository.findByTagPidRev(tags,pID,revisao);
            if (d == null) {
                // Optional<Usuario> usuEnt = usuarioService.findById(id);
                Usuario usuario = usuarioRepository.findByIdCad(idCad);
                // Usuario usuario = usuEnt.get();

                // Usuario usuario = usuarioRepository.permiteRegistro(idCad);
                Optional<Maquete> maqEnt = maqueteService.findById(idMaq);
                Maquete maquete = maqEnt.get();
                Desenho desenho = new Desenho();
                desenho.setUsuario(usuario);
                desenho.setMaquete(maquete);
                desenho.setTag(tags);
                desenho.setDesContratado(desContratado);
                desenho.setDesSubtitulo(desSubtitulo);
                desenho.setDesIdCad(idCad);
                desenho.setStatus(status);
                desenho.setRevisao(revisao);
                desenho.setDataini(dataIni);
                desenho.setDatafim(dataFim);
                desenho.setComentarios(comentarios);
                desenho.setNomeVerificador(nomeVerificador);
                desenho.setPipeService(pipeServ);
                desenho.setPipeSpec(pipeSpec);
                desenho.setPID(pID);
                desenho.setNumFolhas(numFolha);

                return desenhoRepository.save(desenho);
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Desenho> mesmaTag(String tag) {
        /*StringBuilder s = new StringBuilder(tag).insert(tag.indexOf('-'), '"');*/

        tag = cleanComas(tag);

        Linha tags = linhaService.buscaLinha(tag);
        List<Desenho> desenhos = desenhoRepository.desDeMesmaTag(tags);
        List<Desenho> des = new ArrayList<>();
        for (Desenho desenho : desenhos) {
            if (desenho.getTag().getLiTag().charAt(1) == '"') {
                desenho.getTag().setLiTag(desenho.getTag().getLiTag().replace('"',' '));
                des.add(desenho);
            } else {
                des.add(desenho);
            }

        }
        return des;
    }

    public String cleanComas(String str){

        if(str.contains("--")){
            StringBuilder s = new StringBuilder(str);
            s.setCharAt(str.indexOf("--"),'"');
            str = s.toString();

            return str;
        }
        return str;
    }

    // ******CONTAGEM SEM SELECAO DE PROJETO CALENDARIO******/
    public BodyCountStatus contagemPorStatusSelec(Date dIni, Date dFim) {

        BodyCountStatus countStatus = new BodyCountStatus();
        countStatus.setEmitido(desenhoRepository.contagemEmitidoSelec(dIni, dFim));
        countStatus.setVerificando(desenhoRepository.contagemVerificadoSelec(dIni, dFim));
        countStatus.setCancelado(desenhoRepository.contagemCanceladoSelec(dIni, dFim));
        return countStatus;

    }

    public BodyCountStatus contagemPorStatusDEFAULT(Date dIni) {

        BodyCountStatus countStatus = new BodyCountStatus();
        // Date ini = new SimpleDateFormat("YYYY-MM-DD").parse(dIni);

        countStatus.setEmitido(desenhoRepository.contagemEmitidoDEFAULT(dIni));
        countStatus.setVerificando(desenhoRepository.contagemVerificadoDEFAULT(dIni));
        countStatus.setCancelado(desenhoRepository.contagemCanceladoDEFAULT(dIni));
        return countStatus;

    }

    // ******CONTAGEM COM SELECAO DE PROJETO E DATA DEFAULT******/

    public BodyCountStatus contagemPorProjStatusSelec(Long maque, Date dIni, Date dFim) {

        BodyCountStatus countStatus = new BodyCountStatus();
        Maquete maquet = maqueteRepository.findByProjetoNumero(maque);
        countStatus.setEmitido(desenhoRepository.contagemProjEmitidoSelec(maquet, dIni, dFim));
        countStatus.setVerificando(desenhoRepository.contagemProjVerificadoSelec(maquet, dIni, dFim));
        countStatus.setCancelado(desenhoRepository.contagemProjCanceladoSelec(maquet, dIni, dFim));
        return countStatus;

    }

    public BodyCountStatus contagemPorProjStatusDEFAULT(Long maque, Date dIni) {

        BodyCountStatus countStatus = new BodyCountStatus();
        // Date ini = new SimpleDateFormat("YYYY-MM-DD").parse(dIni);
        Maquete maquet = maqueteRepository.findByProjetoNumero(maque);
        countStatus.setEmitido(desenhoRepository.contagemProjEmitidoDEFAULT(maquet, dIni));
        countStatus.setVerificando(desenhoRepository.contagemProjVerificadoDEFAULT(maquet, dIni));
        countStatus.setCancelado(desenhoRepository.contagemProjCanceladoDEFAULT(maquet, dIni));
        return countStatus;

    }

    public List<Desenho> desenhosPorStatusCalend(String status, Date dIni, Date dFim) {

        return desenhoRepository.listaPorStatus(status, dIni, dFim);
    }

    public List<Desenho> desenhosPorStatusMesAtual(String status, Date dIni) {

        return desenhoRepository.listaPorStatusDefault(status, dIni);
    }

    public List<Desenho> desenhosPorMaquete(Long numMaquete) {

        return desenhoRepository.buscaPorMaquete(maqueteRepository.findByProjetoNumero(numMaquete));
    }

    // LISTAGEM PELO CAMPO VER DETALHES COM NUMERO DO PROJETO SEM E COM
    // CALENDARIO
    public List<Desenho> desenhosPormaqueteVerDetCalend(Long maque, String status, String dIni, String dFim)
            throws ParseException {
        List<Desenho> desenhos = new ArrayList<Desenho>();
        if (maque == -1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataIni = sdf.parse(dIni);
            Date dataFim = sdf.parse(dFim);
            desenhos = desenhoRepository.listaPorStatus(status, dataIni, dataFim);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataIni = sdf.parse(dIni);
            Date dataFim = sdf.parse(dFim);
            Maquete maquet = maqueteRepository.findByProjetoNumero(maque);
            desenhos = desenhoRepository.listaPorProjStatus(status, maquet, dataIni, dataFim);
        }

        return desenhos;
    }

    public List<Desenho> desenhosPormaqueteVerDetDEFAULT(Long maque, String Status, String dIni) throws ParseException {
        // TODO Auto-generated method stub
        List<Desenho> desenhos = new ArrayList<Desenho>();
        if (maque == -1) {
            Date dataIni = new Date();
            dataIni.setDate(1);
            desenhos = desenhoRepository.listaPorStatusDefault(Status, dataIni);
        } else {
            Date dataIni = new Date();
            dataIni.setDate(1);
            Maquete maquet = maqueteRepository.findByProjetoNumero(maque);
            desenhos = desenhoRepository.listaPorProjeStatusDefault(Status, maquet, dataIni);
        }

        return desenhos;
    }

    // CARREGA O GRAFICO
    public List<BodyDesGraficoDTO> desGrafico() {
        Date dataIni = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dataIni);
        c.add(Calendar.MONTH, -6);

        return desenhoRepository.bodyGrafico(c.getTime());

    }

    @Override
    public List<Desenho> buscaDeseParams(Long maquete, String nCamp, String busca) {
        Optional<Maquete> maq = maqueteService.findById(maquete);
        Maquete mqt = maq.get();

        List<Desenho> desenhos = new ArrayList<Desenho>();

        switch (nCamp) {
        case "tag":
            Optional<Linha> lin = linhaService.findById(busca);
            Linha linha = lin.get();
            desenhos = desenhoRepository.buscaDeseTag(mqt, linha);
            break;
        case "desContratado":
            desenhos = desenhoRepository.buscaDeseCont(mqt, busca);
            break;
        case "desSubtitulo":
            desenhos = desenhoRepository.buscaDeseSub(mqt, busca);
            break;
        case "status":
            desenhos = desenhoRepository.buscaDeseStatus(mqt, busca);
            break;
        case "revisao":
            desenhos = desenhoRepository.buscaDeseRev(mqt, busca);
            break;
        case "comentarios":
            desenhos = desenhoRepository.buscaDeseComen(mqt, busca);
            break;
        case "desIdCad":
            desenhos = desenhoRepository.buscaDeseUs(mqt, busca);
            break;
        case "nomeVerificador":
            desenhos = desenhoRepository.buscaDeseVerif(mqt, busca);
            break;
        case "pipeService":
            desenhos = desenhoRepository.buscaDesePipSer(mqt, busca);
            break;
        case "pipeSpec":
            desenhos = desenhoRepository.buscaDesePipeSpec(mqt, busca);
            break;
        case "pID":
            desenhos = desenhoRepository.buscaDesePid(mqt, busca);
            break;
        case "numFolhas":
            desenhos = desenhoRepository.buscaDesePid(mqt, busca);
            break;

        }

        return desenhos;
    }

    @Override
    public List<BodyDesGraficoDTO> desGraficoSelect(Long proj, int dIni) {
        List<BodyDesGraficoDTO> desenhos = new ArrayList<BodyDesGraficoDTO>();
        if (proj == -1) {
            Date dataIni = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dataIni);
            c.add(Calendar.MONTH, -dIni);
            desenhos = desenhoRepository.bodyGraficoSelectDef(c.getTime());
        } else {
            Date dataIni = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dataIni);
            c.add(Calendar.MONTH, -dIni);

            Maquete maquet = maqueteRepository.findByProjetoNumero(proj);
            desenhos = desenhoRepository.bodyGraficoSelectProjec(c.getTime(), maquet);
        }

        return desenhos;
    }

    @Override
    public Desenho atualizaDesenho(BodyDataFinal bd){

        System.out.println("ANTES = "+bd.getStatusFinal()+" - "+ bd.getComentFinal());    

        String str = cleanComas(bd.getTagFinal());
        /*StringBuilder s = new StringBuilder(bd.getTagFinal()).insert(bd.getTagFinal().indexOf('-'), '"');*/
        
        System.out.println("DEPOIS = "+bd.getStatusFinal()+" - "+ bd.getComentFinal());    
        Linha tags = linhaService.buscaLinha(str);
        Desenho d = desenhoRepository.findByTagPidRev(tags,bd.getDesSubtitulo(),bd.getReviFinal());
        desenhoRepository.editEmissaoFinal(bd.getDataFinal(), bd.getStatusFinal(), bd.getComentFinal(), d.getIdDesenho());
        return d;

    }

}
