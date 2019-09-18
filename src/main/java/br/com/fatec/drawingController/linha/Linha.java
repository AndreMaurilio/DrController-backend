package br.com.fatec.drawingController.linha;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.maquete.Maquete;

@XmlRootElement
// @XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "LINHA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Linha {

    @Id
    @Column(name = "li_tag", nullable = false, length = 100)
    private String liTag;

    @Column(name = "li_material")
    private String liMaterial;

    @Column(name = "li_pendencias")
    private String liPendencias;

    @Column(name = "li_fluido")
    private String liFluido;

    @Column(name = "li_area")
    private String liArea;

    @Column(name = "li_site")
    private String liSite;

    @Column(name = "li_bim_tag")
    private String liBimTag;

    @OneToMany(fetch = FetchType.LAZY)
    // @JsonManagedReference
    private List<Desenho> desenhos;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projetoNumero")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maqu_id")
    private Maquete maquete;

    public Linha() {
        this.desenhos = new ArrayList<>();
    }

    public Linha(String liTag, String liMaterial, String liPendencias, String liFluido, String liArea, String liSite,
            String liBimTag, Maquete maquete) {
        this.liTag = liTag;
        this.liMaterial = liMaterial;
        this.liPendencias = liPendencias;
        this.liFluido = liFluido;
        this.liArea = liArea;
        this.liSite = liSite;
        this.liBimTag = liBimTag;
        this.maquete = maquete;
    }

    public String getLiTag() {
        return this.liTag;
    }

    public void setLiTag(String liTag) {
        this.liTag = liTag;
    }

    public String getLiMaterial() {
        return this.liMaterial;
    }

    public void setLiMaterial(String liMaterial) {
        this.liMaterial = liMaterial;
    }

    public String getLiPendencias() {
        return this.liPendencias;
    }

    public void setLiPendencias(String liPendencias) {
        this.liPendencias = liPendencias;
    }

    public String getLiFluido() {
        return this.liFluido;
    }

    public void setLiFluido(String liFluido) {
        this.liFluido = liFluido;
    }

    public String getLiArea() {
        return this.liArea;
    }

    public void setLiArea(String liArea) {
        this.liArea = liArea;
    }

    public String getLiSite() {
        return this.liSite;
    }

    public void setLiSite(String liSite) {
        this.liSite = liSite;
    }

    public String getLiBimTag() {
        return this.liBimTag;
    }

    public void setLiBimTag(String liBimTag) {
        this.liBimTag = liBimTag;
    }

    public List<Desenho> getDesenhos() {
        return this.desenhos;
    }

    public void setDesenhos(List<Desenho> desenhos) {
        this.desenhos = desenhos;
    }

    public Maquete getMaquete() {
        return this.maquete;
    }

    public void setMaquete(Maquete maquete) {
        this.maquete = maquete;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Linha)) {
            return false;
        }
        Linha linha = (Linha) o;
        return Objects.equals(liTag, linha.liTag) && Objects.equals(liMaterial, linha.liMaterial)
                && Objects.equals(liPendencias, linha.liPendencias) && Objects.equals(liFluido, linha.liFluido)
                && Objects.equals(liArea, linha.liArea) && Objects.equals(liSite, linha.liSite)
                && Objects.equals(liBimTag, linha.liBimTag) && Objects.equals(desenhos, linha.desenhos)
                && Objects.equals(maquete, linha.maquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liTag, liMaterial, liPendencias, liFluido, liArea, liSite, liBimTag, desenhos, maquete);
    }

    @Override
    public String toString() {
        return "{" + " liTag='" + getLiTag() + "'" + ", liMaterial='" + getLiMaterial() + "'" + ", liPendencias='"
                + getLiPendencias() + "'" + ", liFluido='" + getLiFluido() + "'" + ", liArea='" + getLiArea() + "'"
                + ", liSite='" + getLiSite() + "'" + ", liBimTag='" + getLiBimTag() + "'" + ", desenhos='"
                + getDesenhos() + "'" + ", maquete='" + getMaquete() + "'" + "}";
    }

}