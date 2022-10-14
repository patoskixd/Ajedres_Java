package Ajedrez;

public class Rey extends PiezasAjedrez {

    public PartidaAjedrez partidaAjedrez;

    public Rey(Tablero tablero, Jugadores jugador, PartidaAjedrez partidaAjedrez) {
        super(tablero, jugador);
        this.partidaAjedrez = partidaAjedrez;
    }

    public PartidaAjedrez getpartidaAjedrez() {
        return partidaAjedrez;
    }

    private boolean puedeMover(Posicion posicion) {
        PiezasAjedrez p = (PiezasAjedrez) getTablero().pieza(posicion);
        return p == null || p.getJugador() != getJugador();
    }

    private boolean enroque(Posicion posicion) {
        PiezasAjedrez p = (PiezasAjedrez) getTablero().pieza(posicion);
        return posicion != null && p instanceof Torre && getJugador() == getJugador();
    }

    @Override
    public boolean[][] posibleMovimiento() {
        boolean[][] mat = new boolean[getTablero().getFila()][getTablero().getColumna()];
        Posicion p = new Posicion(0, 0);

        /*Movimiento hacia Alfrente*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna());
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia Atras*/ 
        p.setValores(posicion.getFila() + 1, posicion.getColumna());
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia la Izquierda*/
        p.setValores(posicion.getFila(), posicion.getColumna() - 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia la Derecha*/
        p.setValores(posicion.getFila(), posicion.getColumna() + 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Noroeste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() - 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Sureste */
        p.setValores(posicion.getFila() + 1, posicion.getColumna() + 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Noreste*/
        p.setValores(posicion.getFila() + 1, posicion.getColumna() - 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        /*Movimiento hacia el Suroeste*/
        p.setValores(posicion.getFila() - 1, posicion.getColumna() + 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }

        /*Movimientos de Enroque*/
        if (!partidaAjedrez.isJaque()) {
            Posicion posicionT1 = new Posicion(posicion.getFila(), posicion.getColumna() + 3);
            if (enroque(posicionT1)) {
                Posicion p1 = new Posicion(posicion.getFila(), posicion.getColumna() + 1);
                Posicion p2 = new Posicion(posicion.getFila(), posicion.getColumna() + 2);
                if (getTablero().pieza(p1) == null && getTablero().pieza(p2) == null) {
                    mat[posicion.getFila()][posicion.getColumna() + 2] = true;
                }
            }
            Posicion posicionT2 = new Posicion(posicion.getFila(), posicion.getColumna() - 4);
            if (enroque(posicionT2)) {
                Posicion p1 = new Posicion(posicion.getFila(), posicion.getColumna() - 1);
                Posicion p2 = new Posicion(posicion.getFila(), posicion.getColumna() - 2);
                Posicion p3 = new Posicion(posicion.getFila(), posicion.getColumna() - 3);
                if (getTablero().pieza(p1) == null && getTablero().pieza(p2) == null && getTablero().pieza(p3) == null) {
                    mat[posicion.getFila()][posicion.getColumna() - 2] = true;
                }
            }
        }
        return mat;
    }
    @Override
    public String toString() {
        return "K";
    }

}
