package br.com.fatec.drawingController.usuario;


public enum Perfil{

ADMINISTRADOR(1),SUPEVISOR(2),DESENHISTA(3);


private int cod;



Perfil(int cod){
    this.setCod(cod);
}
public int getCod() {
    return cod;
}

public void setCod(int cod) {
    this.cod = cod;
}




}
