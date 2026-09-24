package tp.dessin.dao;

import tp.dessin.Dessin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DaoDessinJdbc implements DaoDessin {

    private Dessin dessinFromResultSetRow(ResultSet rs) throws SQLException{
        return new Dessin(
                rs.getLong("id"),
                rs.getString("titre"),
                rs.getString("path"),
                rs.getString("description")
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
    public List<Dessin> findAll() {
        List<Dessin> dessins = new ArrayList<>();
        try(Connection cn = this.getConnection();
            Statement statement = cn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM dessin")) {
            while (rs.next()){
                dessins.add(this.dessinFromResultSetRow(rs));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return dessins;
    }



    @Override
    public Dessin findById(long id) {
        Dessin dessin = null;
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM dessin WHERE id=?")){
                pst.setLong(1,id);
                try(ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        dessin = this.dessinFromResultSetRow(rs);
                    }
                }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return dessin;
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
    public Dessin insert(Dessin d) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO dessin(titre,path,description) VALUES(?,?,?)",
                                                          Statement.RETURN_GENERATED_KEYS ))
        {
            pst.setString(1, d.getTitre());
            pst.setString(2, d.getPath());
            pst.setString(3, d.getDescription());
            pst.executeUpdate(); //avec auto_increment mysql sur colonne id
            Long pk = recupValeurAutoIncrPk(pst);
            d.setId(pk);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return d;
    }


   
    @Override
    public Dessin update(Dessin d) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("UPDATE dessin SET titre=?,path=?,description=? WHERE id=?"))
        {
            pst.setString(1, d.getTitre());
            pst.setString(2, d.getPath());
            pst.setString(3, d.getDescription());
            pst.setLong(4,d.getId());
            pst.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        //Automatic close via try(withResource)
        //NB: dans projet plus élaborer cn.close() libère la connexion dans un pool de connexions
        return d;
    }
    
    @Override
    public void deleteById(long id) {
        try(Connection cn = this.getConnection();
            PreparedStatement pst = cn.prepareStatement("DELETE FROM dessin  WHERE id=?"))
        {
            pst.setLong(1,id);
            pst.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
