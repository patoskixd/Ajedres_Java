package Ajedrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioJuego {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        PartidaAjedrez chessMatch = new PartidaAjedrez();
        System.out.println("Intrucciones: "+"\n" +"Para seleccionar la posicion de la pieza deberas escribir desde la letra: a hasta la h continuamente de esto un numero: del  1 hasta el 8");

            while (!chessMatch.isJaquemate()){  /*Creamos un ciclo que se imprimira siempre y cuando no se encuentre en jaquemate */
                try{              
                    VistasTablero.imprimePartido(chessMatch); /*Nos imprime el tablero*/
                    System.out.println();
                    System.out.println("Selecciones posición de pieza: ");                   
                    PosicionAjedrez source = VistasTablero.leerPosicionAjedrez(sc); /*En este metodo encontrado en vistas tablas nos lee la posicion ingresada*/
                    System.out.println();
                    System.out.println("Seleccione Posición de destino: ");            
                    PosicionAjedrez target  = VistasTablero.leerPosicionAjedrez(sc);/*En este metodo encontrado en vistas tablas nos lee la posicion ingresada*/
                    chessMatch.realizaMovimiento(source, target);/*Comprueba si es posible realizar moviemiento*/
                    System.out.println("\n");
                    if(chessMatch.getPromocion() != null){ /*en caso de que el peon llegue fila del rey del oponente tendra opciones elegir las siguientes piezas*/
                        System.out.println("Digite la letra de la pieza escogida en MAYUSCULA: (R/C/T/A)");
                        String type = sc.nextLine();
                        chessMatch.reemplazarPiezaPromocion(type);/*Remplazara las piezas seleccionada anteriormente*/
                    }
                }
                catch (AjedrezExcepciones | InputMismatchException e) { /*en caso de ingresar un dato erroneo nos imprimira el error */
                    System.out.println(e.getMessage());
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.nextLine();
                }
            }
    }
}
