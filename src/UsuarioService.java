package src;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class UsuarioService {

    public static void crearUsuario(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica tu nombre completo ");
        String nombreCompleto = scanner.nextLine();
        System.out.println("Indica tu correo ");
        String correo = scanner.nextLine();
        System.out.println("Indica tu clave ");
        String clave = scanner.nextLine();
        clave = cifrarMD5(clave);

        Usuario registro = new Usuario(correo, clave, nombreCompleto);
        UsuarioDAO.crearUsuarioDB(registro);
    }

    public static String cifrarMD5(String clave){
        return DigestUtils.md5Hex(clave);
    }

    public static Usuario iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indica tu correo ");
        String correo = scanner.nextLine();
        System.out.println("Indicar tu clave ");
        String clave = scanner.nextLine();
        clave = cifrarMD5(clave);
        Usuario login = new Usuario(correo,clave);
        Usuario resultado = UsuarioDAO.iniciarSesionDB(login);
        return resultado;
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
        clave = cifrarMD5(clave);

        Usuario registro = new Usuario(usuario.getIdUsuario(), correo, clave, nombre_completo);
        UsuarioDAO.editarUsuarioDB(registro);
    }
}
