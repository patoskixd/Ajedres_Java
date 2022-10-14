package Ajedrez;

public class Peon extends PiezasAjedrez {

    public PartidaAjedrez partidaAjedrez;

    public Peon(Tablero tablero, Jugadores jugador, PartidaAjedrez partidaAjedrez) {
        super(tablero, jugador);
        this.partidaAjedrez = partidaAjedrez;
    }

    @Override
     /*Realizamos todos los movimientos de la pieza Peon*/
    public boolean[][] posibleMovimiento() {
        boolean[][] mat= new boolean[getTablero().getFila()][getTablero().getColumna()];
        Posicion p = new Posicion(0,0);

        if (getJugador() == Jugadores.JUGADOR_N1) {
            p.setValores(posicion.getFila() - 1, posicion.getColumna());
            if (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() - 2, posicion.getColumna());
            Posicion p2 = new Posicion(posicion.getFila() - 1, posicion.getColumna());
            if (getTablero().posicionExiste(p) && !getTablero().hayPieza(p) && getTablero().posicionExiste(p2) && !getTablero().hayPieza(p2) ){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() - 1, posicion.getColumna() - 1);
            if (getTablero().posicionExiste(p) && esPiezaOponente(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() - 1, posicion.getColumna() + 1);
            if (getTablero().posicionExiste(p) && esPiezaOponente(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }     
        }
        else {
            p.setValores(posicion.getFila() + 1, posicion.getColumna());
            if (getTablero().posicionExiste(p) && !getTablero().hayPieza(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() + 2, posicion.getColumna());
            Posicion p2 = new Posicion(posicion.getFila() + 1, posicion.getColumna());
            if (getTablero().posicionExiste(p) && !getTablero().hayPieza(p) && getTablero().posicionExiste(p2) && !getTablero().hayPieza(p2) ){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() + 1, posicion.getColumna() + 1);
            if (getTablero().posicionExiste(p) && esPiezaOponente(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }
            p.setValores(posicion.getFila() + 1, posicion.getColumna() - 1);
            if (getTablero().posicionExiste(p) && esPiezaOponente(p)){
                mat[p.getFila()][p.getColumna()] = true;
            }           
        }
        return mat;
    }

    @Override
    public String toString(){
        return "P";
    }
}
