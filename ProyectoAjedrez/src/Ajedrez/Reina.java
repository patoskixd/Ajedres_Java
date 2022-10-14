package Ajedrez;

public class Reina extends PiezasAjedrez {


    public Reina(Tablero tablero, Jugadores jugador) {
        super(tablero, jugador);
    }



    @Override
    /*Realizamos todos los movimientos de la pieza Peon*/
    public boolean[][] posibleMovimiento() {
        boolean[][] mat = new boolean[getTablero().getFila()][getTablero().getColumna()];
        Posicion p = new Posicion(0, 0);
        /*Movimiento hacia Alfrente*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna());
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setFila(p.getFila() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia la Izquierda*/ 
        p.setValores(posicion.getFila(), posicion.getColumna() - 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setColumna(p.getColumna() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia la Derecha*/
        p.setValores(posicion.getFila(), posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setColumna(p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia Atras*/ 
        p.setValores(posicion.getFila() + 1, posicion.getColumna());
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setFila(p.getFila() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Noroeste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() - 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() - 1, p.getColumna() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Noreste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() - 1, p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Sureste*/ 
        p.setValores(posicion.getFila() + 1 , posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() + 1, p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Suroeste*/
        p.setValores(posicion.getFila() + 1, posicion.getColumna() - 1 );
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() + 1, p.getColumna() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        return mat;
    }
    @Override
        public String toString(){
        return "R";
    }
}

