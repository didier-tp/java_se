package tp.repository;

import tp.entity.PersonEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonRepositoryJdbc implements PersonRepository {

    static void main() {
        Connection c = getConnection();
        System.out.println("connection:" + c);
    }

    private static Connection getConnection(){
        Connection cn =null;
        try {
            ResourceBundle ressources = ResourceBundle.getBundle("paramDB") ; // paramDB.properties
            String driver = ressources.getString("driver");
            String chUrl = ressources.getString("url");
            String username = ressources.getString("username");
            String password = ressources.getString("password");
            Class.forName(driver);
            cn = DriverManager.getConnection(chUrl,username,password) ;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cn;
    }


    @Override
    public Optional<PersonEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonEntity> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        return null;
    }

    @Override
    public void update(PersonEntity p) {

    }
}
