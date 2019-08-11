package br.com.fatec.drawingController.usuario;

public class BodyDesGraficoDTO {

    private String data;
    private long emitidos;
    private long verificando;
    private long cancelados;

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