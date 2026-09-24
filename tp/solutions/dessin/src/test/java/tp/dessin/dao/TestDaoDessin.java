package tp.dessin.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.dessin.Dessin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class TestDaoDessin {

    private DaoDessin daoDessin; //à tester

    @BeforeEach
    public void init(){
        daoDessin = new DaoDessinJdbc();
    }

    @Test
    public void displayAllExistingDessins(){
        List<Dessin> allDessin = daoDessin.findAll();
        log.debug("allDessin="+allDessin);
    }

    @Test
    public void testCRUD(){
        //insertion
        Dessin dess = daoDessin.insert(new Dessin(null,"nouveau dessin", "nouveau_dessin.svg", "super dessin"));
        Long pk = dess.getId();
        //verif insertion
        Assertions.assertTrue( pk != null);
        Dessin dessRelu = daoDessin.findById(pk);
        log.debug("dessRelu (after insert)="+dessRelu);
        assertEquals("nouveau dessin",dessRelu.getTitre());
        assertEquals("nouveau_dessin.svg",dessRelu.getPath());
        //update , verif update , ...
        dessRelu.setPath("nouveau_dessin_v2.svg");
        daoDessin.update(dessRelu);
        Dessin dessRelu2 = daoDessin.findById(pk);
        log.debug("dessRelu2 (after update)="+dessRelu2);
        assertEquals("nouveau_dessin_v2.svg",dessRelu.getPath());

        //delete
        daoDessin.deleteById(pk);
        //verif delete
        Dessin dessRelu3 = daoDessin.findById(pk);
        assertNull(dessRelu3);
    }

}
