package tp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.entity.PersonEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestPersonRepository {
    private PersonRepository personRepository; //à tester

    @BeforeEach
    public void init(){
        this.personRepository = new PersonRepositoryJdbc();
    }

    @Test
    public void testFindByIdAfterInsert(){
        PersonEntity newPers = new PersonEntity("toto",44,70.7,null);
        PersonEntity insertedPers = this.personRepository.insert(newPers);
        Long newPk = insertedPers.getId();
        log.trace("insertedPers person  : " + insertedPers + " of id="+newPk);
        Optional<PersonEntity> personEntityOptional = this.personRepository.findById(newPk);
        assertTrue(personEntityOptional.isPresent());
        log.trace("reloaded person  : " + personEntityOptional.get().toString());
    }

}
