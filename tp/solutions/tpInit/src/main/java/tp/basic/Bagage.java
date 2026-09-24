package tp.basic;

import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor
public class Bagage {
    private String label;
    private Integer poids; //en g
    private Double volume; //en litre (dcm 3)

    public Bagage(String label, Integer poids, Double volume) {
        this.label = label;
        this.poids = poids;
        this.volume = volume;
    }

}
