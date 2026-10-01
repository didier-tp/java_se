package tp;

import java.sql.*;
import java.util.ResourceBundle;

public class MyApp3 {
    static void main() {
        test_select();
    }

    public static void test_select(){
        try {
            Connection cn = etablir_connexion_db();
            Statement statement=cn.createStatement();
            String chRequeteSql="SELECT * FROM personne";
            ResultSet rs = statement.executeQuery(chRequeteSql);
            while(rs.next()){
               String nom = rs.getString("nom");
               Integer age = (Integer) rs.getObject("age");
                System.out.printf("nom=%s age=%d\n",nom,age);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    static Connection etablir_connexion_db(){
        //page 130
        //avec try/catch (Exception ex){ ... }
        Connection cn =null;
        try {
            ResourceBundle ressources = ResourceBundle.getBundle("db") ; // db.properties
            String driver = ressources.getString("driver");
            String chUrl = ressources.getString("url");
            String username = ressources.getString("username");
            String password = ressources.getString("password");
            Class.forName(driver);
            cn = DriverManager.getConnection(chUrl,username,password) ;
            System.out.println("cn="+cn.toString());

        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.err.println("erreur de driver jdbc :" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.err.println("erreur de connection :" + e.getMessage());
            e.printStackTrace();
        }
        return cn;
    }


}
