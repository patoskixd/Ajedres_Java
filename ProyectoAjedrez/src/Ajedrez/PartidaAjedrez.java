package Ajedrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaAjedrez { 
    public PiezasAjedrez promocion;
    public Tablero tablero;
    public Jugadores turnoJugador;
    public boolean jaque;
    public boolean jaquemate;
/*Creamos una lista llamada piezas tablero*/
    public List<Pieza> piezasTableros = new ArrayList<>();

/*Metodo que obtiene el turno del jugador*/
    public Jugadores getTurnoJugador() {
        return turnoJugador;
    }
/*Metodo booleano que retora si es jaque o no*/
    public boolean isJaque() {
        return jaque;
    }
/*Metodo booleano que retorna si es jaquemate o no*/
    public boolean isJaquemate() {
        return jaquemate;
    }
/*Metodo que obtiene la promocion*/
    public PiezasAjedrez getPromocion() {
        return promocion;
    }
/*Crea la partida para que comienze el primer movimiento el jugador numero 1*/
    public PartidaAjedrez() {
        this.tablero = new Tablero(8, 8);
        turnoJugador = Jugadores.JUGADOR_N1;
        configuracionInicial();
    }
/*Guarda todas las piezas a una matriz*/
    public PiezasAjedrez[][] getPiezas() {
        PiezasAjedrez[][] mat = new PiezasAjedrez[tablero.getFila()][tablero.getColumna()];
        for (int i = 0; i < tablero.getFila(); i++) {
            for (int j = 0; j < tablero.getColumna(); j++) {
                mat[i][j] = (PiezasAjedrez) tablero.pieza(i, j);
            }
        }
        return mat;
    }
/*Metodo para realizar movimiento de las piezas*/
    public PiezasAjedrez realizaMovimiento(PosicionAjedrez origenPosicion, PosicionAjedrez objetivoPosicion) {
        Posicion origen = origenPosicion.posicionar();
        Posicion objetivo = objetivoPosicion.posicionar();
        validarOrigenPosicion(origen);
        validarObjetivoPosicion(origen, objetivo);
        Pieza piezaCapturada = hacerMovimiento(origen, objetivo);
        if (jaque(turnoJugador)) {
            deshacerMovimiento(origen, objetivo, piezaCapturada);
            throw new AjedrezExcepciones("No puedes colocar la pieza ahí, estás en jaque!");
        }
        PiezasAjedrez piezaMovida = (PiezasAjedrez)tablero.pieza(objetivo);

        /*Realizamos el movimiento de promocion en los peones*/
        promocion = null;
        if(piezaMovida instanceof Peon){
            if((piezaMovida.getJugador() == Jugadores.JUGADOR_N1 && objetivo.getFila() == 0 )||(piezaMovida.getJugador() == Jugadores.JUGADOR_N2 && objetivo.getFila() == 7 )){
                promocion =  (PiezasAjedrez)tablero.pieza(objetivo);
                promocion = reemplazarPiezaPromocion("A");
            }
        }

        jaque = (jaque(oponente(turnoJugador)));

        if (jaquemate(oponente(turnoJugador))) {
            jaquemate = true;
        } else {
            siguienteTurno();
        }
        return null;
    }
        /*Realizamos la seleccion y el remplazamiento de la pieza tras su promocion*/
        public PiezasAjedrez reemplazarPiezaPromocion(String type){
            if(promocion == null){
            throw new IllegalStateException("No hay piezas para ser Promocion");
        }
        if(!type.equals("T") && !type.equals("R") && !type.equals("C") && !type.equals("A")){ /*Utilizamos este .equals para comparar y deuelve un valor  booleano */
            throw new InvalidParameterException("Movimiento no valido");
        }
        Posicion posicion = promocion.getPosicionAjedrez().posicionar();
        Pieza p = tablero.quitarPieza(posicion);
        piezasTableros.remove(p);

        PiezasAjedrez nuevaPieza = nuevaPieza(type, promocion.getJugador());
        tablero.colocarPieza(nuevaPieza,posicion);
        piezasTableros.add(nuevaPieza);

        return nuevaPieza;
    }
/*Reemplazamos las piezas seleccion de las piezas de promocion*/
    private PiezasAjedrez nuevaPieza(String type, Jugadores jugador) {
        if (type.equals("A")) return new Alfil(tablero, jugador);
        if (type.equals("C")) return new Caballo(tablero, jugador);
        if (type.equals("R")) return new Reina(tablero, jugador);
        return new Torre(tablero, jugador);
    }
/*Metodo que void valida la posicion que seleccionas, si no te pertenecen la pieza, si no hay piezas en la posicion y si no hay movimientos validos para la pieza*/
    private void validarOrigenPosicion(Posicion posicion) {
        if (!tablero.hayPieza(posicion)) {
            throw new AjedrezExcepciones("No hay ninguna pieza en la posicion de origen.");
        }
        if (turnoJugador != ((PiezasAjedrez) tablero.pieza(posicion)).getJugador()) {
            throw new AjedrezExcepciones("La pieza seleccionada no es suya");
        }
        if (!tablero.pieza(posicion).existePosibleMovimiento()) {
            throw new AjedrezExcepciones("No hay movimientos validos para la pieza escogida");
        }
    }
/*Metodo booleano que verifica si es posible el movimiento*/
    public boolean[][] posibleMovimiento(PosicionAjedrez origenposicion) {
        Posicion posicion = origenposicion.posicionar();
        validarOrigenPosicion(posicion);
        return tablero.pieza(posicion).posibleMovimiento();
    }
/*Metodo que realizar el moviemiento*/
    private Pieza hacerMovimiento(Posicion origen, Posicion objetivo) {
        PiezasAjedrez p = (PiezasAjedrez) tablero.quitarPieza(origen);
        
        Pieza piezaCapturada = tablero.quitarPieza(objetivo);
        tablero.colocarPieza(p, objetivo);
        if (piezaCapturada != null) {
            piezasTableros.remove(piezaCapturada);

        }

        /*Realiza el enoreque a su lado derecho */
        if(p instanceof Rey && objetivo.getColumna() == origen.getColumna() + 2){
            Posicion origenT = new Posicion(origen.getFila(), origen.getColumna() + 3);
            Posicion objetivoT = new Posicion(origen.getFila(), origen.getColumna() + 1);
            PiezasAjedrez torre = (PiezasAjedrez)tablero.quitarPieza(origenT);
            tablero.colocarPieza(torre,objetivoT);
            
        }
        /*Realiza el enroque a su lado izquierdo*/
        else if(p instanceof Rey && objetivo.getColumna() == origen.getColumna() - 2){
            Posicion origenT = new Posicion(origen.getFila(), origen.getColumna() - 4);
            Posicion objetivoT = new Posicion(origen.getFila(), origen.getColumna() - 1);
            PiezasAjedrez torre = (PiezasAjedrez)tablero.quitarPieza(origenT);
            tablero.colocarPieza(torre,objetivoT);
            
        }

        if (p instanceof Peon) {
            if (origen.getColumna() != objetivo.getColumna() && piezaCapturada == null) {
                Posicion posicionPeon;
                if (p.getJugador() == Jugadores.JUGADOR_N1) {
                    posicionPeon = new Posicion(objetivo.getFila() + 1, objetivo.getColumna());
                }
                else {
                    posicionPeon = new Posicion(objetivo.getFila() - 1, objetivo.getColumna());
                }
                piezaCapturada = tablero.quitarPieza(posicionPeon);

                piezasTableros.remove(piezaCapturada);
            }
        }

        return piezaCapturada;
    }
/*Metodo que deshace el movimiento realizado*/
    private void deshacerMovimiento(Posicion origen, Posicion objetivo, Pieza capturada) {
        PiezasAjedrez p = (PiezasAjedrez) tablero.quitarPieza(objetivo);
        
        tablero.colocarPieza(p, origen);

        if (capturada != null) {
            tablero.colocarPieza(capturada, objetivo);

        }
        /*Realiza su enroque a su lado derecho*/
        if(p instanceof Rey && objetivo.getColumna() == origen.getColumna() + 2){
            Posicion origenT = new Posicion(origen.getFila(), origen.getColumna() + 3);
            Posicion objeticoT = new Posicion(origen.getFila(), origen.getColumna() + 1);
            PiezasAjedrez torre = (PiezasAjedrez)tablero.quitarPieza(objeticoT);
            tablero.colocarPieza(torre,origenT);
            
        }
        /*Realiza el enroque a su lado izquierdo*/
        else if(p instanceof Rey && objetivo.getColumna() == origen.getColumna() - 2){
            Posicion origenT = new Posicion(origen.getFila(), origen.getColumna() - 4);
            Posicion objetivoT = new Posicion(origen.getFila(), origen.getColumna() - 1);
            PiezasAjedrez torre = (PiezasAjedrez)tablero.quitarPieza(objetivoT);
            tablero.colocarPieza(torre,origenT);
            
        }
    
    }
/*Metodo que valida si la posicion final asignada es posible moverla a ese punto asignado */
    private void validarObjetivoPosicion(Posicion origen, Posicion objetivo) {
        if (!tablero.pieza(origen).posibleMovimiento(objetivo)) {
            throw new AjedrezExcepciones("La pieza escogida no se puede mover hacia esa posición");
        }
    }
/*Metodo que cambia de turno para losjugadores*/
    private void siguienteTurno() {
        turnoJugador = (turnoJugador == Jugadores.JUGADOR_N1) ? Jugadores.JUGADOR_N2 : Jugadores.JUGADOR_N1;
    }
/*Metodo para colocar la nueva pieza */
    private void colocarNuevaPieza(char columna, int fila, PiezasAjedrez pieza) {
        tablero.colocarPieza(pieza, new PosicionAjedrez(columna, fila).posicionar());
        piezasTableros.add(pieza);
    }
/*Metodo para el realizar correctamente el cambio de turno*/
    private Jugadores oponente(Jugadores jugador) {
        return (jugador == Jugadores.JUGADOR_N1) ? Jugadores.JUGADOR_N2 : Jugadores.JUGADOR_N1;
    }
/*Metodo para retornar las pieza si es un rey*/
    private PiezasAjedrez king(Jugadores jugador) {
        List<Pieza> lista = listaJugadorPiezas(jugador);
        for (Pieza p : lista) {
            if (p instanceof Rey) {
                return (PiezasAjedrez) p;
            }
        }
        throw new IllegalStateException("No hay un rey con el color " + jugador);
    }
/*Metodo coleccionar todo y listar la piezas del jugador*/
    private List<Pieza> listaJugadorPiezas(Jugadores jugador) {
        return piezasTableros.stream()
                .filter(x -> ((PiezasAjedrez) x)
                        .getJugador() == jugador)
                .collect(Collectors.toList());
    }
/*Metodo booleano para comprobar si las piezas del oponente esta en jaque*/
    private boolean jaque(Jugadores jugador) {
        Posicion PosicionRey = king(jugador).getPosicionAjedrez().posicionar();
        List<Pieza> piezasOponente = listaJugadorPiezas(oponente(jugador));

        for (Pieza p : piezasOponente) {
            boolean[][] mat = p.posibleMovimiento();
            if (mat[PosicionRey.getFila()][PosicionRey.getColumna()]) {
                return true;
            }
        }
        return false;
    }
/*Metodo booleando para comprobar si las piezas del oponente esta en jaquemate*/
    private boolean jaquemate(Jugadores jugador) {
        if (!jaque(jugador)) {
            return false;
        }
        List<Pieza> lista = listaJugadorPiezas(jugador);
        for (Pieza p : lista) {
            boolean[][] mat = p.posibleMovimiento();
            for (int i = 0; i < tablero.getFila(); i++) {
                for (int j = 0; j < tablero.getColumna(); j++) {
                    if (mat[i][j]) {
                        Posicion origen = ((PiezasAjedrez) p).getPosicionAjedrez().posicionar();
                        Posicion objetivo = new Posicion(i, j);
                        Pieza piezaCapturada = hacerMovimiento(origen, objetivo);
                        boolean jaque = jaque(jugador);
                        deshacerMovimiento(origen, objetivo, piezaCapturada);
                        if (!jaque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void configuracionInicial() {
        colocarNuevaPieza('a', 1, new Torre(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('b', 1, new Caballo(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('c', 1, new Alfil(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('d', 1, new Reina(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('e', 1, new Rey(tablero, Jugadores.JUGADOR_N1, this));
        colocarNuevaPieza('f', 1, new Alfil(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('g', 1, new Caballo(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('h', 1, new Torre(tablero, Jugadores.JUGADOR_N1));
        colocarNuevaPieza('a', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('b', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('c', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('d', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('e', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('f', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('g', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));
        colocarNuevaPieza('h', 2, new Peon(tablero, Jugadores.JUGADOR_N1,this));

        colocarNuevaPieza('a', 8, new Torre(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('b', 8, new Caballo(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('c', 8, new Alfil(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('d', 8, new Reina(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('e', 8, new Rey(tablero, Jugadores.JUGADOR_N2, this));
        colocarNuevaPieza('f', 8, new Alfil(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('g', 8, new Caballo(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('h', 8, new Torre(tablero, Jugadores.JUGADOR_N2));
        colocarNuevaPieza('a', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('b', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('c', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('d', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('e', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('f', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('g', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
        colocarNuevaPieza('h', 7, new Peon(tablero, Jugadores.JUGADOR_N2,this));
    }

}
