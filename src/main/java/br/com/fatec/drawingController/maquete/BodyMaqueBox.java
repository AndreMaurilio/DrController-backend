package br.com.fatec.drawingController.maquete;

public class BodyMaqueBox {

    private Long numProj;
    private String nomProj;

    public BodyMaqueBox() {

    }

    public BodyMaqueBox(Long numProj, String nomProj) {
        this.numProj = numProj;
        this.nomProj = nomProj;
    }

    public long getNumProj() {
        return this.numProj;
    }

    public void setNumProj(long numProj) {
        this.numProj = numProj;
    }

    public String getNomProj() {
        return this.nomProj;
    }

    public void setNomProj(String nomProj) {
        this.nomProj = nomProj;
    }

}
