package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public static Connection Conector(){
        String dbName="walletdatabase";
        String userName="root";
        String password="";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
            return connection;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

    }
}
