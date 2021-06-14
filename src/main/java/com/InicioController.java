package com;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

// habilitar esta clase como un controlador REST y que pueda interceptar peticiones al servidor
@RestController
public class InicioController {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/usuario")
    public String registrarUsuario(@RequestBody UsuarioDTO usuario, HttpServletResponse response) {
        //usuario.setPassword(bCryptPasswordEncoder().encode(usuario.getPassword()));
        UsuarioService.crearUsuario(usuario);
        return usuario.getMensaje();
    }

    @PostMapping(value = "/login")
    public UsuarioDTO login(@RequestBody UsuarioDTO usuario, HttpServletResponse response){
        UsuarioService.iniciarSesion(usuario);

        if (usuario.isLogin()){
            String token = getJWTToken(usuario.getUserName());
            usuario.setToken(token);
        }
        return usuario;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

//debo consultar si lo comentado es necesario ojo funciona sin eso
//    @PostMapping(value = "/mensaje") //,consumes = "application/json", produces = "application/json")
//    public String crearMensaje(@RequestBody UsuarioDTO usuario, HttpServletResponse response){
//        MensajeService.crearMensaje(usuario);
//        return usuario.getMensaje(); //esto debe confirmar el token
//    }
//
//    @GetMapping(value = "/mensajes" , consumes = "application/json" ,produces = "application/json")
//    public void consultarMensajes(HttpServletResponse response ){
//        MensajeService.listarMensajes();
//        //falta crear objeto de respuesta y que se valide el token
//    }
//    public static void menuSesion(UsuarioDTO usuario){
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

