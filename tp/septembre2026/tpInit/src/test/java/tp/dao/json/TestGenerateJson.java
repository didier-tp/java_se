package tp.dao.json;

import org.junit.jupiter.api.Test;
import tp.json.Dto;

public class TestGenerateJson {

    @Test
    public void test1(){
        Dto.Produit prod1 = new Dto.Produit(1);
        System.out.println("prod1="+prod1);
    }
}
