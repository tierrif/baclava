package io.github.hotlava03.baclava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    public Database(String host, String pw, String usr){
        try{
            connection = DriverManager.getConnection(host,usr,pw);
        }catch(SQLException e){
            System.out.println("[Database] An error has occured while getting connection with host "+host+", username "+usr+":\n"+e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void close(){
        try{
            connection.close();
        }catch(SQLException e){
            System.out.println("[Database] An error has occured while closing connection with database:\n"+e);
        }
    }
}
