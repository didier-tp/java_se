package tp.pers.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.pers.PersonEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class TestDaoPersonne {

    private DaoPersonne daoPersonne; //à tester

    @BeforeEach
    public void init(){
        daoPersonne = new DaoPersonneJdbc();
    }

    @Test
    public void displayAllExistingPerson(){
        List<PersonEntity> allPerson = daoPersonne.findAll();
        log.debug("allPerson="+allPerson);
    }

    @Test
    public void testCRUD(){
        //insertion
        PersonEntity pers = daoPersonne.insert(new PersonEntity(null,"nouveau nom", 45, 77.7));
        Long pk = pers.getId();
        //verif insertion
        Assertions.assertTrue( pk != null);
        PersonEntity persRelu = daoPersonne.findById(pk);
        log.debug("persRelu (after insert)="+persRelu);
        assertEquals("nouveau nom",persRelu.getNom());
        assertEquals(45,persRelu.getAge());
        //update , verif update , ...
        persRelu.setAge(persRelu.getAge()+1); //1 an de plus
        persRelu.setPoids(persRelu.getPoids()-1); //1kg de moins
        daoPersonne.update(persRelu);
        PersonEntity persRelu2 = daoPersonne.findById(pk);
        log.debug("persRelu2 (after update)="+persRelu2);
        assertEquals(46,persRelu2.getAge());
        assertEquals(76.7,persRelu2.getPoids(),0.0001);

        //delete
        daoPersonne.deleteById(pk);
        //verif delete
        PersonEntity persRelu3 = daoPersonne.findById(pk);
        assertNull(persRelu3);
    }

}
