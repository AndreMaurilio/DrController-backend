package br.com.fatec.drawingController.maquete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fatec.drawingController.desenho.Desenho;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "MAQUETE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Maquete implements Serializable {

    private static final long serialVersionUID = 1667218111788384719L;

    @Id
    @Column(name = "maqu_id")
    private Long projetoNumero;

    @Column(name = "maqu_projeto")
    private String projetoNome;

    @Column(name = "maqu_cliente")
    private String projetoCliente;

    @Column(name = "maqu_local")
    private String projetoLocal;

    @Column(name = "maqu_descricao")
    private String projetoDescricao;

    @Column(name = "maqu_data")
    private Date dataProjeto;

    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    // @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Desenho> desenhos;

    public Maquete() {

        this.desenhos = new ArrayList();
    }

    public Long getProjetoNumero() {
        return this.projetoNumero;
    }

    public void setProjetoNumero(Long projetoNumero) {
        this.projetoNumero = projetoNumero;
    }

    public String getProjetoNome() {
        return this.projetoNome;
    }

    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }

    public String getProjetoCliente() {
        return this.projetoCliente;
    }

    public void setProjetoCliente(String projetoCliente) {
        this.projetoCliente = projetoCliente;
    }

    public String getProjetoLocal() {
        return this.projetoLocal;
    }

    public void setProjetoLocal(String projetoLocal) {
        this.projetoLocal = projetoLocal;
    }

    public String getProjetoDescricao() {
        return this.projetoDescricao;
    }

    public void setProjetoDescricao(String projetoDescricao) {
        this.projetoDescricao = projetoDescricao;
    }

    public Date getDataProjeto() {
        return this.dataProjeto;
    }

    public void setDataProjeto(Date dataProjeto) {
        this.dataProjeto = dataProjeto;
    }

    public List<Desenho> getDesenhos() {
        return this.desenhos;
    }

    public void setDesenhos(List<Desenho> desenhos) {
        this.desenhos = desenhos;
    }

    public Maquete projetoNumero(Long projetoNumero) {
        this.projetoNumero = projetoNumero;
        return this;
    }

    public Maquete projetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
        return this;
    }

    public Maquete projetoCliente(String projetoCliente) {
        this.projetoCliente = projetoCliente;
        return this;
    }

    public Maquete projetoDescricao(String projetoDescricao) {
        this.projetoDescricao = projetoDescricao;
        return this;
    }

    public Maquete dataProjeto(Date dataProjeto) {
        this.dataProjeto = dataProjeto;
        return this;
    }

    public Maquete desenhos(List<Desenho> desenhos) {
        this.desenhos = desenhos;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Maquete)) {
            return false;
        }
        Maquete maquete = (Maquete) o;
        return projetoNumero == maquete.projetoNumero && Objects.equals(projetoNome, maquete.projetoNome)
                && Objects.equals(projetoCliente, maquete.projetoCliente)
                && Objects.equals(projetoDescricao, maquete.projetoDescricao)
                && Objects.equals(dataProjeto, maquete.dataProjeto) && Objects.equals(desenhos, maquete.desenhos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projetoNumero, projetoNome, projetoCliente, projetoDescricao, dataProjeto, desenhos);
    }

    @Override
    public String toString() {
        return "{" + " projetoNumero='" + getProjetoNumero() + "'" + ", projetoNome='" + getProjetoNome() + "'"
                + ", projetoCliente='" + getProjetoCliente() + "'" + ", projetoDescricao='" + getProjetoDescricao()
                + "'" + ", dataProjeto='" + getDataProjeto() + "'" + ", desenhos='" + getDesenhos() + "'" + "}";
    }

}