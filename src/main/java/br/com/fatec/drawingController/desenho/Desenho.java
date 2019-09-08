package br.com.fatec.drawingController.desenho;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GeneratorType;

import br.com.fatec.drawingController.usuario.Usuario;
import br.com.fatec.drawingController.maquete.Maquete;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_seq", initialValue = 1, allocationSize = 1)
@Table(name = "DESENHO")
public class Desenho {

    // private static final long serialVersionUID = 1507218635788384719L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @Column(name = "id_desenho")
    private long idDesenho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "usu_id")
    private Usuario usuario;

    @Column(name = "des_tag")
    private String tag;

    @Column(name = "des_contratado")
    private String desContratado;

    @Column(name = "des_subtitulo")
    private String desSubtitulo;

    @Column(name = "des_status")
    private String status; // pensar se usar ENUM e melhor nesse atributo
    // private List<Relatorio> relatorios;

    @Column(name = "des_revisao")
    private String revisao;

    @Column(name = "des_data_ini")
    private Date dataini;

    @Column(name = "des_data_fim")
    private Date datafim;

    @Column(name = "des_comentario")
    private String comentarios;

    @Column(name = "des_idcad")
    private String desIdCad;

    @Column(name = "des_verificador")
    private String nomeVerificador;

    // novos campos 26/08/2019

    @Column(name = "des_pipeservice")
    private String pipeService;

    @Column(name = "des_pipespec")
    private String pipeSpec;

    @Column(name = "des_pid")
    private String pID;
    @Column(name = "des_numfolhas")
    private int numFolhas;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projetoNumero")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "des_maquete")
    private Maquete maquete;

    public Desenho() {
    }

    public Desenho(long idDesenho, Usuario usuario, String tag, String desContratado, String desSubtitulo,
            String status, String revisao, Date dataini, Date datafim, String comentarios, String desIdCad,
            String nomeVerificador, String pipeService, String pipeSpec, String pID, int numFolhas, Maquete maquete) {
        this.idDesenho = idDesenho;
        this.usuario = usuario;
        this.tag = tag;
        this.desContratado = desContratado;
        this.desSubtitulo = desSubtitulo;
        this.status = status;
        this.revisao = revisao;
        this.dataini = dataini;
        this.datafim = datafim;
        this.comentarios = comentarios;
        this.desIdCad = desIdCad;
        this.nomeVerificador = nomeVerificador;
        this.pipeService = pipeService;
        this.pipeSpec = pipeSpec;
        this.pID = pID;
        this.numFolhas = numFolhas;
        this.maquete = maquete;
    }

    public long getIdDesenho() {
        return this.idDesenho;
    }

    public void setIdDesenho(long idDesenho) {
        this.idDesenho = idDesenho;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getDesIdCad() {
        return this.desIdCad;
    }

    public void setDesIdCad(String desIdCad) {
        this.desIdCad = desIdCad;
    }

    public String getNomeVerificador() {
        return this.nomeVerificador;
    }

    public void setNomeVerificador(String nomeVerificador) {
        this.nomeVerificador = nomeVerificador;
    }

    public Maquete getMaquete() {
        return this.maquete;
    }

    public void setMaquete(Maquete maquete) {
        this.maquete = maquete;
    }

    public Desenho idDesenho(long idDesenho) {
        this.idDesenho = idDesenho;
        return this;
    }

    public Desenho usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Desenho tag(String tag) {
        this.tag = tag;
        return this;
    }

    public Desenho desContratado(String desContratado) {
        this.desContratado = desContratado;
        return this;
    }

    public Desenho desSubtitulo(String desSubtitulo) {
        this.desSubtitulo = desSubtitulo;
        return this;
    }

    public Desenho status(String status) {
        this.status = status;
        return this;
    }

    public Desenho revisao(String revisao) {
        this.revisao = revisao;
        return this;
    }

    public Desenho dataini(Date dataini) {
        this.dataini = dataini;
        return this;
    }

    public Desenho datafim(Date datafim) {
        this.datafim = datafim;
        return this;
    }

    public Desenho comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public Desenho desIdCad(String desIdCad) {
        this.desIdCad = desIdCad;
        return this;
    }

    public Desenho nomeVerificador(String nomeVerificador) {
        this.nomeVerificador = nomeVerificador;
        return this;
    }

    public Desenho maquete(Maquete maquete) {
        this.maquete = maquete;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Desenho)) {
            return false;
        }
        Desenho desenho = (Desenho) o;
        return idDesenho == desenho.idDesenho && Objects.equals(usuario, desenho.usuario)
                && Objects.equals(tag, desenho.tag) && Objects.equals(desContratado, desenho.desContratado)
                && Objects.equals(desSubtitulo, desenho.desSubtitulo) && Objects.equals(status, desenho.status)
                && Objects.equals(revisao, desenho.revisao) && Objects.equals(dataini, desenho.dataini)
                && Objects.equals(datafim, desenho.datafim) && Objects.equals(comentarios, desenho.comentarios)
                && Objects.equals(desIdCad, desenho.desIdCad) && Objects.equals(desIdCad, desenho.desIdCad)
                && Objects.equals(nomeVerificador, desenho.nomeVerificador)
                && Objects.equals(pipeService, desenho.pipeService) && Objects.equals(pipeSpec, desenho.pipeSpec)
                && Objects.equals(pID, desenho.pID) && numFolhas == desenho.numFolhas
                && Objects.equals(maquete, desenho.maquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDesenho, usuario, tag, desContratado, desSubtitulo, status, revisao, dataini, datafim,
                comentarios, desIdCad, nomeVerificador, maquete);
    }

    @Override
    public String toString() {
        return "{" + " idDesenho='" + getIdDesenho() + "'" + ", usuario='" + getUsuario() + "'" + ", tag='" + getTag()
                + "'" + ", desContratado='" + getDesContratado() + "'" + ", desSubtitulo='" + getDesSubtitulo() + "'"
                + ", status='" + getStatus() + "'" + ", revisao='" + getRevisao() + "'" + ", dataini='" + getDataini()
                + "'" + ", datafim='" + getDatafim() + "'" + ", comentarios='" + getComentarios() + "'" + ", desIdCad='"
                + getDesIdCad() + "'" + "'" + ", nomeVerificador='" + getNomeVerificador() + "'" + ", pipeService='"
                + getPipeService() + "'" + ", pipeSpec='" + getPipeSpec() + "'" + ", pID='" + getPID() + "'"
                + ", numFolhas='" + getNumFolhas() + "'" + ", maquete='" + getMaquete() + "'" + "}";
    }

}