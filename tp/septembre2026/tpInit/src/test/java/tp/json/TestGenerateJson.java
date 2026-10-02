package tp.json;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.Arrays;
import java.util.List;

public class TestGenerateJson {

    @Test
    public void test1(){
        Dto.Produit prod1 = new Dto.Produit(1 , "cahier" , "papeterie" , 1.5 , 200.5);
        System.out.println("prod1="+prod1);
    }

    @Test
    public void testGenerateJsonViaTextBloc(){
        Dto.Produit prod1 = new Dto.Produit(1, "cahier" , "papeterie" , 1.5 , 200.5);
        String prod1JsonString = prod1.toJsonString(); //voir Dto.Produit avec méthode .toJsonString() basée sur TextBloc + .formatted
        System.out.println("prod1JsonString (via textbloc)="+prod1JsonString);
    }

    private List<Dto.Produit> buildProdList(){
        return Arrays.asList(new Dto.Produit(1 , "cahier" , "papeterie" , 1.5 , 200.5) ,
                              new Dto.Produit(2 , "pomme" , "nourriture" , 2.5 , 1500.5));
    }

    @Test
    public void testSwitchAsExpression(){
        for(Dto.Produit prod : buildProdList()){
            //final var sur variable local  = equivalent du mot clef const en javascript
            final var coeffAugmentation = switch(prod.categorie()){
                case "nourriture" -> 1.1;
                case "papeterie" -> 1.05;
                default -> 1.0;
            };
            Dto.Produit prodApresAugmentation = new Dto.Produit(prod.numero(),prod.label(),prod.categorie(),prod.prix()*coeffAugmentation,prod.poids());
            System.out.println("prodApresAugmentation="+prodApresAugmentation.toString()  + " coeffAugmentation=" + coeffAugmentation);
        }
    }

    @Test void testPatternMatching(){
        var listeDeChosesDiverses = Arrays.asList("azerty" , 5 , 6 , "suite");
        for(Object obj : listeDeChosesDiverses){
           if(obj instanceof String str){
               System.out.println(str + " est une chaine de longeur " + str.length());
           }
           else if(obj instanceof Integer i){
                System.out.println(i + " est un entier");
            }
        }
    }

    @Test
    public void testGenerateJsonViaApiJacksonDataBind(){
        Dto.Produit prod1 = new Dto.Produit(1, "cahier" , "papeterie" , 1.5 , 200.5);
        //JsonMapper nécessite dependency "jackson-databind" dans pom.xml
        JsonMapper jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        String prod1JsonString = jsonMapper.writeValueAsString(prod1);
        System.out.println("prod1JsonString (via jackson-databind)="+prod1JsonString);
    }
}
