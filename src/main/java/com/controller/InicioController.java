package com.controller;

import com.service.UsuarioService;
import com.dto.UsuarioDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;

// habilitar esta clase como un controlador REST y que pueda interceptar peticiones al servidor
@RestController
public class InicioController {


    @PostMapping(value = "/usuario")
    public String registrarUsuario(@RequestHeader("Authorization") String authorization,
                                   @RequestBody UsuarioDTO usuario, HttpServletResponse response) {

        if (validateJWTToken(authorization)) {
            UsuarioService.crearUsuario(usuario);
            return usuario.getMensaje();
        } else {
            return "token invalido";
        }
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UsuarioDTO usuario, HttpServletResponse response){
        UsuarioService.iniciarSesion(usuario);

        if (usuario.isLogin()){
            String token = createJWTToken(usuario.getUserName());
            usuario.setToken(token);
            return usuario.getMensaje();
        }
        return usuario.getMensaje();
    }

    private String createJWTToken(String username) {
        final String SECRET_KEY = "mySecretKey";
        //SecretKey privateKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKey privateKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(username)
                .signWith(privateKey)
                .compact();

    }

    private boolean validateJWTToken(String authorization) {

        final String SECRET_KEY = "mySecretKey";
        //SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKey privateKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(privateKey)
                    .build()
                    .parseClaimsJws(authorization);

            return true;

        }catch (JwtException ex){
            System.out.println(ex);
            return false;
        }
    }

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

