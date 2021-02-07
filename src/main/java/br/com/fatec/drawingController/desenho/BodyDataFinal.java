package br.com.fatec.drawingController.desenho;

import java.util.Date;

public class BodyDataFinal {

    private Date dataFinal;
    private String statusFinal;
    private String tagFinal;
    private String comentFinal;
    private String reviFinal;
    private String desSubtitulo;

    public BodyDataFinal(Date dataFinal, String statusFinal, String tagFinal, String comentFinal, String reviFinal, String desSubtitulo) {
        this.dataFinal = dataFinal;
        this.statusFinal = statusFinal;
        this.tagFinal = tagFinal;
        this.comentFinal = comentFinal;
        this.reviFinal = reviFinal;
        this.desSubtitulo = desSubtitulo;
    }

    public Date getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getStatusFinal() {
        return this.statusFinal;
    }

    public void setStatusFinal(String statusFinal) {
        this.statusFinal = statusFinal;
    }

    public String getTagFinal() {
        return this.tagFinal;
    }

    public void setTagFinal(String tagFinal) {
        this.tagFinal = tagFinal;
    }

    public String getComentFinal() {
        return this.comentFinal;
    }

    public void setComentFinal(String comentFinal) {
        this.comentFinal = comentFinal;
    }

    public String getReviFinal() {
        return this.reviFinal;
    }

    public void setReviFinal(String reviFinal) {
        this.reviFinal = reviFinal;
    }

    public String getDesSubtitulo() {
        return this.desSubtitulo;
    }

    public void setDesSubtitulol(String tagFinal) {
        this.desSubtitulo = tagFinal;
    }

}