package tp;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Bagage {
    private String label;
    private Integer poids; //en grammes
    private Double volume; //en litre

    public Bagage(String label, Integer poids, Double volume) {
        this.label = label;
        this.poids = poids;
        this.volume = volume;
    }

    public void setPoids(Integer poids) {
        if(poids>0)
           this.poids = poids;
    }
}
