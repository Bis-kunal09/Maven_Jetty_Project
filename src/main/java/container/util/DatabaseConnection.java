package container.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
   
    private static DatabaseConnection instance;
    private Connection connection;
    String url = "jdbc:mysql://127.0.0.1:3306/employees";
    String username = "root";
    String password = "Hinata@999";
//
//    private DatabaseConnection() throws IOException, ClassNotFoundException, SQLException {
//        
//
//       
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        connection = DriverManager.getConnection(url, username, password);
//    }

    public static DatabaseConnection getInstance() throws IOException, ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url, username, password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }

//    public Connection getConnection() {
//        return connection;
//    }
}
