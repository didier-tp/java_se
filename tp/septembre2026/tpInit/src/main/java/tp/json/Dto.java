package tp.json;

public class Dto {

    public record Produit(Integer numero , String label, String categorie, Double prix, Double poids ){
        public String toJsonString() {
            return """
                    {
                    "numero" : %d,
                    "label" : "%s",
                    "categorie" : "%s",
                    "prix" : %s ,
                    "poids" : %s 
                    }
                    """.formatted(numero,label,categorie,String.valueOf(prix),String.valueOf(poids));
        }
    }

}
