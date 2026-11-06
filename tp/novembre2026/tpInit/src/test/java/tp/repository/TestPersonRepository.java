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
    public void testFindById(){
        Optional<PersonEntity> personEntityOptional = this.personRepository.findById(1L);
        //hypothese : table avec un element existant d'id =1
        assertTrue(personEntityOptional.isPresent());
        log.trace("person 1 : " + personEntityOptional.get().toString());
    }

}
