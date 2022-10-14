package Ajedrez;

public class Caballo extends PiezasAjedrez {
    public Caballo(Tablero tabla, Jugadores jugador) {
        super(tabla, jugador);
    }
    /*Comprobamos si puede mover*/
    private boolean puedeMover(Posicion posicion){
        PiezasAjedrez p = (PiezasAjedrez)getTablero().pieza(posicion);
        return  p == null || p.getJugador() != getJugador();
    }
    
    @Override
    /*Creamos todos los movimientos de caballo*/
    public boolean[][] posibleMovimiento() {
        boolean[][] mat= new boolean[getTablero().getFila()][getTablero().getColumna()];
        Posicion p = new Posicion(0,0);
        p.setValores(posicion.getFila() - 1, posicion.getColumna() - 2);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() - 2, posicion.getColumna() - 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() - 2, posicion.getColumna() + 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() - 1, posicion.getColumna() + 2);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() + 1, posicion.getColumna() + 2);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() + 2, posicion.getColumna() + 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() + 2, posicion.getColumna() - 1);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        p.setValores(posicion.getFila() + 1, posicion.getColumna() - 2);
        if (getTablero().posicionExiste(p) && puedeMover(p)) {
            mat[p.getFila()][p.getColumna()] = true;
        }
        return mat;
    }

    @Override
    public String toString(){
        return "C";
    }
}

