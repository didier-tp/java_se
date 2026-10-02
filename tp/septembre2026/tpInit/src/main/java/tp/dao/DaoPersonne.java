package tp.dao;


/*
 * DAO = Data Access Object
 * avec methodes CRUD (Create : INSERT INTO
 *                    Rechercher : SELECT
 *                    Update,
 *                    Delete )
 */

import tp.PersonEntity;

import java.util.List;

public interface DaoPersonne {
    public PersonEntity findById(long id);

    public List<PersonEntity> findAll();

    public PersonEntity insert(PersonEntity p); //en retour Personne avec id auto incrémenté

    public PersonEntity update(PersonEntity p);

    public void deleteById(long id);  //throws RuntimeException
}