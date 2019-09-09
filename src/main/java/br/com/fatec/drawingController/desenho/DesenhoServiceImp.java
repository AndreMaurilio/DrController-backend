package br.com.fatec.drawingController.desenho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;
import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.usuario.UsuarioRepository;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.maquete.MaqueteRepository;
import br.com.fatec.drawingController.maquete.MaqueteService;

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
            Desenho d = desenhoRepository.findByTagRev(revisao, tag);

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
                desenho.setTag(tag);
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

        return desenhoRepository.desDeMesmaTag(tag);
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

        return desenhoRepository.bodyGrafico();

    }

}
