package tp.json;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tp.csv.Produit;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestSwitchExpression {

    private List<Produit> produitsDataSet(){
        return Arrays.asList(
                new Produit(1,"stylo bille","papeterie",1.0,0.02),
                new Produit(2,"cahier","papeterie",2.0,0.1),
                new Produit(3,"pommes","nourriture",2.0,1.0),
                new Produit(4,"gomme","papeterie",2.0,0.03),
                new Produit(5,"poires","nourriture",2.0,1.2),
                new Produit(6,"bannanes","nourriture",1.0,0.8)
        );
    }

    private List<Dto.Product> produtRecordDataSet(){
        return Arrays.asList(
                new Dto.Product(1,"stylo bille","papeterie",1.0,0.02),
                new Dto.Product(2,"cahier","papeterie",2.0,0.1),
                new Dto.Product(3,"pommes","nourriture",2.0,1.0),
                new Dto.Product(4,"gomme","papeterie",2.0,0.03),
                new Dto.Product(5,"poires","nourriture",2.0,1.2),
                new Dto.Product(6,"bannanes","nourriture",1.0,0.8)
        );
    }

    private String extractCategorie(Object prod){
        /*
        String categorie="?";
        if(prod instanceof Produit produit)
             categorie = produit.getCategorie();
        else if(prod instanceof Dto.Product product)
            categorie = product.categorie();
        return categorie;
        */
        return switch (prod) {
            case Produit produit -> produit.getCategorie();
            case Dto.Product product -> product.categorie();
            default -> "?";
        };
    }

    private Object prodAvecAugmentation(Object prod,double augmentationPct){
        return switch (prod) {
            case Produit p -> new Produit(p.getNumero(),p.getLabel(),p.getCategorie(),p.getPrix()*(1+augmentationPct/100),p.getPoids());
            case Dto.Product p -> new Dto.Product(p.numero(),p.label(),p.categorie(),p.prix()*(1+augmentationPct/100),p.poids());
            default -> null;
        };
    }


    @Test
    public void testAugmentationPrixAvecSwitchExpression(){
        //var produits =  produitsDataSet();
        var produits =  produtRecordDataSet();
        var produitsApresAugmention = produits.stream()
                .map((p)-> {
                    String categorie=extractCategorie(p);
                    double augmentationPct = switch(categorie){
                        case "papeterie" -> 1.0;
                        case "nourriture" -> 3.0;
                        default -> 0.0;
                    };
                    return prodAvecAugmentation(p,augmentationPct);
                })
                .toList();
        log.debug("produitsApresAugmention="+produitsApresAugmention);
        assertTrue(produitsApresAugmention.size()==6);
    }
}
