package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public Connection get_connection(){
        Connection connection =  null;
        String url = "jdbc:mysql://localhost:3306/mensajeria";
        try {
            connection = DriverManager.getConnection(url,"root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
