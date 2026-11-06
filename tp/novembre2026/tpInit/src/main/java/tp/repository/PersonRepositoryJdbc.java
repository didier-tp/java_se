package tp.repository;

import tp.entity.PersonEntity;

import java.sql.*;
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
        Optional<PersonEntity> personEntityOptional = Optional.empty();
        try(Connection cn = getConnection()){
            PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM personne WHERE id=?");
            pstmt.setLong(1,id); //1 pour ? numéro1
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                personEntityOptional = Optional.of(new PersonEntity(rs.getString("nom"),
                                                                    rs.getInt("age"),
                                                                    rs.getDouble("poids"),
                                                                    rs.getLong("id") )
                                                  );
            }
            rs.close();
            pstmt.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //automatic cn.close via automatic finally
        return personEntityOptional;
    }

    @Override
    public List<PersonEntity> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    private Long recupValeurAutoIncrPk(PreparedStatement pst){
        Long pk=null;
        try {
            ResultSet rsKeys = pst.getGeneratedKeys();
            if(rsKeys.next()){ pk= rsKeys.getLong(1) ; /*rsKeys.getInt(1);*/}
        } catch (SQLException e) {e.printStackTrace();}
        return pk;
    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        try(Connection cn = getConnection()){
            PreparedStatement pstmt = cn.prepareStatement("INSERT INTO personne(nom,age,poids) VALUES(?,?,?)",
                                                          Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,p.getNom()); //1 pour ? numéro1
            pstmt.setInt(2,p.getAge());
            pstmt.setDouble(3,p.getPoids());
            pstmt.executeUpdate();//avec auto increment postgres sur colonne id de type SERIAL
            long pk = this.recupValeurAutoIncrPk(pstmt);
            p.setId(pk);
            pstmt.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //automatic cn.close via automatic finally
        return p; //with id auto incrémenté (plus null)
    }

    @Override
    public void update(PersonEntity p) {

    }
}
