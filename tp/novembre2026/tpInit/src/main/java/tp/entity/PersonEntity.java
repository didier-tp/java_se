package tp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp.Personne;

@Getter @Setter
@NoArgsConstructor
public class PersonEntity extends Personne {
    private Long id;//null par defaut
    //NB: la clef primaire id (null avant INSERT) sera auto incrémentée par Postgres

    public PersonEntity(String nom, int age, Double poids, Long id) {
        super(nom, age, poids);
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                "} " + super.toString();
    }
}
