package Ajedrez;

public class Tablero {
    public Integer filas;
    public Integer columnas;
    public Pieza[][] piezas;
/*Metodo que crea el tablero*/
    public Tablero(int filas, int columnas) {
        if (filas < 1 || columnas < 1) {
            throw new TablaExcepciones("Error al crear el tablero " +
                    "Es necesario que haya por lo menos 1 linea y 1 columna");
        }
        this.filas = filas;
        this.columnas = columnas;
        piezas = new Pieza[filas][columnas];
    }
/*Metodo que obtiene la fila*/
    public Integer getFila() {
        return filas;
    }
/*Metodo que obtiene la columna*/
    public Integer getColumna() {
        return columnas;
    }
/*Metodo que comprueba si la posicion se encuentra dentro del tablero*/
    public Pieza pieza(Integer fila, Integer columna){
        if(!Tablero.this.posicionExiste(fila, columna)){
            throw new TablaExcepciones("La posición está fuera del tablero");
        }
        return piezas[fila][columna];
    }
/*Metodo que comprueba si la posicion se encuentra dentro del tablero*/
    public Pieza pieza(Posicion posicion){
        if (!posicionExiste(posicion)){
            throw new TablaExcepciones("La posición está fuera del tablero");
        }
        return piezas[posicion.getFila()][posicion.getColumna()];
    }
/*Metodo para  comprobar si puede colocar pieza*/
    public  void colocarPieza(Pieza pieza, Posicion posicion){
        if(hayPieza(posicion)){
            throw new TablaExcepciones("Ya existe una pieza en la posición "+ posicion);
        }
        piezas[posicion.getFila()][posicion.getColumna()] = pieza;
        pieza.posicion = posicion;
    }
/*Metodo para comprobar si puede quitar pieza*/
    public Pieza quitarPieza (Posicion posicion){
        if(!posicionExiste(posicion)){
            throw new TablaExcepciones("La posición está fuera del tablero");
        }
        if (pieza(posicion) == null) {
            return null;
        }
        Pieza aux = pieza(posicion);
        aux.posicion = null;
        piezas[posicion.getFila()][posicion.getColumna()] = null;
        return aux;
    }
/*Metodo booleando para comprobar si la posicion existe*/
    private boolean posicionExiste(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }
/*Metodo booleando para comprobar si la posicion existe*/
    public boolean posicionExiste(Posicion posicion) {
        return Tablero.this.posicionExiste(posicion.getFila(), posicion.getColumna());
    }
/*Metodo booleando para comprobar si la pieza existe*/
    public boolean hayPieza(Posicion posicion) {
        if (!posicionExiste(posicion)) {
            throw new TablaExcepciones("La posición está fuera del tablero");
        }
        return pieza(posicion) != null;
    }
}
