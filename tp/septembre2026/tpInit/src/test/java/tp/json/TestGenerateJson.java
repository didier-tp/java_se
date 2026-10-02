package tp.json;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

public class TestGenerateJson {

    @Test
    public void test1(){
        Dto.Produit prod1 = new Dto.Produit(1);
        System.out.println("prod1="+prod1);
    }

    @Test
    public void testGenerateJsonViaTextBloc(){
        Dto.Produit prod1 = new Dto.Produit(1);
      //....
    }

    @Test
    public void testGenerateJsonViaApiJacksonDataBind(){
        Dto.Produit prod1 = new Dto.Produit(1);
        //JsonMapper nécessite dependency "jackson-databind" dans pom.xml
        JsonMapper jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        String prod1JsonString = jsonMapper.writeValueAsString(prod1);
        System.out.println("prod1JsonString="+prod1JsonString);
    }
}
