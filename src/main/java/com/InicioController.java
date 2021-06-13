package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

// habilitar esta clase como un controlador REST y que pueda interceptar peticiones al servidor
@RestController
public class InicioController {

    @PostMapping(value = "/usuario", consumes = "application/json", produces = "application/json" )
    public String registrarUsuario(@RequestBody Usuario usuario, HttpServletResponse response) {
        UsuarioService.crearUsuario(usuario);
        return usuario.getMensaje();
    }

    //debo consultar si lo comentado es necesario ojo funciona sin eso
    @PostMapping(value = "/users") //,consumes = "application/json", produces = "application/json")
    public String login(@RequestBody Usuario usuario, HttpServletResponse response){
        UsuarioService.iniciarSesion(usuario);
        return usuario.getMensaje(); //esto debe tener un token
    }

    @PostMapping(value = "/mensaje")
    public String crearMensaje(@RequestBody Usuario usuario, HttpServletResponse response){
        MensajeService.crearMensaje(usuario);
        return usuario.getMensaje(); //esto debe confirmar el token
    }

    @GetMapping(value = "/mensajes" , consumes = "application/json" ,produces = "application/json")
    public void consultarMensajes(HttpServletResponse response ){
        MensajeService.listarMensajes();
        //falta crear objeto de respuesta y que se valide el token
    }




//    public static void menuSesion(Usuario usuario){
//        Scanner scanner = new Scanner(System.in);
//        int option = 0;
//        do {
//            System.out.println("Aplicacion de mensajes");
//            System.out.println("1. Crear mensajes");
//            System.out.println("2. Listar mensajes");
//            System.out.println("3. Editar mensajes");
//            System.out.println("4. Eliminar mensajes");
//            System.out.println("5. Ver usuarios");
//            System.out.println("6. editar perfil");
//            System.out.println("7. Cerrar sesión");
//            option = scanner.nextInt();
//            switch (option) {
////                case 1:
////                    MensajeService.crearMensaje(usuario);
////                    break;
////                case 2:
////                    MensajeService.listarMensajes();
////                    break;
//                case 3:
//                    MensajeService.editarMensaje(usuario);
//                    break;
//                case 4:
//                    MensajeService.borrarMensaje(usuario);
//                    break;
//                case 5:
//                    UsuarioService.listarUsuarios();
//                    break;
//                case 6:
//                    UsuarioService.editarUsuario(usuario);
//                    break;
//                default:
//                    break;
//            }
//        } while (option != 7);
//    }
}

