package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void crearUsuarioDB(Usuario usuario) {
        ConnectionDB connectionDB = new ConnectionDB();

        try(Connection connection = connectionDB.get_connection()) {
            try {
                String query = "INSERT INTO mensajeria.usuarios (correo, clave, nombre_completo) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, usuario.getCorreo());
                preparedStatement.setString(2, usuario.getClave());
                preparedStatement.setString(3, usuario.getNombreCompleto());
                preparedStatement.executeUpdate();
                System.out.println("Usuario Creado, ahora puedes iniciar sesión");
            } catch (SQLException e) {
                System.out.println("No se pudo crear el usuario");
                e.printStackTrace();
            }
        }catch (SQLException ex){
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
                    System.out.println("Login correcto!");
                    login.setIdUsuario(resultSet.getInt("id_usuario"));
                    login.setCorreo(resultSet.getString("correo"));
                    login.setNombreCompleto(resultSet.getString("nombre_completo"));
                } else {
                    System.out.println("Login failed");
                }
                return login;
            }catch (SQLException e){
                System.out.println("No se pudo autenticar con el servidor");
            }
        }catch (SQLException ex){
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
}
