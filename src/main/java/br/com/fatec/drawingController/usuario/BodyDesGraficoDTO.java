package br.com.fatec.drawingController.usuario;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.fatec.drawingController.maquete.Maquete;

public class BodyDesGraficoDTO {

    private String data;
    private long emitidos;
    private long verificando;
    private long cancelados;

    /*
     * @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
     * property = "projetoNumero")
     * 
     * @JsonIdentityReference(alwaysAsId = true) private Maquete projeto;
     */

    public BodyDesGraficoDTO(String data, long emitidos, long verificando, long cancelados) {
        this.data = data;
        this.emitidos = emitidos;
        this.verificando = verificando;
        this.cancelados = cancelados;
    }

    public String getData() {
        return this.data;
    }

    public long getEmitidos() {
        return this.emitidos;
    }

    public long getVerificando() {
        return this.verificando;
    }

    public long getCancelados() {
        return this.cancelados;
    }

}