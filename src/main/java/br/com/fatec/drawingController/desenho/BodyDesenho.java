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

    private String pipeService;

    private String pipeSpec;

    private String pID;

    private int numFolhas;

    private long maqueteId;

    public BodyDesenho() {
    }

    public BodyDesenho(String idCad, String tag, String desContratado, String desSubtitulo, String status,
            String revisao, Date dataini, Date datafim, String comentarios, String nomeVerificador, String pipeService,
            String pipeSpec, String pID, int numFolhas, long maqueteId) {
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
        this.pipeService = pipeService;
        this.pipeSpec = pipeSpec;
        this.pID = pID;
        this.numFolhas = numFolhas;
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

    public String getPipeService() {
        return this.pipeService;
    }

    public void setPipeService(String pipeService) {
        this.pipeService = pipeService;
    }

    public String getPipeSpec() {
        return this.pipeSpec;
    }

    public void setPipeSpec(String pipeSpec) {
        this.pipeSpec = pipeSpec;
    }

    public String getPID() {
        return this.pID;
    }

    public void setPID(String pID) {
        this.pID = pID;
    }

    public int getNumFolhas() {
        return this.numFolhas;
    }

    public void setNumFolhas(int numFolhas) {
        this.numFolhas = numFolhas;
    }

    public long getMaqueteId() {
        return this.maqueteId;
    }

    public void setMaqueteId(long maqueteId) {
        this.maqueteId = maqueteId;
    }

    public BodyDesenho idCad(String idCad) {
        this.idCad = idCad;
        return this;
    }

    public BodyDesenho tag(String tag) {
        this.tag = tag;
        return this;
    }

    public BodyDesenho desContratado(String desContratado) {
        this.desContratado = desContratado;
        return this;
    }

    public BodyDesenho desSubtitulo(String desSubtitulo) {
        this.desSubtitulo = desSubtitulo;
        return this;
    }

    public BodyDesenho status(String status) {
        this.status = status;
        return this;
    }

    public BodyDesenho revisao(String revisao) {
        this.revisao = revisao;
        return this;
    }

    public BodyDesenho dataini(Date dataini) {
        this.dataini = dataini;
        return this;
    }

    public BodyDesenho datafim(Date datafim) {
        this.datafim = datafim;
        return this;
    }

    public BodyDesenho comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public BodyDesenho nomeVerificador(String nomeVerificador) {
        this.nomeVerificador = nomeVerificador;
        return this;
    }

    public BodyDesenho pipeService(String pipeService) {
        this.pipeService = pipeService;
        return this;
    }

    public BodyDesenho pipeSpec(String pipeSpec) {
        this.pipeSpec = pipeSpec;
        return this;
    }

    public BodyDesenho pID(String pID) {
        this.pID = pID;
        return this;
    }

    public BodyDesenho numFolhas(int numFolhas) {
        this.numFolhas = numFolhas;
        return this;
    }

    public BodyDesenho maqueteId(long maqueteId) {
        this.maqueteId = maqueteId;
        return this;
    }

}