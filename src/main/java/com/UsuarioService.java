package com;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class UsuarioService {

    public static void crearUsuario(Usuario usuario){
        usuario.setClave(cifrarClave(usuario.getClave()));
        UsuarioDAO.crearUsuarioDB(usuario);
    }

    public static String cifrarClave(String clave){
        return DigestUtils.md5Hex(clave);  // no se recomienda, se debe usar modernos como Hash basada en el cifrado Blowfish (BCrypt)
    }

    public static void iniciarSesion(Usuario usuario) {
        usuario.setClave(cifrarClave(usuario.getClave()));
        UsuarioDAO.iniciarSesionDB(usuario);
    }

    public static void listarUsuarios() {
        UsuarioDAO.leerUsuariosDB();
    }

    public static void editarUsuario(Usuario usuario) {
        //instanciamos la clase scanner para leer datos
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica tu nombre completo");
        String nombre_completo = scanner.nextLine();
        System.out.println("Indica tu correo");
        String correo = scanner.nextLine();
        System.out.println("Indica tu clave");
        String clave = scanner.nextLine();
        clave = cifrarClave(clave);

        Usuario registro = new Usuario(usuario.getIdUsuario(), correo, clave, nombre_completo);
        UsuarioDAO.editarUsuarioDB(registro);
    }
}
