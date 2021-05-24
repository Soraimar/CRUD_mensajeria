package src;

import java.util.Scanner;

public class Inicio {

    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        int option;
        do{
            System.out.println("--------------------");
            System.out.println("Aplicacion de mensajes");
            System.out.println("1. Crear mensajes");
            System.out.println("2. listar mensajes");
            System.out.println("3. editar mensajes");
            System.out.println("4. eliminar mensajes");
            System.out.println("5. Salir ");
            option= scanner.nextInt();

            switch(option){
                case 1:
                    MensajeService.crearMensaje();
                    break;
                case 2:
                    MensajeService.listarMensajes();
                    break;
                case 3:
                    MensajeService.editarMensaje();
                    break;
                case 4:
                    MensajeService.borrarMensaje();
                    break;
                default:
                    break;
            }
        }while(option!=5);
    }
}

