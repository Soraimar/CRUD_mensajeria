package src;

import java.util.Scanner;

public class MensajeService {

    public static void crearMensaje() {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Escribe tu mensaje: ");
        String mensaje = scanner.nextLine();

        System.out.println("Tu nombre: ");
        String nombre = scanner.nextLine();

        Mensaje registro = new Mensaje();
        registro.setMensaje(mensaje);
        registro.setAutorMensaje(nombre);

        MensajesDAO.createMensajeBD(registro);
    }

    public static void listarMensajes() {
        MensajesDAO.listarMensajesDB();
    }

    public static void editarMensaje(){
        Scanner scanner = new Scanner(System.in);
        Mensaje actualizacion = new Mensaje();

        System.out.println("Ingresa tu nevo mensaje: ");
        actualizacion.setMensaje(scanner.nextLine());

        System.out.println("Ingresa ID de mensaje que deseas editar: ");
        actualizacion.setIdMensaje(scanner.nextInt());

        MensajesDAO.updateMensajeDB(actualizacion);
    }

    public static void borrarMensaje() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa ID de mensaje que deseas borrar: ");
        int idMensaje = scanner.nextInt();

        MensajesDAO.deleteMensajeDB(idMensaje);
    }

}
