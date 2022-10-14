package Ajedrez;


public class Alfil extends PiezasAjedrez {/*herenic del archivo PiezaAjedrez*/
    public Alfil(Tablero tabla, Jugadores jugador) {
        super(tabla, jugador);
    }

    @Override
    /*Realizamos todos los movimientos de la pieza Alfil*/
    public boolean[][] posibleMovimiento() {

        boolean[][] mat = new boolean[getTablero().getFila()][getTablero().getColumna()];

        Posicion p = new Posicion(0, 0);

        /*Realizamos su movimiento a su lado Noroeste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() - 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() - 1, p.getColumna() - 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }

        /*Realizamos su movimiento a su lado Noreste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() - 1, p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }

        /*Realizamos su movimiento a su lado Suroeste*/
        p.setValores(posicion.getFila() + 1 , posicion.getColumna() + 1);
        while (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)) {
            mat[p.getFila()][p.getColumna()] = true;
            p.setValores(p.getFila() + 1, p.getColumna() + 1);
        }
        if (getTablero().posicionExiste(p) && esPiezaOponente(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }

        /*Realizamos su movimiento a su lado Sureste*/
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
        return "A";
    }
}

