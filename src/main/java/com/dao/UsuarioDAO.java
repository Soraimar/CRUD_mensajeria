package com.dao;

import com.dto.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void crearUsuarioDB(UsuarioDTO usuarioDTO) {
        ConnectionDB connectionDB = new ConnectionDB();

        if (existeCorreoAsociado(usuarioDTO.getCorreo())){
            usuarioDTO.setMensaje("Correo especificado cuenta con usuarioDTO");
            return;
        }

        try(Connection connection = connectionDB.get_connection()) {
            try {
                String query = "INSERT INTO mensajeria.usuarios (user_name ,correo, nombre_completo, password) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usuarioDTO.getUserName());
                preparedStatement.setString(2, usuarioDTO.getCorreo());
                preparedStatement.setString(3, usuarioDTO.getNombreCompleto());
                preparedStatement.setString(4, usuarioDTO.getPassword());

                preparedStatement.executeUpdate();
                usuarioDTO.setMensaje("UsuarioDTO Creado.");
            } catch (SQLException e) {
                usuarioDTO.setMensaje("No se pudo crear el usuarioDTO.");
                e.printStackTrace();
            }
        }catch (SQLException ex){
            usuarioDTO.setMensaje("Ha ocurrido un error");
            ex.printStackTrace();
        }
    }

    public static UsuarioDTO iniciarSesionDB(UsuarioDTO usuarioDTO) {
        ConnectionDB connectionDB = new ConnectionDB();
        try(Connection connection= connectionDB.get_connection()){

            try {
                String query = "select * from mensajeria.usuarios where user_name = ? and password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usuarioDTO.getUserName());
                preparedStatement.setString(2, usuarioDTO.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();

                UsuarioDTO login = new UsuarioDTO();

                if (resultSet.next()) {
                    usuarioDTO.setLogin(true);
                    usuarioDTO.setMensaje("Login correcto!");
                    login.setIdUsuario(resultSet.getInt("id_usuario"));
                    login.setCorreo(resultSet.getString("correo"));
                    login.setNombreCompleto(resultSet.getString("nombre_completo"));
                } else {
                    usuarioDTO.setLogin(false);
                    usuarioDTO.setMensaje("Login failed");
                }
                return login;
            }catch (SQLException e){
                usuarioDTO.setMensaje("No se pudo autenticar con el servidor");
            }
        }catch (SQLException ex){
            usuarioDTO.setMensaje("Ocurrio un error");
            ex.printStackTrace();
        }
        //si no se logra iniciar sesión devolvemos un objeto vacío
        return null;
    }

    public static void editarUsuarioDB(UsuarioDTO usuarioDTO){
        ConnectionDB connectionDB = new ConnectionDB();
        try (Connection connection = connectionDB.get_connection()) {

            try {
                String query="update mensajeria.usuarios set correo = ?, clave = ?, nombre_completo = ? where id_usuario = ?";
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, usuarioDTO.getCorreo());
                preparedStatement.setString(2, usuarioDTO.getPassword());
                preparedStatement.setString(3, usuarioDTO.getNombreCompleto());
                preparedStatement.setInt(4, usuarioDTO.getIdUsuario());
                preparedStatement.executeUpdate();
                System.out.println("usuarioDTO actualizado");

            } catch (SQLException e) {
                System.out.println("No se pudo actualizar el usuarioDTO");
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
