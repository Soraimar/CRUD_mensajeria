package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void crearUsuarioDB(Usuario usuario) {
        ConnectionDB connectionDB = new ConnectionDB();

        if (existeCorreoAsociado(usuario.getCorreo())){
            usuario.setMensaje("Correo especificado cuenta con usuario");
            return;
        }

        try(Connection connection = connectionDB.get_connection()) {
            try {
                String query = "INSERT INTO mensajeria.usuarios (correo, clave, nombre_completo) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usuario.getCorreo());
                preparedStatement.setString(2, usuario.getClave());
                preparedStatement.setString(3, usuario.getNombreCompleto());
                preparedStatement.executeUpdate();
                usuario.setMensaje("Usuario Creado.");
            } catch (SQLException e) {
                usuario.setMensaje("No se pudo crear el usuario.");
                e.printStackTrace();
            }
        }catch (SQLException ex){
            usuario.setMensaje("Ha ocurrido un error");
            ex.printStackTrace();
        }
    }

    public static Usuario iniciarSesionDB(Usuario usuario) {
        ConnectionDB connectionDB = new ConnectionDB();
        try(Connection connection= connectionDB.get_connection()){

            try {
                String query = "select * from mensajeria.usuarios where correo = ? and clave = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usuario.getCorreo());
                preparedStatement.setString(2, usuario.getClave());
                ResultSet resultSet = preparedStatement.executeQuery();

                Usuario login = new Usuario();

                if (resultSet.next()) {
                    usuario.setMensaje("Login correcto!");
                    login.setIdUsuario(resultSet.getInt("id_usuario"));
                    login.setCorreo(resultSet.getString("correo"));
                    login.setNombreCompleto(resultSet.getString("nombre_completo"));
                } else {
                    usuario.setMensaje("Login failed");
                }
                return login;
            }catch (SQLException e){
                usuario.setMensaje("No se pudo autenticar con el servidor");
            }
        }catch (SQLException ex){
            usuario.setMensaje("Ocurrio un error");
            ex.printStackTrace();
        }
        //si no se logra iniciar sesión devolvemos un objeto vacío
        return null;
    }

    public static void editarUsuarioDB(Usuario usuario){
        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.get_connection()) {

            try {
                String query="update mensajeria.usuarios set correo = ?, clave = ?, nombre_completo = ? where id_usuario = ?";
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, usuario.getCorreo());
                preparedStatement.setString(2, usuario.getClave());
                preparedStatement.setString(3, usuario.getNombreCompleto());
                preparedStatement.setInt(4, usuario.getIdUsuario());
                preparedStatement.executeUpdate();
                System.out.println("usuario actualizado");

            } catch (SQLException e) {
                System.out.println("No se pudo actualizar el usuario");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void leerUsuariosDB(){
        ConnectionDB connectionDB = new ConnectionDB();
        try(Connection connection = connectionDB.get_connection()){
            try {
                String query="select * from mensajeria.usuarios";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    System.out.print("\n [ID: "  + resultSet.getString("id_usuario")      +"  | ");
                    System.out.print("Correo: -" + resultSet.getString("correo")          + " | ");
                    System.out.print("Nombre: "  + resultSet.getString("nombre_completo") + " ] ");
                }
            } catch (SQLException e) {
                System.out.println("No se pudo listar los usuarios \n");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static boolean existeCorreoAsociado(String correo){
        ConnectionDB connectionDB = new ConnectionDB();
        int resultado = 0;

        try(Connection connection = connectionDB.get_connection()){
            try {
                String query= "select count(*) as correo from mensajeria.usuarios where correo = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, correo);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    resultado = resultSet.getInt("correo");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return resultado != 0;
    }
}
