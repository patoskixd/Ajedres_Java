package Ajedrez;

public class PosicionAjedrez {

    public char columna;
    public Integer fila;
/*Metodo para determinar si la posicion seleccionada no se encuentra en el tablero*/
    public PosicionAjedrez(char columna, Integer fila) {
        if(columna < 'a' || columna > 'h' || fila < 1 || fila > 8){
            throw new AjedrezExcepciones("Error al ubicarte en el tablero. Valores validos desde a1 hasta h8.");
        }
        this.columna = columna;
        this.fila = fila;
    }
/*Metodo para obtener la columna*/
    public char getColumna() {
        return columna;
    }
/*Metodo para obtener la fila*/
    public Integer getFila() {
        return fila;
    }
/*Metodo para posicionar*/
    protected Posicion posicionar(){
        return new Posicion(8-fila,columna - 'a');
    }
/*Metodo calcular distancia*/
    protected static PosicionAjedrez desdePosicion(Posicion posicion){
        return new PosicionAjedrez((char)('a' + posicion.getColumna()),8 - posicion.getFila());
    }

    @Override
    public String toString (){
        return "" + columna + fila;
    }

}
