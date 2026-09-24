package tp.pers;

public class PersonEntity extends Personne{
    private Long id;

    public PersonEntity(Long id,String nom, int age, double poids) {
        super(nom, age, poids);
        this.id = id;
    }

    public PersonEntity(String nom) {
        super(nom);
        this.id = null;
    }

    public PersonEntity(Long id) {
        super();
        this.id = null;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                "} as a kind of " + super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
