package tp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyApp3 {
    static void main() {
        tester_connexion_db();
    }

    static void tester_connexion_db(){
        //page 130
        //avec try/catch (Exception ex){ ... }

        try {
            ResourceBundle ressources = ResourceBundle.getBundle("db") ; // db.properties
            String driver = ressources.getString("driver");
            String chUrl = ressources.getString("url");
            String username = ressources.getString("username");
            String password = ressources.getString("password");
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(chUrl,username,password) ;
            System.out.println("cn="+cn.toString());
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.err.println("erreur de driver jdbc :" + e.getMessage());
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.err.println("erreur de connection :" + e.getMessage());
        }

    }
}
