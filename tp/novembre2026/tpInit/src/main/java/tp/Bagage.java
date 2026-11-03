package tp;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor //génére constructeur par défaut avec zéro paramètre
@ToString //Attention : piége boucle infinie avec relations bidirectionnelles (ex: Personne <-> Adresse)
@EqualsAndHashCode
public class Bagage {
    private String label;
    private Integer poids; //en g
    private Double volume; //en litres

    public void setPoids(Integer poids) {
        if(poids>=0){
            this.poids = poids;
        } else {
            System.out.println("Erreur: le poids ne peut pas être négatif.");
        }
    }

    public Bagage(String label, Integer poids, Double volume) {
        this.label = label;
        this.poids = poids;
        this.volume = volume;
    }
}
