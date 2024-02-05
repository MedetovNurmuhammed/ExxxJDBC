package java12.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconfig {
    private  final static String url="jdbc:postgresql://localhost:5432/jdbc";
    private final static String username = "postgres";
    private final static String password = "1234";
    public static Connection getconnection(){
        Connection connection = null ;
        try {
            connection =  DriverManager.getConnection(url,username,password);
            System.out.println("Succes");
        } catch (SQLException e){
            System.out.println(e.getMessage());}
        return connection;
    }

}

