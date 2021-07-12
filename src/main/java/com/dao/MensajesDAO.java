package com.dao;

import com.dto.MensajeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensajesDAO {

    public static void createMensajeBD(MensajeDTO mensajeDTO){
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;
        String query;

        try (Connection connection= connectionDB.get_connection()){
            try{
                query = "insert into mensajes (mensaje, id_usuario) values(?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, mensajeDTO.getMensaje());
                preparedStatement.setInt(2, mensajeDTO.getIdUsuario());
                preparedStatement.executeUpdate();
                System.out.println("MensajeDTO creado. ");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }catch(SQLException e){
            System.out.println("No pudo crearesultSete el mensajeDTO.");
            e.printStackTrace();
        }
    }

    public static void listarMensajesDB() {
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try(Connection connection= connectionDB.get_connection()) {

            String query=   "select m.id_mensaje,m.mensaje, m.fecha_creacion, u.nombre_completo " +
                            "from mensajeria.mensajes m " +
                            "join mensajeria.usuarios u on m.id_usuario=u.id_usuario";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.print("\n[ID: "    +resultSet.getInt("id_mensaje")         +" | ");
                System.out.print("mensaje: -" +resultSet.getString("mensaje")         +" | ");
                System.out.print("fecha: -"   +resultSet.getString("fecha_creacion")  +" | ");
                System.out.print("Autor: "    +resultSet.getString("nombre_completo") +" ] ");
            }

        }catch (SQLException e){
            System.out.println("Ocurrio un error no se pudieron recuperar los mensajes");
            e.printStackTrace();
        }

    }

    public static void updateMensajeDB(MensajeDTO mensajeDTO) {
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;

        try(Connection connection= connectionDB.get_connection()){
             try{
                if (!esElAutorDelMensaje(mensajeDTO.getIdMensaje(), mensajeDTO.getIdUsuario())){
                    return;
                }

                String query = "update mensajeria.mensajes set mensaje = ? where id_mensaje = ? and id_usuario = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, mensajeDTO.getMensaje());
                preparedStatement.setInt(2, mensajeDTO.getIdMensaje());
                preparedStatement.setInt(3, mensajeDTO.getIdUsuario());
                preparedStatement.executeUpdate();
                System.out.println("MensajeDTO Actualizado.");

             }catch (SQLException ex){
                 System.out.println("ID de mensajeDTO no existe o no es el autor.");
             }
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
    public static boolean esElAutorDelMensaje(int idMensaje, int idUsuario){
        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;
        int resultado = 0;

        try(Connection connection= connectionDB.get_connection()){
            try {
                String query = "select count(*) from mensajeria.mensajes where id_mensaje = ? and id_usuario = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idMensaje);
                preparedStatement.setInt(2, idUsuario);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultado = resultSet.getInt("autor");

            }catch (SQLException ex){
                ex.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        boolean b = resultado > 0 ? true : false;
        return b;
    }

    public static void deleteMensajeDB(MensajeDTO mensajeDTO) {

        ConnectionDB connectionDB = new ConnectionDB();
        PreparedStatement preparedStatement;

        try(Connection connection= connectionDB.get_connection()){
            try {
                if (!esElAutorDelMensaje(mensajeDTO.getIdMensaje(), mensajeDTO.getIdUsuario())){
                    return;
                }
                String query = "delete from mensajeria.mensajes where id_mensaje = ? and id_usuario = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, mensajeDTO.getIdMensaje());
                preparedStatement.setInt(2, mensajeDTO.getIdUsuario());
                preparedStatement.executeUpdate();
                System.out.println("MensajeDTO con ID "+ mensajeDTO.getIdMensaje() + " fue eliminado.");

            }catch (SQLException ex){
                System.out.println("\n No se pudo eliminar mensajeDTO. \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo eliminar mensajeDTO.");
        }

    }
}
