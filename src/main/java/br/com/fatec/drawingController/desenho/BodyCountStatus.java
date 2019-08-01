package br.com.fatec.drawingController.desenho;

public class BodyCountStatus {

    private Long cancelado;
    private Long emitido;
    private Long verificando;

    public BodyCountStatus() {

    }

    public BodyCountStatus(Long cancelado, Long emitido, Long verificando) {
        this.cancelado = cancelado;
        this.emitido = emitido;
        this.verificando = verificando;
    }

    public Long getCancelado() {
        return this.cancelado;
    }

    public void setCancelado(Long cancelado) {
        this.cancelado = cancelado;
    }

    public Long getEmitido() {
        return this.emitido;
    }

    public void setEmitido(Long emitido) {
        this.emitido = emitido;
    }

    public long getVerificando() {
        return this.verificando;
    }

    public void setVerificando(Long verificando) {
        this.verificando = verificando;
    }

}
