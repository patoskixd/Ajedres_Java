package Ajedrez;

public class Torre extends PiezasAjedrez {


    public Torre(Tablero tablero, Jugadores jugador) {
        super(tablero, jugador);
    }

    @Override
    public boolean[][] posibleMovimiento() {
        boolean[][] mat = new boolean[getTablero().getFila()][getTablero().getColumna()];
        Posicion p = new Posicion(0, 0);
        /*Movimiento hacia adelante*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna());
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setFila(p.getFila() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
       /*Movimiento hacia la izquierda*/ 
        p.setValores(posicion.getFila(), posicion.getColumna() - 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setColumna(p.getColumna() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia la derecha*/ 
        p.setValores(posicion.getFila(), posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setColumna(p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia atras*/
        p.setValores(posicion.getFila() + 1, posicion.getColumna());
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setFila(p.getFila() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        return mat;
    }
    @Override
    public String toString(){
        return "T";
    }
}

