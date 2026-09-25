package tp.json;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tp.csv.Produit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//old jackson 2 : com.fasterxml.jackson.databind.ObjectMapper
//new jackson 3 : import tools.jackson.databind.ObjectMapper; et //import tools.jackson.databind.json.JsonMapper

@Slf4j
public class TestGenerateJson {

    private String produitAsJsonTextBloc(Produit produit){
        String jsonTextBloc = """
                              {
                                "numero" : %d,
                                "label" : "%s",
                                "categorie" : "%s",
                                "prix" : %s,
                                "poids" : %s
                              }
                              """.formatted(produit.getNumero(),produit.getLabel(),produit.getCategorie(),
                                            String.valueOf(produit.getPrix()),String.valueOf(produit.getPoids()));
        return jsonTextBloc;
    }

    private void writeTextFile(String fileName,String content){
        try {
            Path filePath = Paths.get(fileName);
            Files.writeString(filePath,content, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Optional<String> readTextFile(String fileName){
        Optional<String> globalContent=Optional.empty();
        try {
            Path filePath = Paths.get(fileName);
            List<String> lines = Files.readAllLines(filePath);
            globalContent = lines.stream().reduce(String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return globalContent;
    }

    @Test
    public void testGenerateJsonFromProductClassAndJacksonDataBind(){
        Produit produit = new Produit(1,"stylo bille","papeterie",1.3,4.2);
        JsonMapper jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        String productJsonString = jsonMapper.writeValueAsString(produit);
        log.debug("productJsonString (from class instance)="+productJsonString);
        writeTextFile("produit1.json",productJsonString);

        Optional<String> optionalReloadedJsonString= readTextFile("produit1.json");
        Produit reloadedProduct = jsonMapper.readValue(optionalReloadedJsonString.get(),Produit.class);
        log.debug("reloadedProduct="+reloadedProduct);
        assertEquals("stylo bille",reloadedProduct.getLabel());
        assertEquals(1.3,reloadedProduct.getPrix(), 0.0001);
        assertEquals(4.2,reloadedProduct.getPoids(),0.0001);
    }

    @Test
    public void testGenerateJsonFromProductRecordAndJacksonDataBind(){
        Dto.Product produit = new Dto.Product(1,"stylo bille","papeterie",1.3,4.2);
        JsonMapper jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        String productJsonString = jsonMapper.writeValueAsString(produit);
        log.debug("productJsonString (from record)="+productJsonString);
        writeTextFile("produit2.json",productJsonString);

        Optional<String> optionalReloadedJsonString= readTextFile("produit2.json");
        Dto.Product reloadedProduct = jsonMapper.readValue(optionalReloadedJsonString.get(),Dto.Product.class);
        log.debug("reloadedProduct (as record)="+reloadedProduct);
        assertEquals("stylo bille",reloadedProduct.label());
        assertEquals(1.3,reloadedProduct.prix(), 0.0001);
        assertEquals(4.2,reloadedProduct.poids(),0.0001);
    }

    @Test
    public void testGenerateJsonFromProductClassAndTextBloc(){
        Produit produit = new Produit(1,"stylo bille","papeterie",1.3,4.2);
        String productTextBloc =  produitAsJsonTextBloc(produit);
        log.debug("productTextBloc (from class instance)="+productTextBloc);
        writeTextFile("produit3.json",productTextBloc);
        //writeTextFile("produit3.json".trim(),productTextBloc);

        String reloadedJsonString= readTextFile("produit3.json").get();
        String reloadedJsonNormalizedString = reloadedJsonString.replaceAll("\\s+", " "); //replace several consecutives spaces as only one space
        log.debug("reloadedJsonString="+reloadedJsonString);
        log.debug("reloadedJsonNormalizedString (to compare with expected textBloc)="+reloadedJsonNormalizedString);
        String expectedJsonTextBloc = """
                {
                  "numero" : 1,
                  "label" : "stylo bille",
                  "categorie" : "papeterie",
                  "prix" : 1.3,
                  "poids" : 4.2
                }
                """;
        String expectedJsonNormalizedContent = expectedJsonTextBloc
                                                .replaceAll("\n", "") //replace new_line as nothing (same behavior as String::concat from lines)
                                                .replaceAll("\\s+", " "); //replace several consecutives spaces as only one space
        log.debug("expectedJsonNormalizedContent (from expectedJsonTextBloc) : "+expectedJsonNormalizedContent);
        assertEquals(expectedJsonNormalizedContent,reloadedJsonNormalizedString);
    }
}
