package Ajedrez;

public class Posicion {
    public int fila;
    public int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
/*Metodo para obtener la fila*/
    public int getFila() {
        return fila;
    }
/*Metodo para colocar la fila*/
    public void setFila(int fila) {
        this.fila = fila;
    }
/*Metodo para obtener la columna*/
    public int getColumna() {
        return columna;
    }
/*Metodo para colocar la columna*/
    public void setColumna(int columna) {
        this.columna = columna;
    }
/*Metodo para colocar los valores*/
    public void setValores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return fila + ", " + columna;
    }

}
