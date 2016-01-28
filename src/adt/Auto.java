package adt;

/**
 * Creato da Vlady il 28/01/16.
 * in origine parte del progetto:
 * Pila_Case_Traghetto
 */
public class Auto {

    String targa;
    int lunghezza;

    public Auto(String targa, int lunghezza) {
        this.targa = targa;
        this.lunghezza = lunghezza;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }
}
