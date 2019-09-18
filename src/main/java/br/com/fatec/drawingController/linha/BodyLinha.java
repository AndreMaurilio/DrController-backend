package br.com.fatec.drawingController.linha;

import org.springframework.data.annotation.Id;

public class BodyLinha {

    @Id
    private String blTag;
    private String blMaterial;
    private String blPendencias;
    private String blFluido;
    private String blArea;
    private String blSite;
    private String blBimTag;
    private Long blMaquete;

    public BodyLinha() {
    }

    public BodyLinha(String blTag, String blMaterial, String blPendencias, String blFluido, String blArea,
            String blSite, String blBimTag, Long blMaquete) {
        this.blTag = blTag;
        this.blMaterial = blMaterial;
        this.blPendencias = blPendencias;
        this.blFluido = blFluido;
        this.blArea = blArea;
        this.blSite = blSite;
        this.blBimTag = blBimTag;
        this.blMaquete = blMaquete;
    }

    public String getBlTag() {
        return this.blTag;
    }

    public void setBlTag(String blTag) {
        this.blTag = blTag;
    }

    public String getBlMaterial() {
        return this.blMaterial;
    }

    public void setBlMaterial(String blMaterial) {
        this.blMaterial = blMaterial;
    }

    public String getBlPendencias() {
        return this.blPendencias;
    }

    public void setBlPendencias(String blPendencias) {
        this.blPendencias = blPendencias;
    }

    public String getBlFluido() {
        return this.blFluido;
    }

    public void setBlFluido(String blFluido) {
        this.blFluido = blFluido;
    }

    public String getBlArea() {
        return this.blArea;
    }

    public void setBlArea(String blArea) {
        this.blArea = blArea;
    }

    public String getBlSite() {
        return this.blSite;
    }

    public void setBlSite(String blSite) {
        this.blSite = blSite;
    }

    public String getBlBimTag() {
        return this.blBimTag;
    }

    public void setBlBimTag(String blBimTag) {
        this.blBimTag = blBimTag;
    }

    public Long getBlMaquete() {
        return this.blMaquete;
    }

    public void setBlMaquete(Long blMaquete) {
        this.blMaquete = blMaquete;
    }

    public BodyLinha blTag(String blTag) {
        this.blTag = blTag;
        return this;
    }

    public BodyLinha blMaterial(String blMaterial) {
        this.blMaterial = blMaterial;
        return this;
    }

    public BodyLinha blPendencias(String blPendencias) {
        this.blPendencias = blPendencias;
        return this;
    }

    public BodyLinha blFluido(String blFluido) {
        this.blFluido = blFluido;
        return this;
    }

    public BodyLinha blArea(String blArea) {
        this.blArea = blArea;
        return this;
    }

    public BodyLinha blSite(String blSite) {
        this.blSite = blSite;
        return this;
    }

    public BodyLinha blBimTag(String blBimTag) {
        this.blBimTag = blBimTag;
        return this;
    }

    public BodyLinha blMaquete(Long blMaquete) {
        this.blMaquete = blMaquete;
        return this;
    }

}