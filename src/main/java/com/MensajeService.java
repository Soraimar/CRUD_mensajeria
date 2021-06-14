package com;

import java.util.Scanner;

public class MensajeService {

    public static void crearMensaje(UsuarioDTO usuarioDTO) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Escribe tu mensajeDTO, máx 280 caracteres ");
        String elMensaje = scanner.nextLine();

        MensajeDTO mensajeDTO = new MensajeDTO(elMensaje, usuarioDTO.getIdUsuario());
        MensajesDAO.createMensajeBD(mensajeDTO);
    }

    public static void listarMensajes() {
        MensajesDAO.listarMensajesDB();
    }

    public static void editarMensaje(UsuarioDTO usuarioDTO){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa tu nuevo mensaje: ");
        String newMensaje = scanner.nextLine();

        System.out.println("Ingresa ID de mensaje que deseas editar: ");
        int idMensaje = scanner.nextInt();

        MensajeDTO actualizacion = new MensajeDTO(newMensaje, idMensaje , usuarioDTO.getIdUsuario());

        MensajesDAO.updateMensajeDB(actualizacion);
    }

    public static void borrarMensaje(UsuarioDTO usuarioDTO) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa ID de mensajeDTO que deseas borrar: ");
        int idMensaje = scanner.nextInt();

        MensajeDTO mensajeDTO = new MensajeDTO(idMensaje, usuarioDTO.getIdUsuario());
        MensajesDAO.deleteMensajeDB(mensajeDTO);
    }

}
