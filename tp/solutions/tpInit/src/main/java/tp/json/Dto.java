package tp.json;

public class Dto {
    public record Product(Integer numero,
                        String label,
                        String categorie,
                        Double prix,
                        Double poids){}
}
