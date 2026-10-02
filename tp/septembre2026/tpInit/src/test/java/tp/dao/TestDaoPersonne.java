package tp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.PersonEntity;



public class TestDaoPersonne {

    private DaoPersonne daoPersonne; //à tester

    @BeforeEach
    public void initialiser(){
        daoPersonne = new DaoPersonneJdbc();
        //daoPersonne = new DaoPersonneJpaHibernate();
    }

    @Test
    public void testInsertEtFind(){
        PersonEntity person = daoPersonne.findById(1);
        Assertions.assertNotNull(person);
        System.out.println("person avec id=1 :" + person );
    }
}
