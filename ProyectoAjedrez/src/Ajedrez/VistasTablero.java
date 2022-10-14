package Ajedrez;

import java.util.*;

public class VistasTablero {
/*Metodo para leer la posicion de la pieza seleccionada y comprobar */
    public static PosicionAjedrez leerPosicionAjedrez(Scanner sc){
        try {
            String s = sc.nextLine();
            char columna = s.charAt(0);
            int fila = Integer.parseInt(s.substring(1));
            return new PosicionAjedrez(columna, fila);
        }catch (RuntimeException e){
                throw new InputMismatchException("Error al leer la posición en el tablero. Solo se puede entre a1 hasta h8");
        }
    }
    /*Metodo que imprime el partido de ajedrez*/
    public static void imprimePartido(PartidaAjedrez partidaAjedrez){
        VistasTablero.imprimeTablero(partidaAjedrez.getPiezas());
        System.out.println();

        if (!partidaAjedrez.isJaquemate()){
            System.out.println("Toca mover pieza al: "+ partidaAjedrez.getTurnoJugador());
            if (partidaAjedrez.isJaque()){
                System.out.println("Jaque!");
            }
        }else {
            System.out.println("Jaquemate!");
            System.out.println("Ganador: "+ partidaAjedrez.getTurnoJugador());
        }
    }
    /*Metodo que imprime el tablero*/
    public static void imprimeTablero(PiezasAjedrez[][] piezas) {
        for (int i = 0; i < piezas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < piezas.length; j++) {
                ImprimirPieza(piezas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
/*Metodo que imprime el tablero*/
    public static void imprimeTablero(PiezasAjedrez[][] piezas, boolean[][] posibleMovimientos) {
        for (int i = 0; i < piezas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < piezas.length; j++) {
                ImprimirPieza(piezas[i][j], posibleMovimientos[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
/*Metodo que imprime las piezas*/
    private static void ImprimirPieza(PiezasAjedrez piezas, boolean pieza) {
        if (piezas == null) {
            System.out.print("-" );
        }
        else {
            if (piezas.getJugador() == Jugadores.JUGADOR_N1) {
                System.out.print(piezas);
            }
            else {
                System.out.print(piezas);
            }
        }
        System.out.print(" ");
    }
}
