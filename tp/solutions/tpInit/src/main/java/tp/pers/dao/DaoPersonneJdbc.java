package tp.pers.dao;

import tp.pers.PersonEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DaoPersonneJdbc implements DaoPersonne{

    private PersonEntity personEntityFromResultSetRow(ResultSet rs) throws SQLException{
        return new PersonEntity(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getInt("age"),
                rs.getDouble("poids")
        );
    }

    private Connection getConnection(){
        Connection cn = null;
        try {
            ResourceBundle ressources = ResourceBundle.getBundle("db") ; // db.properties
            String driver = ressources.getString("driver");
            String chUrl = ressources.getString("url");
            String username = ressources.getString("username");
            String password = ressources.getString("password");
            Class.forName(driver); //charger en mémoire classe principale du driver/pilote JDBC
            cn = DriverManager.getConnection(chUrl,username,password) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //driver indisponible (oubli dans pom.xml)
        } catch (SQLException e) {
            e.printStackTrace(); //erreur de connexion (serveur pas démarré , pb username/password)
        }
        return cn;
    }

    @Override
    public List<PersonEntity> findAll() {
        List<PersonEntity> personnes = new ArrayList<>();
        try(Connection cn = this.getConnection();
            Statement statement = cn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM personne")) {
            while (rs.next()){
                personnes.add(this.personEntityFromResultSetRow(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return personnes;
    }



    @Override
    public PersonEntity findById(long id) {
        PersonEntity personne = null;
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM personne WHERE id=?")){
                pst.setLong(1,id);
                try(ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        personne = this.personEntityFromResultSetRow(rs);
                    }
                }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return personne;
    }

    Long recupValeurAutoIncrPk(PreparedStatement pst){
        Long pk=null;
        try {
            ResultSet rsKeys = pst.getGeneratedKeys();
            if(rsKeys.next()){ pk= rsKeys.getLong(1); }
        } catch (SQLException e) { e.printStackTrace(); }
        return pk;
    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO personne(nom,age,poids) VALUES(?,?,?)",
                                                          Statement.RETURN_GENERATED_KEYS ))
        {
            pst.setString(1, p.getNom());
            pst.setInt(2, p.getAge());
            pst.setDouble(3, p.getPoids());
            pst.executeUpdate(); //avec auto_increment mysql sur colonne id
            Long pk = recupValeurAutoIncrPk(pst);
            p.setId(pk);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return p;
    }


   
    @Override
    public PersonEntity update(PersonEntity p) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("UPDATE personne SET nom=?,age=?,poids=? WHERE id=?"))
        {
            pst.setString(1, p.getNom());
            pst.setInt(2, p.getAge());
            pst.setDouble(3, p.getPoids());
            pst.setLong(4,p.getId());
            pst.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return p;
    }
    
    @Override
    public void deleteById(long id) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("DELETE FROM personne  WHERE id=?"))
        {
            pst.setLong(1,id);
            pst.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
