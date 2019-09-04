package br.com.fatec.drawingController.desenho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Desenho> mesmaTag(String tag) {

        return desenhoRepository.desDeMesmaTag(tag);
    }

    // ******CONTAGEM SEM SELECAO DE PROJETO******/
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

    public List<Desenho> desenhosPorStatusCalend(String status, Date dIni, Date dFim) {

        return desenhoRepository.listaPorStatus(status, dIni, dFim);
    }

    public List<Desenho> desenhosPorStatusMesAtual(String status, Date dIni) {

        return desenhoRepository.listaPorStatusDefault(status, dIni);
    }

    public List<Desenho> desenhosPorMaquete(Long numMaquete) {

        return desenhoRepository.buscaPorMaquete(maqueteRepository.findByProjetoNumero(numMaquete));
    }

    public List<BodyDesGraficoDTO> desGrafico() {

        return desenhoRepository.bodyGrafico();

    }

}
