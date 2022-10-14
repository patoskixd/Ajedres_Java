package Ajedrez;

public abstract class Pieza {
    protected Posicion posicion;
    public Tablero tablero;
    Posicion position;
    public abstract boolean[][] posibleMovimiento();

    public Pieza(Tablero tablero) {
        this.tablero = tablero;
        posicion = null;
    }
/*Metodo para obtener el tablero */
    protected Tablero getTablero() {
        return tablero;
    }
    /*Metodo booleano para realizar posible movimiento*/
    public boolean posibleMovimiento(Posicion posicion) {
        return Pieza.this.posibleMovimiento()[posicion.getFila()][posicion.getColumna()];
    }
/*Metodo booleano para ver si existe posible movimiento*/
    public boolean existePosibleMovimiento() {
        boolean[][] mat = Pieza.this.posibleMovimiento();
        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
