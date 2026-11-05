package tp.repository;

import tp.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<PersonEntity> findById(Long id);
    List<PersonEntity> findAll();
    void deleteById(Long id) /* throws RuntimeException*/ ;
    // save() dans Spring-Data au sens saveOrUpdate
    PersonEntity insert(PersonEntity p); //en retour l'objet sauvegardé avec id non null
    void update(PersonEntity p);
}
