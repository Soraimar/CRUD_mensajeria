package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensajesDAO {

    public static void createMensajeBD(Mensaje mensaje){
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;
        String query;

        try (Connection connection= connectionDB.get_connection()){
            try{
                query = "insert into mensajes (mensaje, autor_mensaje) values(?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, mensaje.getMensaje());
                preparedStatement.setString(2, mensaje.getAutorMensaje());
                preparedStatement.executeUpdate();
                System.out.println("Mensaje creado. ");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }catch(SQLException e){
            System.out.println("No pudo crearse el mensaje.");
            e.printStackTrace();
        }
    }

    public static void listarMensajesDB() {
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try(Connection connection= connectionDB.get_connection()) {

            String query= "select * from mensajeria.mensajes";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println("*********************************************************************************");
                System.out.println("ID: "+ resultSet.getInt("id_mensaje"));
                System.out.println("Mensaje: " + resultSet.getString("mensaje"));
                System.out.println("Autor: " + resultSet.getString("autor_mensaje"));
                System.out.println("Fecha de creación: " + resultSet.getString("fecha_creacion"));
            }

        }catch (SQLException e){
            System.out.println("Ocurrio un error no se pudieron recuperar los mensajes");
            e.printStackTrace();
        }

    }

    public static void updateMensajeDB(Mensaje mensaje) {
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;

        try(Connection connection= connectionDB.get_connection()){

            String query = "update mensajeria.mensajes set mensaje = ? where id_mensaje = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mensaje.getMensaje());
            preparedStatement.setInt(2, mensaje.getIdMensaje());
            preparedStatement.executeUpdate();
            System.out.println("Mensaje Actualizado.");

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Mensaje no pudo ser actualizado.");
        }
    }

    public static void deleteMensajeDB(int idMensaje) {

        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;

        try(Connection connection= connectionDB.get_connection()){

            String query = "delete from mensajeria.mensajes where id_mensaje = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idMensaje);
            preparedStatement.executeUpdate();
            System.out.println("Mensaje con ID "+ idMensaje + " fue eliminado.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo eliminar mensaje.");
        }

    }
}
