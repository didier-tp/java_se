package tp;

public class Personne {
    //V1 (tout en public)
    private String nom ;
    private int age ;
    private Double poids;

    public Personne(String nom, int age, Double poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    public Personne() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void afficher(){
        System.out.println("Personne nom: " + this.nom + ", age: " + this.age + ", poids: " + this.poids);
    }
}
