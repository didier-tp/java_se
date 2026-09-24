package tp.dessin.dao;

import tp.dessin.Dessin;

import java.util.List;


/*
 * DAO = Data Access Object
 * avec methodes CRUD (Create : INSERT INTO
 *                    Rechercher : SELECT
 *                    Update,
 *                    Delete )
 */

public interface DaoDessin {
    public Dessin findById(long id);

    public List<Dessin> findAll();

    public Dessin insert(Dessin p); //en retour Personne avec id auto incrémenté

    public Dessin update(Dessin p);

    public void deleteById(long id);  //throws RuntimeException
}
