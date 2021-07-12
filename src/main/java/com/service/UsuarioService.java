package com.service;

import com.dao.UsuarioDAO;
import com.dto.UsuarioDTO;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class UsuarioService {

    public static void crearUsuario(UsuarioDTO usuarioDTO){
        usuarioDTO.setPassword(cifrarClave(usuarioDTO.getPassword()));
        UsuarioDAO.crearUsuarioDB(usuarioDTO);
    }

    public static String cifrarClave(String clave){
        return DigestUtils.md5Hex(clave);  // no se recomienda, se debe usar modernos como Hash basada en el cifrado Blowfish (BCrypt)
    }

    public static void iniciarSesion(UsuarioDTO usuarioDTO) {
        usuarioDTO.setPassword(cifrarClave(usuarioDTO.getPassword()));
        UsuarioDAO.iniciarSesionDB(usuarioDTO);
    }

    public static void listarUsuarios() {
        UsuarioDAO.leerUsuariosDB();
    }

    public static void editarUsuario(UsuarioDTO usuarioDTO) {
        //instanciamos la clase scanner para leer datos
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica tu nombre completo");
        String nombre_completo = scanner.nextLine();
        System.out.println("Indica tu correo");
        String correo = scanner.nextLine();
        System.out.println("Indica tu clave");
        String clave = scanner.nextLine();
        clave = cifrarClave(clave); // debo cambiar cuando se active este endpoint

        //UsuarioDTO registro = new UsuarioDTO(usuarioDTO.getIdUsuario(), correo, clave, nombre_completo);
        //UsuarioDAO.editarUsuarioDB(registro);
    }
}
