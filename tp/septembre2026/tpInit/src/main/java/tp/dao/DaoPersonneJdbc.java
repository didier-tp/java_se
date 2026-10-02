package tp.dao;

import tp.PersonEntity;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DaoPersonneJdbc implements DaoPersonne{

    @Override
    public PersonEntity findById(long id) {
        PersonEntity person=null;
        try(Connection cn=  etablir_connexion_db()){
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM personne WHERE id=?");
            pst.setLong(1,id); //donner une valeur au paramétre ? numero 1
            ResultSet rs =pst.executeQuery();
            if(rs.next()){
                person = new PersonEntity(id ,
                        rs.getString("nom") ,
                        rs.getInt("age"),
                        rs.getDouble("poids"));
            }
            rs.close();
            pst.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //cn.close() automatique
        return person;
    }

    @Override
    public List<PersonEntity> findAll() {
        return List.of();
    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        return null;
    }

    @Override
    public PersonEntity update(PersonEntity p) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    private static Connection etablir_connexion_db(){
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
