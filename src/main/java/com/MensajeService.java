package com;

import java.util.Scanner;

public class MensajeService {

    public static void crearMensaje(Usuario usuario) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Escribe tu mensaje, máx 280 caracteres ");
        String elMensaje = scanner.nextLine();

        Mensaje mensaje = new Mensaje(elMensaje, usuario.getIdUsuario());
        MensajesDAO.createMensajeBD(mensaje);
    }

    public static void listarMensajes() {
        MensajesDAO.listarMensajesDB();
    }

    public static void editarMensaje(Usuario usuario){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa tu nuevo mensaje: ");
        String newMensaje = scanner.nextLine();

        System.out.println("Ingresa ID de mensaje que deseas editar: ");
        int idMensaje = scanner.nextInt();

        Mensaje actualizacion = new Mensaje(newMensaje, idMensaje ,usuario.getIdUsuario());

        MensajesDAO.updateMensajeDB(actualizacion);
    }

    public static void borrarMensaje(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa ID de mensaje que deseas borrar: ");
        int idMensaje = scanner.nextInt();

        Mensaje mensaje = new Mensaje(idMensaje,usuario.getIdUsuario());
        MensajesDAO.deleteMensajeDB(mensaje);
    }

}
