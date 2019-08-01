package br.com.fatec.drawingController.desenho;

import br.com.fatec.drawingController.usuario.Usuario;
import java.util.Date;
import br.com.fatec.drawingController.maquete.Maquete;

public class BodyDesenho {

    // private long usuarioId;

    private String idCad;

    private String tag;

    private String desContratado;

    private String desSubtitulo;

    private String status; // pensar se usar ENUM e melhor nesse atributo
    // private List<Relatorio> relatorios;

    private String revisao;

    private Date dataini;

    private Date datafim;

    private String comentarios;

    private String nomeVerificador;

    private long maqueteId;

    public BodyDesenho(String idCad, String tag, String desContratado, String desSubtitulo, String status,
            String revisao, Date dataini, Date datafim, String comentarios, String nomeVerificador, long maqueteId) {
        // this.usuarioId = usuarioId;
        this.idCad = idCad;
        this.tag = tag;
        this.desContratado = desContratado;
        this.desSubtitulo = desSubtitulo;
        this.status = status;
        this.revisao = revisao;
        this.dataini = dataini;
        this.datafim = datafim;
        this.comentarios = comentarios;
        this.nomeVerificador = nomeVerificador;
        this.maqueteId = maqueteId;
    }

    public String getIdCad() {
        return this.idCad;
    }

    public void setIdCad(String idCad) {
        this.idCad = idCad;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesContratado() {
        return this.desContratado;
    }

    public void setDesContratado(String desContratado) {
        this.desContratado = desContratado;
    }

    public String getDesSubtitulo() {
        return this.desSubtitulo;
    }

    public void setDesSubtitulo(String desSubtitulo) {
        this.desSubtitulo = desSubtitulo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRevisao() {
        return this.revisao;
    }

    public void setRevisao(String revisao) {
        this.revisao = revisao;
    }

    public Date getDataini() {
        return this.dataini;
    }

    public void setDataini(Date dataini) {
        this.dataini = dataini;
    }

    public Date getDatafim() {
        return this.datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getNomeVerificador() {
        return this.nomeVerificador;
    }

    public void setNomeVerificador(String nomeVerificador) {
        this.nomeVerificador = nomeVerificador;
    }

    public long getMaqueteId() {
        return this.maqueteId;
    }

    public void setMaqueteId(long maqueteId) {
        this.maqueteId = maqueteId;
    }

}