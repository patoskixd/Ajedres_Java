package Ajedrez;

public abstract class PiezasAjedrez extends Pieza {

    private Jugadores jugador;
    

    public PiezasAjedrez(Tablero tablero, Jugadores jugador) {
        super(tablero);
        this.jugador = jugador;
    }
/*Metodo para obtener el jugador*/
    public Jugadores getJugador() {
        return jugador;
    }
/*Metodo booleano para determinar si la pieza es del oponente*/
    protected boolean esPiezaOponente(Posicion posicion) {
        PiezasAjedrez p = (PiezasAjedrez)getTablero().pieza(posicion);
        return p != null && p.getJugador() != jugador;
    }
    public PosicionAjedrez getPosicionAjedrez(){
        return PosicionAjedrez.desdePosicion(posicion);
    }
}

