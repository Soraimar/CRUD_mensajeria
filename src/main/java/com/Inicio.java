package com;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;

@RestController
public class Inicio {

    @PostMapping(value = "/usuario", consumes = "application/json", produces = "application/json" )
    public String registrarUsuario(@RequestBody Usuario usuario, HttpServletResponse response) {
        UsuarioService.crearUsuario(usuario);
        return usuario.getMensaje();
    }

//    public static void menuPrincipal() {
//        Scanner scanner = new Scanner(System.in);
//        int option = 0;
//        do {
//            System.out.println("------------------------------");
//            System.out.println("\n Mini red social \n");
//            System.out.println("1. Registrarse ");
//            System.out.println("2. Iniciar sesión");
//            System.out.println("3. Salir");
//            option = scanner.nextInt();
//            switch (option) {
//                case 1:
//                    UsuarioService.crearUsuario();
//                    break;
//                case 2:
//                    Usuario usuario = UsuarioService.iniciarSesion();
//                    if (usuario.getIdUsuario() > 0) {
//                        menuSesion(usuario);
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }while (option != 3);
//    }

    public static void menuSesion(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Aplicacion de mensajes");
            System.out.println("1. Crear mensajes");
            System.out.println("2. Listar mensajes");
            System.out.println("3. Editar mensajes");
            System.out.println("4. Eliminar mensajes");
            System.out.println("5. Ver usuarios");
            System.out.println("6. editar perfil");
            System.out.println("7. Cerrar sesión");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    MensajeService.crearMensaje(usuario);
                    break;
                case 2:
                    MensajeService.listarMensajes();
                    break;
                case 3:
                    MensajeService.editarMensaje(usuario);
                    break;
                case 4:
                    MensajeService.borrarMensaje(usuario);
                    break;
                case 5:
                    UsuarioService.listarUsuarios();
                    break;
                case 6:
                    UsuarioService.editarUsuario(usuario);
                    break;
                default:
                    break;
            }
        } while (option != 7);
    }
}

